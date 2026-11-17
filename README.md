# Financial Processing Platform

An event-driven financial transaction processing platform built with Java 21 and Spring Boot.

The project explores the design and implementation of reliable asynchronous processing systems, focusing on idempotency, fault tolerance, concurrency control, observability, and transaction reconciliation.

## Project Goals

The main goal is to demonstrate how to design and build a financial processing platform capable of handling high-volume transaction batches while maintaining consistency and operational reliability.

Key engineering topics:

- Event-driven architecture with Apache Kafka
- REST APIs with Spring Boot
- Transactional persistence with PostgreSQL
- Idempotent message processing
- Retry strategies and Dead Letter Queues (DLQ)
- Concurrency control
- Batch processing and reconciliation
- Automated testing and observability
- Containerized local development

## Target Architecture

The platform is designed around three independently deployable services:

| Service | Responsibility |
|---|---|
| Batch Service | Receives, validates, and manages transaction batches |
| Transaction Service | Processes individual transactions asynchronously |
| Reconciliation Service | Consolidates processing results and reconciles batches |

Apache Kafka is used for asynchronous communication between services.

See [Architecture Documentation](docs/architecture.md) for details.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Main programming language |
| Spring Boot | Application framework |
| Spring Web | REST APIs |
| Spring Boot Actuator | Health checks and application monitoring |
| Spring Validation | Request validation |
| Maven | Build and dependency management |
| Lombok | Boilerplate reduction |
| PostgreSQL | Relational persistence |
| Apache Kafka | Event streaming and messaging |
| Docker Compose | Local infrastructure |
| JUnit 5 | Automated testing |
| Testcontainers | Integration testing |

Some technologies are part of the target architecture and will be introduced incrementally.

## Current Implementation

The repository currently contains the initial Spring Boot application foundation.

Implemented:

- Java 21 and Spring Boot project setup
- REST endpoint for application status
- Spring Boot Actuator health endpoint
- Maven build configuration
- Git version control

Planned:

- Transaction batch ingestion
- PostgreSQL persistence
- Kafka-based asynchronous processing
- Idempotent transaction handling
- Retry and Dead Letter Queue strategies
- Reconciliation workflow
- Integration and performance testing
- Observability and containerized infrastructure

## Running Locally

### Prerequisites

- Java 21
- Maven (or Maven Wrapper)
- Git

### Clone the repository

```bash
git clone https://github.com/luizfelipetozatti/financial-processing-platform.git
cd financial-processing-platform
```

### Run the application

```bash
./mvnw spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

### Health check

```bash
curl http://localhost:8080/actuator/health
```

### Application status

```bash
curl http://localhost:8080/api/v1/status
```

## Engineering Principles

The platform follows these principles:

- **Reliability:** failures must be handled explicitly.
- **Idempotency:** repeated message delivery must not cause duplicate business effects.
- **Consistency:** transaction state must be persisted and managed predictably.
- **Resilience:** transient failures should be recoverable through controlled retries.
- **Observability:** system behavior must be measurable and traceable.
- **Testability:** business rules and integration boundaries must be covered by automated tests.

The platform does not assume exactly-once processing across the entire distributed system. Instead, it uses at-least-once message delivery combined with idempotent processing to prevent duplicate business effects.

## Project Status

The project is under active development. The initial Spring Boot foundation is available; distributed processing capabilities will be delivered incrementally.

## License

To be defined.