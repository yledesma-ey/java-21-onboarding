FROM openjdk:21
LABEL name=ms-personas

WORKDIR /app/ms-personas

COPY target/*.jar /app/ms-personas/ms-personas.jar

# Exponer los puertos de la aplicación y de depuración
EXPOSE 8080 5005

# Configurar la JVM para la depuración remota
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "ms-personas.jar"]

#CMD ["java", "-jar", "ms-personas.jar"]