# Etapa 1: Construcción (Build)
# Usamos '3-eclipse-temurin-25-alpine' para que tome la última versión de Maven 3 compatible con Java 25
FROM maven:3-eclipse-temurin-25-alpine AS build
WORKDIR /app

# 1. Copiamos el POM y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Copiamos el código y compilamos
COPY src ./src
RUN mvn clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8 -Dproject.reporting.outputEncoding=UTF-8

# Etapa 2: Ejecución (Runtime)
# Usamos la imagen JRE oficial de Temurin 25
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Por seguridad, no corremos como root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
