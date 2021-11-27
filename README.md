# Youtube Metric Search Java REST API
By: Gabriel Chan </br>
Date: 11/21/2021

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
- Start RabbitMQ Server usually found in sbin of where you installed RabbitMQ it will be simlar to this: C:\Program Files\RabbitMQ Server\rabbitmq_server-3.9.10\sbin
- Server bat file: rabbitmq-server.bat

# 3. Quick Start
- run command ```java -jar youtubesearch-0.0.1-SNAPSHOT.jar``` in [target folder](target/youtubesearch-0.0.1-SNAPSHOT.jar)
- open up [localhost:8080/queueB](http://localhost:8080/queueB) for full solution
- view JMS queues on [RabbitMQ](http://localhost:15672/)

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
        - This was used to consume Queue A, alter the title from "telcom" to "telco" and send to Queue B
  - Controller classes:
    - [QueueAController](src/main/java/com/youtubemetricapp/youtubesearch/Controller/QueueAController.java)
      - This uses the QueueAModel class method and presents the list of xml messages onto the /queueA page
    - [QueueBController](src/main/java/com/youtubemetricapp/youtubesearch/Controller/QueueBController.java)
      - This uses the QueueBModel class methods and the QueueAModel method to send messages to Queue A and the consume Queue A to send to Queue B with an altered title. The messages for QueueB are also presented on /queueB.
    - [YtApiController](src/main/java/com/youtubemetricapp/youtubesearch/Controller/YtApiController.java)
      - This retrieves information from the Youtube API and converts it into a list of hashmap. This list is also presented onto the page with /callrawyt.
- Singleton design pattern was used for the service class
  - [YtApiService.java](src/main/java/com/youtubemetricapp/youtubesearch/Model/YtApiService.java)
    - This is a singleton class because api calls should not be called very often and should not be altered. This class is used to retrieve Json from Youtube API that contains metric for "telecom" videos.
  - [ConnectionInfo.java](src/main/java/com/youtubemetricapp/youtubesearch/classes/ConnectionInfo.java)
    - This class contains information of the connection host and port, should not be altered and called very often.
  - Credentials.java
    - This is not part of the repository because it contains secret keys for api calls and username and password for RabbitMQ however this is also a singleton class because this should not be altered by the program


# 5. Schema Describing Messages Produced
As Described above, this program collects information of videos with "telecom" the titles from the Youtube Metric API and then uses information of the title and url of those videos converted into an XML message and sent into QueueA.
Queue A is then consumed and the title are of the videos are altered from "telcom" to "telco" and sent as XML messages to QueueB.

# 6. How to compile the source code produced
This is a Java Springboot application and the main function is in the class [YoutubesearchApplication.java](src/main/java/com/youtubemetricapp/youtubesearch/YoutubesearchApplication.java)
As it is compiling, Marven dependencies must be installed as seen in the [pom.xml](pom.xml).
Using IntelliJ, Eclipse or any other complier, you can open up the folder "youtubesearch" and run the program.
This could also be easily done using the steps in [Quick Start](#3-quick-start).
This links can also be found [here](#3-quick-start) and [here](#31-additional-features).

# 7. What could be done differently
- Design Patterns: This could be better in terms of design patterns. I felt that I did not have enough time to full grasp all 24 design patterns of the Gang of 4.
- Deployment: I spent a lot of time trying to get RabbitMQ to work with heroku upon deployment but still could not get it to work.
- Multiple REST API pages: If I did not want to showcase the results of the information retrieved from youtube and the Queues, I would only use QueueB as the REST API page.
- Sending Messages: I would use stream instead of for loops when sending messages if I had more time to learn that function.
- Better String replacement logic: If given more time I would use a better regex expression to also capture accents that contain "telecom"
- Better Exception Handling: Did not spend too much time searching on how to do proper exception handling but tried my best
</br></br>
Overall I believe that I did the best I could do with my experience and time spent. I spent a lot of time trying to plan and learn design patterns and could have possibly spent more time in some aspects to get a better replacement string methods in place. 

# 8. Heroku Server Deployment
- https://cgiyoutubesearch.herokuapp.com/callrawyt
- https://cgiyoutubesearch.herokuapp.com/queueA
- https://cgiyoutubesearch.herokuapp.com/queueB
- Information on what it does in [Additional Features](#31-additional-features)

# 9. Link to Git Repository
https://github.com/gabrielchanyk/youtubesearchapplication

# 10. Challenges Faced
- new to springboot: I needed to spend a lot of learning spring through documentation and video tutorials.
- first time hearing about Gang of 4: Again, needed to spend a lot of time learning this and still felt I could not grasp as much as I wanted.
- new to java and api calls: My more comfortable language is C# however the switch was not too bad but conversion from JSON was a little tricky
- new to JMS queues: Using RabbitMQ was a new experience to me and I have not used message Queues before.
- new to heroku : Took a long time to figure out how to make sure all the dependencies were in the .jar file and that the class with the main method was called. Also ended up not being able to get RabbitMQ to work with heroku.

# 11. Things that I learnt
- how rapid it is to create an application with springboot
- how to use JMS queues with RabbitMQ middleware
- how to convert json to maps and maps to xml
- how to deploy to heroku


# 12. Links that helped with project
https://start.spring.io/ </br>
https://www.youtube.com/watch?v=9SGDpanrc8U </br>
https://springframework.guru/gang-of-four-design-patterns/ </br>
https://www.youtube.com/watch?v=zbp_6yWZrIQ </br>
https://www.youtube.com/watch?v=gN5EVIoV0O8 </br>
https://www.youtube.com/watch?v=C_6uQwBjsmk </br>
https://stackabuse.com/build-a-spring-boot-rest-api-with-java-full-guide/ </br>
https://github.com/youtube/api-samples/blob/master/java/pom.xml </br>
https://stackoverflow.com/questions/13264055/how-can-i-use-textmessage-to-send-an-xml-file-to-the-jms-queue </br>
https://www.youtube.com/watch?v=qcfEChxM7Mk </br>
https://dzone.com/articles/using-jms-in-spring-boot-1 </br>
https://spring.io/guides/gs/consuming-rest/