FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY api/ api
COPY core/ core
COPY bo/ bo

# build
RUN ./gradlew :bo:build -x test

# deploy
FROM eclipse-temurin:21-jdk-alpine
COPY --from=builder /app/bo/build/libs/bo-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]