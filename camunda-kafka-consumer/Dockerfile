FROM openjdk:8-jdk-alpine
COPY target/camunda-kafka-consumer-1.0.0.jar camunda-kafka-consumer.jar
ENTRYPOINT ["java","-jar","/camunda-kafka-consumer.jar"]