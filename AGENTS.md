# AGENTS.md

# AI Agent Instructions — Financial Processing Platform

## 1. Project Overview

Financial Processing Platform is an event-driven financial transaction processing system built with Java 21 and Spring Boot.

The project demonstrates engineering practices for reliable asynchronous processing, including:

- High-volume batch processing.
- Event-driven communication.
- Idempotent transaction processing.
- Concurrency control.
- Fault tolerance and recovery.
- Transaction reconciliation.
- Observability and automated testing.

This is an educational and portfolio project. All financial operations must be simulated. Never integrate with real banking systems or use real customer or financial data.

## 2. Technology Stack

Use the following technologies and versions as the project baseline:

- Java 21.
- Spring Boot 3.x.
- Maven.
- PostgreSQL.
- Apache Kafka.
- Docker Compose.
- JUnit 5.
- Testcontainers.
- Lombok where it provides meaningful value.

Do not introduce additional frameworks, databases, messaging systems, or infrastructure components without explaining their purpose and obtaining approval.

Check the existing `pom.xml` before adding or changing dependencies.

## 3. Architecture Principles

The target architecture consists of three independently deployable services:

### Batch Service
Responsible for:
- Batch ingestion.
- Input validation.
- Batch lifecycle management.
- Publishing transaction requests.

### Transaction Service
Responsible for:
- Consuming transaction requests.
- Transaction validation.
- Idempotency enforcement.
- Simulated financial execution.
- Publishing transaction results.
- Retry and failure handling.

### Reconciliation Service
Responsible for:
- Consuming transaction results.
- Consolidating processing outcomes.
- Detecting inconsistencies.
- Managing batch reconciliation.

Architectural rules:

- Maintain clear service boundaries.
- Avoid shared business logic between services.
- Prefer asynchronous communication for cross-service workflows.
- Use REST for synchronous API interactions.
- Apply eventual consistency where appropriate.
- Do not claim global exactly-once processing.
- Prefer at-least-once delivery combined with idempotent business operations.
- Evaluate the transactional outbox pattern for reliable event publication.

Do not turn the initial application into a distributed architecture prematurely. Implement capabilities incrementally.

## 4. Java Coding Standards

- Use Java 21 language features where appropriate.
- Follow standard Java naming conventions.
- Use descriptive names for classes, methods, variables, and packages.
- Keep classes focused on a single responsibility.
- Prefer constructor injection.
- Avoid field injection.
- Keep controllers thin.
- Place business rules in the domain or application service layer.
- Avoid unnecessary abstractions and premature optimization.
- Use records for immutable data transfer objects where appropriate.
- Use BigDecimal for monetary values.
- Never use float or double for financial calculations.
- Use explicit validation for external inputs.
- Avoid exposing persistence entities directly through REST APIs.
- Use Lombok selectively rather than automatically.

All source code, comments, identifiers, commit messages, and technical documentation must be written in English.

## 5. Testing Guidelines

Every new business capability must include appropriate automated tests.

Testing priorities:

1. Unit tests for business rules.
2. Integration tests for application components.
3. Repository tests for persistence behavior.
4. Kafka integration tests.
5. Idempotency and duplicate delivery tests.
6. Failure recovery and retry tests.
7. Concurrency tests when applicable.

Guidelines:

- Use JUnit 5.
- Use AssertJ where appropriate.
- Use Mockito selectively.
- Prefer testing observable behavior over implementation details.
- Use Testcontainers for integration tests involving real infrastructure dependencies.
- Do not rely exclusively on mocks for persistence or messaging behavior.

Never remove or weaken existing tests merely to make a build pass.

## 6. Database Guidelines

PostgreSQL is the primary relational database.

- Use explicit database constraints.
- Use transactions where consistency requires them.
- Enforce idempotency with database-level uniqueness constraints.
- Use database migrations for schema changes.
- Avoid destructive schema changes without explicit approval.
- Do not store monetary values using floating-point database types.
- Consider indexing based on actual query patterns.
- Keep persistence concerns separate from domain logic.

Evaluate Flyway or Liquibase before introducing a migration framework.

## 7. Messaging Guidelines

Apache Kafka is the asynchronous messaging backbone.

Initial event contracts:

- `TransactionRequested`
- `TransactionProcessed`

Use consistent message metadata:

- `x-correlation-id`: identifies the overall business journey.
- `x-message-id`: identifies an individual message.
- `x-causation-id`: identifies the direct cause of an event.

Rules:

- Events must have explicit schemas.
- Message identifiers must be stable and unique.
- Consumers must tolerate duplicate delivery.
- Retries must be controlled and observable.
- Permanent failures must not be retried indefinitely.
- Dead Letter Queues must preserve diagnostic information.
- Consider ordering requirements when choosing Kafka partition keys.

Do not assume Kafka alone guarantees business-level idempotency or complete transaction consistency.

## 8. AI Agent Rules

When working on this repository:

1. Inspect existing code and documentation before proposing changes.
2. Follow established architectural decisions.
3. Prefer small, incremental, reviewable changes.
4. Explain significant design decisions and trade-offs.
5. Do not introduce dependencies without justification.
6. Do not invent business requirements.
7. Ask for clarification when requirements are ambiguous and materially affect the implementation.
8. Do not silently modify public APIs or event contracts.
9. Never expose secrets, credentials, tokens, or personal data.
10. Never use real financial or customer information.
11. Do not claim tests have passed without actually running them.
12. Report tests executed and their results.
13. Update documentation when architecture or behavior changes.
14. Do not create commits, push changes, or modify Git remotes unless explicitly requested.
15. Do not perform destructive operations without explicit approval.

Before implementing a feature:

- Identify the affected components.
- Explain the proposed approach.
- Identify relevant tests.
- Consider failure scenarios and consistency requirements.

After implementation:

- Run relevant tests.
- Review the changes for architectural consistency.
- Summarize modified files.
- Report any known limitations.

## 9. Development Workflow

Use Maven Wrapper for project builds and execution.

Common commands:

```bash
./mvnw clean verify
./mvnw spring-boot:run
./mvnw test
```

Follow Conventional Commits:

- `feat:`
- `fix:`
- `refactor:`
- `test:`
- `docs:`
- `chore:`

All changes must remain compatible with Java 21.