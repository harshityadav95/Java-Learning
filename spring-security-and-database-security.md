# Spring Security & Database Security

## What is Spring Security ?

The Spring Security project gives us a rich framework for adding security to our Spring apps.

* Spring Security is more or less the standard for securing your Spring-based applications
* Applications can be stand alone Spring Applications or even web based Spring Applications

## When do we use Spring Security?

* We can use it to easily implement common security concepts like authentication or authorization, 

### Authentication

* Authentication is basically making sure a user or a client is who they say they are 
* In-memory (defining user directly in our configuration) 
* Database 
* LDAP  (**Lightweight Directory Access Protocol**)  
* OpenID etc.

### Authorisation

* Authorisation is making sure that that user or client should be able to access whatever it is they are trying to access.
* [HTTPRequest Level](https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview)
* Method Level  
* Permission Level

### Security Namespace

```
<security:authentication-manager>
<security:authentication-provided/>
<security:authentication-manager/>

<security:http>
</security:http>
```

## Add Dependencies

```
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
</dependency>


<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-config</artifactId>
</dependency>
```

Add the spring security framework in the pom.xml or add it from the manage dependencies.

## Dependency Management

```
<dependencyManagement>
    <dependencies>
        <dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-framework-bom</artifactId>
<version>4.0.6.RELEASE</version>
<type>pom</type>
<scope>import</scope>
</dependency>
</dependencies>
</dependencyManagement>
```

## Dependency vs Dependency Management

In the parent POM, the main difference between the **`<dependencies>`** and **`<dependencyManagement>`** is this:

* Artifacts specified in the **`<dependencies>`** section will ALWAYS be included as a dependency of the child module(s).
* Artifacts specified in the **`<dependencyManagement>`** section, will only be included in the child module if they were also specified in the **`<dependencies>`** section of the child module itself. Why is it good you ask? Because you specify the version and/or scope in the parent, and you can leave them out when specifying the transitive dependencies in the child POM. This can help you use unified versions for dependencies for child modules, without specifying the version in each child module.

![](<.gitbook/assets/image (8).png>)

Link 1 : [Documentation](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)

* MVC applications, are taking advantage of Java servlets.
* Controller in a Spring web MVC Application is`DispatcherServlet`.

