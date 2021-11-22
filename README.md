# Youtube Metric Search Java REST API

# Table of Contents
1. [About the Project](#1-about-the-project)
2. [Prerequisites](#2-prerequisites)
3. [Quick Start](#3-quick-start)

   3.1[Additional Features](#31-additional-features)
4. [Design Patterns Used](#4-design-patterns-used)
5. [Schema Describing Messages Produced](#5-schema-describing-messages-produced)
6. [How to compile source code produced](#6-how-to-compile-the-source-code-produced)
7. [What could have been done differently?](#7-what-could-be-done-differently)
8. [Links to Heroku Server Deployment](#8-heroku-server-deployment)
9. [Link to Git Repository](#9-link-to-git-repository)
10. [Challenges Faced](#10-challenges-faced)
11. [Things I Learnt](#11-things-that-i-learnt)
12. [Links that helped with project](#12-links-that-helped-with-project)

# 1. About the Project
This project consumes the Youtube API in order to gain metrics of videos that contain "telecom" in their title.
An XML message of each video's title and url are then stored into JMS Queue A.
The XML messages stored in JMS Queue A is then consumed and the title of each video is altered from "telecom" words to "telco" and then stored in JMS Queue B as XML messages.
This is a REST API project and the links at the under [here](#3-quick-start) will explain further of the different results produced.


# 2. Prerequisites
- Install RabbitMQ: middleware used for JMS Queues(can look up guide [here](https://spring.io/guides/gs/messaging-rabbitmq/))

# 3. Quick Start
- run command ```java -jar youtubesearch-0.0.1-SNAPSHOT.jar``` in [target folder](target/youtubesearch-0.0.1-SNAPSHOT.jar)
- open up [localhost:8080/queueB](http://localhost:8080/queueB) for full solution

## 3.1 Additional Features
- [localhost:8080/callrawyt](http://localhost:8080/callrawyt) shows raw information for keyword "telecom" retrieved from Youtube API
- [localhost:8080/queueA](http://localhost:8080/queueA) shows output that goes into Queue A and also sends output to JMS Queue A

# 4. Design Patterns Used
- Model-view-controller (MVC) was used in this implementation,
  - Model classes:
    - [QueueAModel.java](src/main/java/com/youtubemetricapp/youtubesearch/Model/QueueAModel.java)
      - This was used with the YtApiService class in order to get the YT metric data of 
      videos with "telecom" in their titles, converted into xml format and sent into Queue A
    - [QueueBModel.java](src/main/java/com/youtubemetricapp/youtubesearch/Model/QueueBModel.java)
        - This was used to
  - Controller classes:
    - [QueueAController](src/main/java/com/youtubemetricapp/youtubesearch/Controller/QueueAController.java)
      - wdw
    - [QueueBController](src/main/java/com/youtubemetricapp/youtubesearch/Controller/QueueBController.java)
      - w
    - [YtApiController](src/main/java/com/youtubemetricapp/youtubesearch/Controller/YtApiController.java)
      - re 
- Singleton design pattern was used for the service class
  - [YtApiService.java](src/main/java/com/youtubemetricapp/youtubesearch/Model/YtApiService.java)
    - This is a singleton class because api calls should not be called very often and should not be altered. This class is used to retrieve Json from Youtube API that contains metric for "telecom" videos.
  - [ConnectionInfo.java](src/main/java/com/youtubemetricapp/youtubesearch/classes/ConnectionInfo.java)
    - This class contains information of the connection host and port, should not be altered and called very often.
  - Credentials.java
    - This is not part of the repository because it contains secret keys for api calls and username and password for RabbitMQ however this is also a singleton class because this should not be altered by the program


# 5. Schema Describing Messages Produced
As Described above, 

# 6. How to compile the source code produced
This is a Java Springboot application and the main function is in the class [YoutubesearchApplication.java](src/main/java/com/youtubemetricapp/youtubesearch/YoutubesearchApplication.java)
As it is compiling, Marven dependencies must be installed as seen in the [pom.xml](pom.xml).
Using IntelliJ, Eclipse or any other complier, you can open up the folder "youtubesearch" and run the program.
This could also be easily done using the steps in [Quick Start](#3-quick-start).
This links can also be found [here](#3-quick-start) and [here](#31-additional-features).

# 7. What could be done differently
Although I tried my best with design patterns. I only known MVC until I got my assignment. I read up and studied on Gang of 4 Design patterns while working on this project however I feel that I was not able to fully utilize it with what little knowledge I had.
I was also unable to get RabbitMQ to work on heroku, I tried for about a day but was unable to figure out the issue.
I learnt a lot, this is my first time using java springboot, JMS queues and Youtube API.
- stream instead of loop
- replaceall logic flaws with accents and case
- dont need more than 1 rest api page

# 8. Heroku Server Deployment
- https://cgiyoutubesearch.herokuapp.com/callrawyt : this is working
- https://cgiyoutubesearch.herokuapp.com/queueA : Message queue does not work so this give a timeout error.
- https://cgiyoutubesearch.herokuapp.com/queueB : Message queue does not work so this give a timeout error.
- Information on what it does in [Additional Features](#31-additional-features)

# 9. Link to Git Repository
https://github.com/gabrielchanyk/youtubesearchapplication

# 10. Challenges Faced
- new to springboot
- new to java and api calls
- new to JMS queues
- new to heroku
- cannot figure out how to make a connection with RabbitMQ on heroku

# 11. Things that I learnt
- how rapid it is to create an application with springboot
- how to use JMS queues with RabbitMQ middleware
- how to convert json to maps and maps to xml
- how to deploy to heroku


# 12. Links that helped with project
https://start.spring.io/
https://www.youtube.com/watch?v=9SGDpanrc8U
https://springframework.guru/gang-of-four-design-patterns/
https://www.youtube.com/watch?v=zbp_6yWZrIQ
https://www.youtube.com/watch?v=gN5EVIoV0O8
https://www.youtube.com/watch?v=C_6uQwBjsmk
https://stackabuse.com/build-a-spring-boot-rest-api-with-java-full-guide/
https://github.com/youtube/api-samples/blob/master/java/pom.xml
https://stackoverflow.com/questions/13264055/how-can-i-use-textmessage-to-send-an-xml-file-to-the-jms-queue
https://www.youtube.com/watch?v=qcfEChxM7Mk
https://dzone.com/articles/using-jms-in-spring-boot-1
https://spring.io/guides/gs/consuming-rest/