FROM openjdk:8-jdk-alpine
LABEL mantainer = "Mauro Labruna, m.labruna@hotmail.it"
LABEL mantainer = "Antonino Coppolino, coppolinonino@gmail.com"

WORKDIR /consumer/consumer
COPY target/sparkConsumer-1.0.0-SNAPSHOT-jar-with-dependencies.jar /consumer.jar
ENTRYPOINT ["java","-jar","/consumer.jar"]





