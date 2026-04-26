terraform {
  required_version = ">= 1.5.0"

  backend "s3" {
    bucket         = "mykola-uav-terraform-state"
    key            = "terraform.tfstate"
    region         = "us-east-1"
    encrypt        = true
  }

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

variable "project_name" {
  description = "Project name for tagging"
  type        = string
  default     = "uav-telemetry"
}

variable "db_password" {
  description = "Database master password"
  type        = string
  sensitive   = true
}