Link 2 : [Article ](https://jainamit333.wordpress.com/2017/08/05/difference-between-dependency-management-and-dependencies-in-maven/)

## Dispatcher Servlet

* `DispatcherServlet` is sub classing the HTTP `servlet` class.
* `DispatcherServlet` is basically prepared to handle all the HTTP requests that comes into the application. 
* Spring security was built in such a way that it takes advantage of filters.

## Filters

* we can use a filter to intercept HTTP requests before they actually arrive at the `DispatcherServlet` and that gives us an entry point for implementing security

## XML Application Context for Spring Security

### Setting up an Filter (web.xml)

Goto (_/main/webapp/WEB-INF/web.xml_) and add the following code for the dispatcher servlet

```
<servlet>
<servlet-name>MySpringSecurityDemoApp</servlet-name>
<servlet-class>org.springframework.web.servlet.DispatcherServlet</ servlet-class>
<init-param>


// loading the security config XML from a location
<param-name>contextConfigLocation</param-name>
<param-value>/WEB-INF/config/myDemoApp-servletConfig.xml</param--value>
</init-param>
</servlet>
```

and URL Mapping

```
<servlet-mapping>
<servlet-name>MySpringSecurityDemoApp</servlet-name>
<url-pattern>/</url-pattern>
</servlet-mapping>
```

_enters the following code below \</servlet-mapping>:_

```
<filter>
<filter-name>springSecurityFilterChain</filter-name>
<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
```

Add the Filter Mapping

```
<filter-mapping>
<filter-name>springSecurityFilterChain</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping
```

### Creating an XML Application Context for Spring Security   (web.xml)

Adding Context Loader Listener

```
<listener>
<listener-class>org.springframework.web.context.Context LoaderListener</
</listener>
```

### Add Context Param  (web.xml)

```
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>
/WEB-INF/config/myDemoApp-appConfig.xml
</param-value>
</context-param>
```

Finally web.xml will look like this

```
<?xml version="1.0" encoding="UTF-8" ?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com,
version= "3.0">

<display-name>My Spring Security Demo App</display-name>

<servlet>
<servlet - name >MySpringSecurityDemoApp</servlet- name>
<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-name>
<init-param>
<param-name>contextConfigLocation</param-name>
<param-value>/WEB-INF/çonfig/myDemoApp-servletConfig.xml</param-value>
</init-param>
</servlet>

<servlet-mapping>
<servlet-name>MySpringSecurityDemoApp</servlet-name>
<url-pattern>/</url-pattern>
</servlet-mapping>

<listener>
<listener-class>org.springframework.web.context.Context LoaderListener</listener-class>
</listener>

<filter>
<filter-name>springSecurityFilterChain</filter-name>
<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>

<filter-mapping>
<filter-name>springSecurityFilterChain</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping>

<listener>
<listener-class>org.springframework.web.context.Context LoaderListener</
</listener>

<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>
/WEB-INF/config/myDemoApp-appConfig.xml
</param-value>
</context-param>
```

Now for the Context Param defined

### Define Data Source at  "myDemoApp-appConfig.xml"

The file will look something like this

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:context="http://www.springframework.org/schema/context"
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.
http://www.springframework.org/schema/context http://www.springframework

<bean id="myBasicDataSource" class="org.springframework.jdbc.datasource.Dri
<property name="driverClassName" value="com.mysql.jdbc.Driver"/>
<property name="url" value="jdbc:mysql://Localhost:3306/springdemodb" />
<property name="username" value="admin"/>
<property name="password" value="admin"/>
</bean>

</beans>
```

Now to create the filter we defined in web.xml to provide the actual security define another \<param-value >

```
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>

/WEB-INF/config/myDemoApp-appConfig.xml
/WEB-INF/config/myDemoApp-securityConfig.xml


</param-value>
</context-param>
```

in config folder create another Spring Bean Configuration file for the "myDemoApp-securityConfig.xml"

After the File has been created

* go to my **Namespaces** tab here at the bottom and I am going to check the security namespace; and then **Save** the file.
* Now go back to the **Source**, you can see now I have my `security` namespace set up. 
* Start providing configuration information from within this application context.

Now you can start providing security configuration from bean

## Spring Security in Memory Authentication

go to my **Namespaces** tab here at the bottom and I am going to check the security namespace; and then I am going to **Save** this file. And if I now go back to the **Source**, you can see now I have my `security` namespace set up. Now I can actually go ahead and start providing configuration information from within this application context.

New Web.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com...version= "3.0">
<display-name>My Spring Security Demo App</display-name>
<servlet>
<servlet-name >MySpringSecurityDemoApp</servlet-name>
<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
<init-param>
<param-name>contextConfigLocation</param-name>
<param-value>/WEB-INF/çonfig/myDemoApp-servletConfig.xml</param-value>
</init-param>
</servlet>
<servlet-mapping>
<servlet-name>MySpringSecurityDemoApp</servlet-name>
<url-pattern>/</url-pattern>
</servlet-mapping>
<listener>
<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
  <filter>  
   <filter-name>springSecurityFilterChain</filter-name> 
   <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class> 
  </filter> 
  <filter-mapping> 
   <filter-name>springSecurityFilterChain</filter-name> -->
  <url-pattern>/*</urlpattern> 
  </filter-mapping>
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>
   /WEB-INF/config/myDemoApp-securityConfig.xml 
/WEB-INF/config/myDemoApp-appConfig.xml
</param-value>
</context-param>
```

same as that of web.xml security

### myDemoApp-appConfig.xml

```
< ?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:security="http://www.springframework.org/schema/security"
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www...
http://www.springframework.org/schema/beans http://www.springframework...

<security:http auto-config="true">
<security:intercept-url pattern="/**" access="ROLE_USER"/>
</security:http>


</beans>
```

* using the `http` tag coming from the `security` namespace. 
* tag basically serves as a container for us to provide some HTTP security configuration information.
* `auto-config` attribute to `"true"`. 
* just accepting some default configuration including a very simple login form page , default . 
* `In http` tag  setting up an `intercept-url`,setting up a `pattern` , the `pattern` attribute to `"/**"` and that basically means to intercept everything.
* `access` attribute here to `"ROLE_USER"`. And the effect of this entire line here, this `intercept-url` tag is we're only going to allow access to our application for authenticated users who have a role of user or a role of `"ROLE_USER"` 

### In-Memory authentication,

* `authentication-manager;`

```
< ?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:security="http://www.springframework.org/schema/security"
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www...
http://www.springframework.org/schema/beans http://www.springframework...

<security:http auto-config="true">
<security:intercept-url pattern="/**" access="ROLE_USER"/>
</security:http>

<security:authentication-manager>
<security:authentication-provider>
<security:user-service>
<security:user name="ANDY" password="1234567" authorities="ROLE_USER"/>
<security:user name="ANN" password="7654321" authorities="ROLE_TRIAL_USER"/>
</security:user- service>
</security:authentication-provider>
</security:authentication-manager>


</beans>
```

* Heading on to URL for the application will be prompted with the default layout of the login page

## Using HTTP Basic Authentication

* Heading on to URL for the application will be prompted with the default layout of the login page from "auto config"
* Now to add the  basic HTTP security to the previous config  

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:security="http://www.springframework.org/schema/security"
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www...
http://www.springframework.org/schema/beans http://www.springframework...
<security:http auto-config="true">
<security:intercept-url pattern="/**" access="ROLE_USER"/>

</security:http>

<security:authentication-manager>
<security:authentication-provider>
<security:user-service>
<Security:user name="ANDY" password="1234567" authorities="ROLE_USER"/>
<security:user name="ANN" password="7654321" authorities="ROLE_TRIAL_USER"/>
</security:user- service>
</security:authentication-provider>
</security:authentication-manager>
</beans>
```

* _\<security:http-basic/>_

Now when the webpage is opened the browser HTTP authentication popup will come instead of default HTML login template

## Spring Security Database Authentication

### General Process

* Configure a data source (username, credential drive, URL)
* Create a data access object (pass the data source) (JDBC )
* Set up authentication provider with data access object 

### User Schema

* User Table  
  * username (varchar)
  * password (varchar)
  * enabled -true or false (BIT or boolean)
*   Authorities Table

    * Username (key that reference username table) (varchar)
    * authority (set roles for the user , admin , super admin etc) (varchart)

    Index on that authority table

Script to create the table for the authentication

```
CREATE TABLE IF NOT EXISTS 'springdemodb'. users (
'username' VARCHAR(50) NOT NULL,
'password' VARCHAR(50) NOT NULL,
'enabled' BIT(1) NOT NULL,
PRIMARY KEY ('username'))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

CREATE TABLE IF NOT EXISTS 'springdemodb'.'authorities' (
'username' VARCHAR(50) NOT NULL,
'password' VARCHAR(50) NOT NULL,
UNIQUE INDEX 'ix_auth_username' ('username' ASC, 'password' ASC),
CONSTRAINT 'fk_authorities_users'
FOREIGN KEY ('username')
REFERENCES 'springdemodb'.'users' ('username'))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8CREATE TABLE IF NOT EXISTS 'springdemodb'. users (
'username' VARCHAR(50) NOT NULL,
'password' VARCHAR(50) NOT NULL,
'enabled' BIT(1) NOT NULL,
PRIMARY KEY ('username'))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

CREATE TABLE IF NOT EXISTS 'springdemodb'.'authorities' (
'username' VARCHAR(50) NOT NULL,
'password' VARCHAR(50) NOT NULL,
UNIQUE INDEX 'ix_auth_username' ('username' ASC, 'password' ASC),
CONSTRAINT 'fk_authorities_users'
FOREIGN KEY ('username')
REFERENCES 'springdemodb'.'users' ('username'))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
```

### Authenticating User from a Database

In the Maven Dependencies add the JDBC driver to connect the application to the database , now to connect the application to the data source add the following Bean to the file _myDemoApp-securityConfig.xml_

```
<bean id="dataSource" class= "org.springframework.jdbc.datasource.DriverManagerDataSource" >
<property name="driverClassName" value="com.mysql.jdbc.Driver"/>
<property name="url" value="jdbc:mysql://localhost:3306/springdemodb" />
<property name="username" value="admin"/>
<property name= "password" value="admin"/>
</bean>
```

Now to create an object of the class to access the database , below the above code add the following bean

```
<bean id="myUserDetailsService" class="org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl" >
<property name="dataSource" ref="dataSource"></property>
</bean>
```

now with the database authentication in place remove the code for the InMemory authentication

```
<security:authentication-provider>
<security:user-service>
<security:user name="ANDY" password="1234567" authorities="ROLE_USER" />
<security:user name="ANN" password="7654321" authorities="ROLE_TRIAL_USER" />
</security:user-service>
</security:authentication-provider>
```

and replace with the below mentioned authentication provider

```
<security:authentication-provider user-service-ref="myUserDetailsService" />
```

Now restart the application the login form will authenticate the user using the database

### Minimal Configuration Approach to DB Authentication

Delete the bean object code

```
<bean id="myUserDetailsService" class="org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl" >
<property name="dataSource" ref="dataSource"></property>
</bean>
```

In the authentication manager tag delete the following authentication manager

```
<security:authentication-provider user-service-ref="myUserDetailsService" />
```

_Next enters the following code below the code statement '\<security:authentication-manager>':_

```
<security: authentication-provider>
<security:jdbc-user-service data-source-ref="dataSource"/>
</security:authentication-provider>
```

It save the implementation of creating the database authentication object and abstracts the process

## Spring Security and Encrypted Database password

### Few Spring Security classes provided

* _MD5 org.springframework.security.authentication.encoding.Md5PasswordEncoder;_
* _SHA org.springframework.security.authentication.encoding.ShaPasswordEncoder;_
* _BCrypt (recommended) org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;_

### Authentication Provider

```
Authentication Provider
Set up password encoder
<security:password-encoder hash="bcrypt" />
```

## Woring with MD5

Create a java class to work wit password hashing (PasswordDemo.java)

```
package com.demo.assets;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


@Component
public class PasswordDemo {

public String getMD5Hash(String plainText){
Md5Password Encoder encoder = new Md5PasswordEncoder();
String encryptd = encoder.encodePassword(plainText, null);
System.out.println("Password encrypted using MD5 = " + encrypted );
return encrypted;
}

}
```

Now app.demo method to run the program

```
import org.springframework.context.ApplicationContext;
public class AppDemo {
public static void main(String[ ] args) {
// TODO Auto-generated method stub
ApplicationContext appContext = new ClassPathXmlApplicationContext("com/
//PasswordDemo demo = appContext.getBean("passwordDemo",PasswordDemo.class
//demo.getMD5Hash("1234567");
((ClassPathXmlApplicationContext) appContext).close();
}
}
```

## Working with Bcrypt Hash

Java class to encode the function

```
package com.demo.assets;

import org.springframework.security.authentication.encoding.Md5PasswordEncode
import org.springframework.security.authentication.encoding.ShaPasswordEncode
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordDemo {

public String getMD5Hash(String plainText){

public String getSHAHash(String plainText){

public String getBcryptHash(String plainText){

BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
String encrypted = encoder.encode(plainText);
System.out.println("Password encrypted using Bcrypt = " + encrypted )

return encrypted;
}
}
```

Appdemo class

```
import org.springframework.context.ApplicationContext;

public class AppDemo {

public static void main(String[ ] args) {
// TODO Auto-generated method stub

ApplicationContext appContext = new ClassPathXmlApplicationContext("c

PasswordDemo demo = appContext.getBean("passwordDemo",PasswordDemo.cl

//demo.getMD5Hash("1234567");
//demo.getSHAHash("1234567");
//demo.getBcryptHash("1234567");

((ClassPathXmlApplicationContext) appContext).close();
}
}
```

Notes for Learning

![](https://api.accredible.com/v1/frontend/credential_website_embed_image/badge/35732943)
