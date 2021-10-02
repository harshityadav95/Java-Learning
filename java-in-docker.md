# Java In Docker

## Create a Simple Maven Spring Boot Project

* [https://github.com/in28minutes/docker-crash-course/tree/master/01-hello-world-rest-api](https://github.com/in28minutes/docker-crash-course/tree/master/01-hello-world-rest-api)

## Maven Clean up and Build

* Clean and Build the maven project 

```text
mvn clean
mvn test 
mvn clean package
mvn clean test package
```

## Changing the Name of Build in pom.xml

```text
    <groupId>com.example</groupId>
    <artifactId>mytroubleartifact</artifactId>
    <version>0</version>
    <name>mytrouble</name>
    <description>Courier Managment System Packed in Docker</description>
    <properties>
```

## Fetch a Linux Image with Java

```text
docker run -dit adoptopenjdk/openjdk11:alpine-jre
```

## Find Docker image name of JDK image

```text
docker ps -a
```

## Copy the pkg JAR in Docker

```text
docker container cp target/mytroubleartifact-0.jar distracted_agnesi:/tmp
```

## Run the attacked jar in container

```text
docker container exec distracted_agnesi ls /tmp
```

this will return the JAR file which we added

## Commit the Container with the Changes

```text
docker container commit distracted_agnesi a-repo-name-of-choice/some-app-name:tagname1
```

On Success you will get the container ID name

## Running the Images

```text
docker run  a-repo-name-of-choice/some-app-name:tagname1
```

Running this will run nothing

## Attach JAR to startup

The reason it is not running is we did not attach anything to run at the launch of the container.

We would want to launch a Java application jar at the startup of the container. We copied the JAR file into this image, but did not specify that the JAR file has to be launched at startup.

```text
docker container commit --change="CMD ["java","-jar","/tmp/mytroubleartifact-0.jar"]" distracted_agnesi a-repo-name-of-choice/some-app-name:tagname2
```

