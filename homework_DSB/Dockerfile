FROM openjdk:8-jdk-alpine
LABEL mantainer = "Mauro Labruna, m.labruna@hotmail.it"
LABEL mantainer = "Antonino Coppolino, coppolinonino@gmail.com"
ARG JAR_FILE=target/*.jar
#Copy the jar file to the container's filesystem
COPY target/*.jar /FileManagementService.jar
WORKDIR /data
COPY ./homework_DSB/files .
ENTRYPOINT ["java","-jar","/FileManagementService.jar"]

