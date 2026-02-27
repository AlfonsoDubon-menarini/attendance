Sistema de Gestión de Reservas de Estacionamiento
Este proyecto consiste en una API REST desarrollada con Java y Spring Boot para la gestión de reservas de espacios de estacionamiento. El sistema emplea una Arquitectura Hexagonal para garantizar el desacoplamiento y la mantenibilidad a largo plazo.

Arquitectura
El diseño sigue el patrón de Puertos y Adaptadores, separando las preocupaciones técnicas de las reglas de negocio.

Estructura de Capas
Dominio (Domain): Núcleo del sistema que contiene la lógica de negocio pura y modelos como Reservation. No posee dependencias de frameworks externos.

Aplicación (Application): Define los casos de uso (Input Ports) y las interfaces de salida (Output Ports) para la comunicación con la infraestructura.

Infraestructura (Infrastructure): Contiene las implementaciones técnicas.

Adaptadores de Entrada: Controladores REST y DTOs con validaciones de esquema.

Adaptadores de Salida: Persistencia en PostgreSQL y clientes de servicios externos.

Especificaciones Técnicas
Java 25 (LTS)

Spring Boot 4.0.x

Spring Data JPA

PostgreSQL 17

Docker y Docker Compose

SpringDoc OpenAPI (Swagger UI)

Jakarta Validation 3.1

MapStruct para el mapeo de objetos

Requisitos del Sistema
Java Development Kit (JDK) 25

Maven 3.9.x o superior

Entorno de ejecución de contenedores (Docker)

Guía de Configuración
1. Infraestructura de Base de Datos
Para desplegar la base de datos PostgreSQL, ejecute el siguiente comando en la raíz del proyecto:

Bash
docker-compose up -d
2. Ejecución del Servicio
Para iniciar la aplicación, utilice el comando de Maven:

Bash
./mvnw spring-boot:run
El servicio se iniciará por defecto en el puerto 8080.

Documentación de la API
La especificación OpenAPI está disponible de manera interactiva a través de Swagger UI en la siguiente ruta:

http://localhost:8080/swagger-ui/index.html

Operaciones Disponibles
POST /api/v1/reservations: Registra una nueva reserva validando disponibilidad y formato.

GET /api/v1/reservations: Recupera el histórico de reservas procesadas por el sistema.

Notas de Desarrollo
Gestión de Identificadores: Se utiliza UUID para garantizar la unicidad de las reservas en sistemas distribuidos.

Validación: El sistema emplea el estándar Jakarta para rechazar peticiones mal formadas antes de que lleguen a la capa de aplicación.

Mapeo: La separación entre DTOs y modelos de dominio se gestiona mediante mappers dedicados para evitar el filtrado de detalles de persistencia hacia la API.
