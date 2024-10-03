FROM openjdk:21
LABEL name=ms-tarjetas

WORKDIR /app/ms-tarjetas

COPY target/*.jar /app/ms-tarjetas/ms-tarjetas.jar

CMD ["java", "-jar", "ms-tarjetas.jar"]