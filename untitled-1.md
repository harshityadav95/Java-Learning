# Spring Boot

### Model View Controller Design Pattern

* Data access layer
* Service Layer
* Presentation Layer 



## Spring Boot  

* Inversion of Control 
* Dependency Injection 

### Spring Framework

* Relies heavily on Inversion of Control 
* Build using POJO - plain old java object
* Spring works with java Standard edition
* POJO+ configuration Metadata = Spring container 
* A Spring bean is a basic building block that is managed by Spring Framework
* Spring is responsible for creating and destorying beans
* Providing dependencies  of the bean which could be other beans or configuration properties
* Intercepting Bean method calls to include additional framework features

### What makes spring powerful ?

* How it manages dependencies
* Design patters are responsibility of developers , solution Inversion of Control  ie role of managing depedencies is handled over to Spring framework 

### What is Inversion of Control  \(IoC\) ?

A process by which  objects define their dependencies and an external container injects those dependencies into the object  , the object need to worry where it dependencies are coming from 

#### Spring Modules

![](.gitbook/assets/image%20%282%29.png)

#### 1. Core Container

* The core container is responsible for managing beans  
* Sets up the context of the application
* Special Expression language  "SpEL"
* All other projects are  build on top of core container
* Spring-core and spring-beans for IoC and dependency injection
* Application Context eliminates singletons and decouple components
* spring-context for access to objects in JNDI \(Java Naming and Directory Interface\) registry style
* spring-expression for working with object at runtime

#### 2. Data Access and Integration 

* spring-jdbc abstracts away vendor specific error codes and handling
* spring-orm for working with Java-Persistance API \(JPA\) , Hibernate and other ORM API's
* spring-jms , spring messaging for message processing 
* spring-tx for working with POJO declaratively

#### 3.Messaging

*  spring-messaging module
* Message, MessageChannel and MessageHandler abstraction
* Annotations for mapping messages to methods
* Similar to Spring MVC annotation based programming

#### 4. Web

* spring-web , spring-webmvc and spring-websocket modules
* spring-web for basic web features eg, servelet listeners , HTTP client
* spring-webmvc for web application programming  using MVC paradigm
* spring-websockets as thin lightweight layer above TCP



#### \(AOP\) Aspect Oriented Programming

* Programming paradigm that adds new " aspects" to behaviour of existing code using "pointcuts" \(external specifications\)
* This ensures existing code is not modified to add new behaviour

#### AOP in Spring

* AOP alliance-compliant aspect oriented programming
* Add additional functionality using interceptors , pointcuts and source level metadata
* Implement aspects with @Aspect annotation
* Spring AOP modules helps combine OOP with AOP

#### 5. Testing 

* Unit-testing as well as integration testing 
* Junit or TestNG
* Loading and caching of Application Context objects
* Mock objects to test code in isolation

### Dependency Management

Dependency management specify what JAR \(java archives \) and libraries our project depends upon

* process of correctly getting all required jar files into correct location \(and into classpath\) so that spring works correctly
* Extremely important and somewhat tricky to get right
* Dependencies include compile time as well as run-time
* Different and distinct from dependency injection 
* Deals with physical resources \(files\)
* Direct vs transitive dependencies
* Transitive dependencies are hardest to manage
* Need copy of all jar libraries for Spring 
* Separated into modules , use what is needed 
* Spring publishes artifacts to Maven Central  
* Maven Central can be thought as repository for JAR files
* Also publishes to specific public Maven repo
* Either use Maven, Gradle  or Ivy
* Install manually or use any above tool above

### Model View Controller

* Spring MVC  or Spring Web MVC , model-view-controller paradigm for building web apps

#### Model

* Encapsulates application state \(but not application logic\)
* Can be queried to obtain state
* Notified  by controller when state needs to change
* Notifies controller once state has been changed  

#### Controller

* Defines application logic \(not application state\)
* Maps user actions to state changes
* Updates application state only via model \(not directly\)
* Updates views once application state has changed

#### Views 

* Present application state to users via appropriate interface
* Allow users to interact with state  , modify state
* Do not store application data \(except for caching\)
* Built using reusable and configurable element

Each component when updated notify the listener via Synchronous methods  vs. Asynchronous events

### Spring MVC Framework

Spring framework relies on three underlying technlogies 

* Servlets
* JSP \(Java Server Pages\)
* JSTL \(Java Pages standard tag library\)

Servlets --&gt; JSP --&gt; JSTL  \(collection of useful JSP tags for common tasks\)

#### Servlets

* used to build dynamic web pages in java
* existed since 1996 , when most web pages were static  
* Run on a Java-enabled web or app server
* Handle incoming HTTP requests

![](.gitbook/assets/image%20%284%29.png)

* Servlet \(Java program\) runs within environment of Servlet Engine 
* Servlet Engine also referred to as Servlet Container
* Deals with cookies and MIME types
* Support for session management and security

#### JSP  , Java Server Pages

* Java ServerPages are an abstraction on top of servlets
* JSP scriptlet is a basic unit -enclosed in tags &lt;% ......%&gt;
* 




















### Reference  

*  [https://docs.spring.io/spring-framework/docs/2.5.x/reference/aop.html](https://docs.spring.io/spring-framework/docs/2.5.x/reference/aop.html)
* 












 

