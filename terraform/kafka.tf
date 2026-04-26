data "aws_ami" "amazon_linux_2023" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["al2023-ami-2023*-x86_64"]
  }
}

resource "aws_security_group" "kafka" {
  name        = "${var.project_name}-kafka-sg"
  description = "Allow Kafka traffic"
  vpc_id      = module.vpc.vpc_id

  # Kafka Broker port
  ingress {
    from_port       = 9092
    to_port         = 9092
    protocol        = "tcp"
    security_groups = [module.eks.node_security_group_id]
  }

  # Zookeeper (for internal coordination)
  ingress {
    from_port = 2181
    to_port   = 2181
    protocol  = "tcp"
    self      = true
  }

  # SSH access (optional, via bastion or SSM)
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = [module.vpc.vpc_cidr_block]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Project = var.project_name
  }
}

resource "aws_instance" "kafka" {
  count = 1 # Start with 1 for simplicity

  ami           = data.aws_ami.amazon_linux_2023.id
  instance_type = "t3.large"
  subnet_id     = module.vpc.private_subnets[0]

  vpc_security_group_ids = [aws_security_group.kafka.id]
  iam_instance_profile   = aws_iam_instance_profile.kafka.name

  user_data = <<-EOF
              #!/bin/bash
              yum update -y
              # Install Java 25 (Amazon Corretto)
              # Note: Corretto 25 might be in preview, fallback to 21 if not available
              yum install java-25-amazon-corretto-devel -y || yum install java-21-amazon-corretto-devel -y
              
              # Download Kafka
              wget https://archive.apache.org/dist/kafka/3.7.0/kafka_2.13-3.7.0.tgz
              tar -xzf kafka_2.13-3.7.0.tgz
              mv kafka_2.13-3.7.0 /opt/kafka
              
              # Start Zookeeper
              /opt/kafka/bin/zookeeper-server-start.sh -daemon /opt/kafka/config/zookeeper.properties
              
              # Update Kafka config with private IP
              PRIVATE_IP=$(curl -s http://169.254.169.254/latest/meta-data/local-ipv4)
              sed -i "s|#listeners=PLAINTEXT://:9092|listeners=PLAINTEXT://0.0.0.0:9092|" /opt/kafka/config/server.properties
              sed -i "s|#advertised.listeners=PLAINTEXT://your.host.name:9092|advertised.listeners=PLAINTEXT://$PRIVATE_IP:9092|" /opt/kafka/config/server.properties
              
              # Start Kafka
              /opt/kafka/bin/kafka-server-start.sh -daemon /opt/kafka/config/server.properties
              EOF

  tags = {
    Name    = "${var.project_name}-kafka-${count.index}"
    Project = var.project_name
  }
}

resource "aws_iam_role" "kafka" {
  name = "${var.project_name}-kafka-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "ec2.amazonaws.com"
        }
      },
    ]
  })
}

resource "aws_iam_role_policy_attachment" "ssm" {
  role       = aws_iam_role.kafka.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonSSMManagedInstanceCore"
}

resource "aws_iam_instance_profile" "kafka" {
  name = "${var.project_name}-kafka-profile"
  role = aws_iam_role.kafka.name
}
