# ── Stage 1: build ────────────────────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /workspace

# Copy the POM first — dependencies are resolved as a cached layer and only
# re-downloaded when pom.xml changes, not on every source edit.
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins

# Copy source and produce the executable uber-JAR.
COPY src ./src
RUN mvn -B package -DskipTests

# ── Stage 2: runtime ──────────────────────────────────────────────────────────
# jhdf is pure Java (no native .so files) so Alpine is safe here.
# The image is ~100 MB smaller than the Debian-based eclipse-temurin:17-jre.
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /workspace/target/brapi-api-server-0.1.0-SNAPSHOT.jar app.jar

# curl is required for the docker-compose healthcheck.
RUN apk add --no-cache curl

EXPOSE 8081

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
