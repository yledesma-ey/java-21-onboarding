FROM openjdk:21
LABEL name=ms-cuentas

WORKDIR /app/ms-cuentas

COPY target/*.jar /app/ms-cuentas/ms-cuentas.jar

CMD ["java", "-jar", "ms-cuentas.jar"]