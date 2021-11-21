#Prerequsites
- Install RabbitMQ: middleware used for JMS Queues

#Quick Start
- run command ```java -jar youtubesearch-0.0.1-SNAPSHOT.jar``` in [target folder](/target)
- open up [localhost:8080/queueB](http://localhost:8080/queueB) for full solution

## Additional features
- [localhost:8080/callrawyt](http://localhost:8080/callrawyt) shows raw information for keyword "telecom" retrieved from Youtube API
- [localhost:8080/queueA](http://localhost:8080/queueA) shows output that goes into Queue A and also sends output to JMS Queue A

# Design Patterns Used
- Model-view-controller (MVC) was used in this implementation, 
- Singleton design pattern was used for the service class
- 

# Schema describing the messages produced
ge
ge

# Heroku Server Deployment
- 

# What should be done differently
