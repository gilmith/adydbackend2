FROM eclipse-temurin:25-jre-alpine

LABEL org.opencontainers.image.title="adydbackend2"

WORKDIR /app

RUN addgroup -S app && adduser -S app -G app

COPY app.jar /app/app.jar

RUN chown app:app /app/app.jar

ENV SPRING_PROFILES_ACTIVE=docker

USER app

EXPOSE 10000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
