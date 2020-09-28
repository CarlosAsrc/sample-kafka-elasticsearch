FROM openjdk:14-jdk-alpine AS builder
COPY . /usr/local/src/elasticsearch-kafka-consumer/
WORKDIR /usr/local/src/elasticsearch-kafka-consumer/
RUN ./gradlew clean build

FROM openjdk:14-jdk-alpine
WORKDIR /usr/local/bin/
COPY --from=builder /usr/local/src/elasticsearch-kafka-consumer/build/libs/elasticsearch-kafka-consumer.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","elasticsearch-kafka-consumer.jar"]

