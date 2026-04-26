output "eks_cluster_endpoint" {
  value = module.eks.cluster_endpoint
}

output "eks_cluster_name" {
  value = module.eks.cluster_name
}

output "rds_endpoint" {
  value = aws_db_instance.postgres.endpoint
}

output "kafka_private_ips" {
  value = aws_instance.kafka[*].private_ip
}

output "ecr_repository_urls" {
  value = { for k, v in aws_ecr_repository.apps : k => v.repository_url }
}
