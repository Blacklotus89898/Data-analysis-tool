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
mvn test

# Service Containers (kafka, rabbitMQ) 
docker compose up -d  /down

# Jenkins container
docker compose up -d  /down

```

## Kafka
- Message broker, coordinated by zookeeper (for the moment)
- Topic, subscriber, publisher idea

```bash
curl -X POST -d "Hello Kafka!" http://localhost:9090/kafka/send
```

## STOMP
- Websocket functionality
- Can broadcast
```bash
# Go to
http://localhost:9090/index.html

```

## RabbitMQ
- Similar to Kafka
```bash
curl -X POST http://localhost:9090/rabbitmq/send -d "hello rabbit" -H "Content-Type: text/plain"
```

## Jenkins
```bash
# Build with custom image for docker cli and git
docker compose down
docker compose up --build -d

# Setup
docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword

# Go to
http://192.168.0.113:9090/jenkins 

```

## To fix:
- [ ] Proper shutdown of zookeeper
- [ ] RabbitMQ tests
- [ ] STOMP test

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