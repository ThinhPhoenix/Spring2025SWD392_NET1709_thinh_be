FROM maven:3.9.4-eclipse-temurin-23 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

# Copy the rest of the project files
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:23

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
