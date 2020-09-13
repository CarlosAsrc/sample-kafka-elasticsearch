FROM openjdk:8-jdk-alpine AS builder
COPY . /usr/local/src/twitter-kafka-producer/
WORKDIR /usr/local/src/twitter-kafka-producer/
RUN ./gradlew clean build

FROM openjdk:8-jdk-alpine
WORKDIR /usr/local/bin/
COPY --from=builder /usr/local/src/twitter-kafka-producer/build/libs/twitter-kafka-producer.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","twitter-kafka-producer.jar"]

