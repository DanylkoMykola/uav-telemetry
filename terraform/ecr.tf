locals {
  services = [
    "ingest-service",
    "processor-service",
    "storage-service",
    "dashboard-service",
    "alert-service"
  ]
}

resource "aws_ecr_repository" "apps" {
  for_each = toset(local.services)

  name                 = "${var.project_name}/${each.key}"
  image_tag_mutability = "MUTABLE"
  force_delete         = true

  image_scanning_configuration {
    scan_on_push = true
  }

  tags = {
    Project = var.project_name
  }
}
