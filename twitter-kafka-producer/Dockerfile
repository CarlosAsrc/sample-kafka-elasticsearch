FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY build/libs/twitter-kafka-producer.jar twitter-kafka-producer.jar
ENTRYPOINT ["java","-jar","/twitter-kafka-producer.jar"]
