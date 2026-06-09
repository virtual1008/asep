# ASEP - Phase 1

## Objective

Build the Repository Intelligence Foundation for ASEP.

The purpose of Phase 1 is to allow users to upload an existing software repository and enable the platform to understand the structure of the project before introducing AI-driven code generation or autonomous agents.

This phase focuses on repository ingestion, repository analysis, metadata extraction, context generation, and repository knowledge storage.

---

# Scope

The system should support:

* Project creation
* Repository registration
* ZIP repository upload
* Repository extraction
* Repository structure analysis
* Metadata storage
* Context document generation

---

# Out Of Scope

The following features are intentionally excluded from Phase 1:

* Authentication
* Authorization
* Agent orchestration
* Security scanning
* Deployment automation
* Kafka
* Redis
* Neo4j
* Multi-agent workflows
* Code generation
* Test generation
* GitHub integration

---

# Functional Requirements

## Project Management

Users should be able to:

* Create projects
* View projects
* Retrieve project details

---

## Repository Upload

Users should be able to:

* Upload repository ZIP files
* Associate repositories with projects
* Store repository metadata

---

## Repository Analysis

The system should:

* Extract repository contents
* Traverse directories
* Identify source files
* Identify controllers
* Identify services
* Identify repositories
* Identify entities
* Identify configuration files

---

## Context Generation

The system should automatically generate:

### project-context.md

Contains:

* Project overview
* Modules
* Technologies
* Dependencies
* Services

### service-context.md

Contains:

* Service responsibilities
* APIs
* Dependencies
* Database interactions

---

## Repository Knowledge Storage

Store:

* File metadata
* Service metadata
* Repository metadata
* Generated documents

for future AI retrieval.

---

# Technical Stack

Frontend

* Next.js
* TypeScript
* Tailwind CSS
* ShadCN

Backend

* Java 21
* Spring Boot 3

Database

* PostgreSQL

Migration

* Flyway

ORM

* Spring Data JPA

Storage

* Local filesystem

Architecture

* Modular Monolith

---

# Deliverables

At the end of Phase 1, a user should be able to:

1. Create a project.
2. Upload a repository ZIP.
3. Trigger repository analysis.
4. View extracted repository metadata.
5. View generated context documents.

Phase 1 will serve as the foundation for all future AI agents and repository-aware functionality.
