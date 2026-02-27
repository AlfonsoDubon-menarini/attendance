Parking Reservation Management System
This project is a REST API developed with Java and Spring Boot for managing parking space reservations. The system utilizes Hexagonal Architecture to ensure decoupling and long-term maintainability.

Architecture
The design follows the Ports and Adapters pattern, separating technical concerns from core business rules.

Layer Structure
Domain: The core of the system containing pure business logic and models such as Reservation. It has no dependencies on external frameworks.

Application: Defines Use Cases (Input Ports) and output interfaces (Output Ports) for infrastructure communication.

Infrastructure: Contains technical implementations.

Input Adapters: REST Controllers and DTOs with schema validations.

Output Adapters: PostgreSQL persistence and external service clients.

Technical Specifications
Java 25 (LTS)

Spring Boot 4.0.x

Spring Data JPA

PostgreSQL 17

Docker and Docker Compose

SpringDoc OpenAPI (Swagger UI)

Jakarta Validation 3.1

MapStruct for object mapping

System Requirements
Java Development Kit (JDK) 25

Maven 3.9.x or higher

Container runtime environment (Docker)

Configuration Guide
1. Database Infrastructure
To deploy the PostgreSQL database, run the following command in the project root:

Bash
docker-compose up -d

2. Running the Service
To start the application, use the Maven command:

Bash
./mvnw spring-boot:run
The service will start by default on port 8080.

API Documentation
The OpenAPI specification is available interactively via Swagger UI at the following path:

http://localhost:8080/swagger-ui/index.html

Available Operations
POST /api/v1/reservations: Registers a new reservation, validating availability and format.

GET /api/v1/reservations: Retrieves the history of reservations processed by the system.

Development Notes
Identifier Management: UUID (Universally Unique Identifier) is used to ensure reservation uniqueness across distributed systems.

Validation: The system employs the Jakarta standard to reject malformed requests before they reach the application layer.

Mapping: Separation between DTOs and domain models is managed via dedicated mappers to prevent persistence details from leaking into the API.
