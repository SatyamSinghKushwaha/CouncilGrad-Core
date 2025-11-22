# ====== BUILD STAGE ======
FROM maven:3.8.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven config and download dependencies (cached)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source and build the application
COPY src ./src
RUN mvn clean package -DskipTests



# ====== RUN STAGE ======
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy the final JAR
COPY --from=build /app/target/CouncilGrad-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app uses
EXPOSE 8443

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
