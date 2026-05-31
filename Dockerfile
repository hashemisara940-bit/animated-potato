FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY . .
RUN ./mvnw -q -DskipTests package || mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/kairo-api/target/kairo-api-0.1.0-SNAPSHOT.jar /app/kairo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/kairo.jar"]
