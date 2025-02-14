# Stage 1: Build the Spring Boot application
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .env /app/.env 

# Install Maven
RUN apk add --no-cache wget \
    && wget https://dlcdn.apache.org/maven/maven-3/3.9.7/binaries/apache-maven-3.9.7-bin.tar.gz -O /tmp/maven.tar.gz \
    && tar -xzf /tmp/maven.tar.gz -C /usr/local/ \
    && mv /usr/local/apache-maven-3.9.7 /usr/local/maven \
    && rm /tmp/maven.tar.gz
ENV MAVEN_HOME=/usr/local/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

# Build the application
RUN mvn clean package

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY .env /app/.env
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]