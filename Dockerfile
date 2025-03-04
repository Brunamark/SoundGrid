FROM eclipse-temurin:21-jdk

WORKDIR /app

RUN mkdir -p /app/data

COPY data/ /app/data
COPY target/*.jar app.jar

RUN chmod -R 777 /app/data

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
