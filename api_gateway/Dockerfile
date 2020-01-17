FROM openjdk:8-jdk-alpine
LABEL mantainer = "Mauro Labruna, m.labruna@hotmail.it"
LABEL mantainer = "Antonino Coppolino, coppolinonino@gmail.com"

WORKDIR /api_gateway/api_gateway
COPY target/*.jar /api_gateway.jar
ENTRYPOINT ["java","-jar","/api_gateway.jar"]





