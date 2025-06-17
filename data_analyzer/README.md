# Data Analyzer Backend

## Project on Kafka, RabbitMQ, STOMP for messaging, queuing with topics
- [ ] Kafka
- [ ] RabbitMQ
- [ ] STOMP

## Project Setup 
```bash
# Maven Project 
mvn clean
mvn spring-boot:run

# Kafka Container
docker compose up/down

```

## Kafka
- Message broker, coordinated by zookeeper (for the moment)
- Topic, subscriber, publisher idea

```bash
curl -X POST -d "Hello Kafka!" http://localhost:8080/kafka/send
```

## STOMP
- Websocket functionality
- Can broadcast
```bash
# Go to
http://localhost:8080/index.html

```

## RabbitMQ
- Similar to Kafka
```bash
curl -X POST http://localhost:8080/rabbitmq/send -d "hello rabbit" -H "Content-Type: text/plain"
```
## TODO:
- [ ] Deep dive Kafka -- look into streams
- [ ] Deep dive RabbitMQ -- streams
- [ ] Deep dive STOMP -- into streams
- [ ] Jenkins -- CICD pipeline
- [ ] Kubernetes
- [ ] Pyspark integration


## For the future
- Look into streams processing and Apache Spark
- Integration with Jenkins
- Deployment with Kubernetes