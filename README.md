

#About the Project
#Prerequsites
- Install RabbitMQ: middleware used for JMS Queues(can look up guide [here](https://spring.io/guides/gs/messaging-rabbitmq/))

#Quick Start
- run command ```java -jar youtubesearch-0.0.1-SNAPSHOT.jar``` in [target folder](target/youtubesearch-0.0.1-SNAPSHOT.jar)
- open up [localhost:8080/queueB](http://localhost:8080/queueB) for full solution

## Additional Features
- [localhost:8080/callrawyt](http://localhost:8080/callrawyt) shows raw information for keyword "telecom" retrieved from Youtube API
- [localhost:8080/queueA](http://localhost:8080/queueA) shows output that goes into Queue A and also sends output to JMS Queue A

# Design Patterns Used
- Model-view-controller (MVC) was used in this implementation, 

- Singleton design pattern was used for the service class
- 

# Schema describing the messages produced
ge
ge

#How to compile the code
This is a Java Springboot application and the main function is in the class [YoutubesearchApplication.java](src/main/java/com/youtubemetricapp/youtubesearch/YoutubesearchApplication.java)
As it is compiling, Marven dependencies must be installed as seen in the [pom.xml](pom.xml)

# Heroku Server Deployment
- https://cgiyoutubesearch.herokuapp.com/callrawyt : this is working
- https://cgiyoutubesearch.herokuapp.com/queueA : Message queue does not work so this give a timeout error.
- https://cgiyoutubesearch.herokuapp.com/queueB : Message queue does not work so this give a timeout error.
- Information on what it does in Additional Features

# What should be done differently
Although I tried my best with design patterns. I only known MVC until I got my assignment. I read up and studied on Gang of 4 Design patterns while working on this project however I feel that I was not able to fully utilize it with what little knowledge I had.
I was also unable to get RabbitMQ to work on heroku, I tried for about a day but was unable to figure out the issue.
I learnt a lot, this is my first time using java springboot, JMS queues and Youtube API.

# Link to Git Repository
https://github.com/gabrielchanyk/youtubesearchapplication

# Some links that helped with the project