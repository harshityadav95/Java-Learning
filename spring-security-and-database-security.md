# Spring Security & Database Security

### What is Spring Security ?  

The Spring Security project gives us a rich framework for adding security to our Spring apps. 

*  Spring Security is more or less the standard for securing your Spring-based applications
* Applications can be stand alone Spring Applications or even web based Spring Applications

### When do we use Spring Security?

* We can use it to easily implement common security concepts like authentication or authorization, 

#### Authentication 

* Authentication is basically making sure a user or a client is who they say they are 
* In-memory \(defining user directly in our configuration\) 
* Database 
* LDAP  \(**Lightweight Directory Access Protocol**\)  
* OpenID etc.

#### Authorisation 

* Authorisation is making sure that that user or client should be able to access whatever it is they are trying to access.
* [HTTPRequest Level](https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview)
* Method Level  
* Permission Level

#### Security Namespace 

```text
<security:authentication-manager>
<security:authentication-provided/>
<security:authentication-manager/>

<security:http>
</security:http>
```

### Add Dependencies 

```text
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
</dependency>


<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-config</artifactId>
</dependency>

```

Add the spring security framework in the pom.xml  or add it from the manage dependencies.

### Dependency Management 

```text
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

### Dependency vs Dependency Management 

In the parent POM, the main difference between the **`<dependencies>`** and **`<dependencyManagement>`** is this:

* Artifacts specified in the **`<dependencies>`** section will ALWAYS be included as a dependency of the child module\(s\).
* Artifacts specified in the **`<dependencyManagement>`** section, will only be included in the child module if they were also specified in the **`<dependencies>`** section of the child module itself. Why is it good you ask? Because you specify the version and/or scope in the parent, and you can leave them out when specifying the transitive dependencies in the child POM. This can help you use unified versions for dependencies for child modules, without specifying the version in each child module.

![](.gitbook/assets/image%20%2811%29.png)

Link 1 : [Documentation](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)

* MVC applications, are taking advantage of Java servlets.
* Controller in a Spring web MVC Application is`DispatcherServlet`.

Link 2 : [Article ](https://jainamit333.wordpress.com/2017/08/05/difference-between-dependency-management-and-dependencies-in-maven/)

### Dispatcher Servlet 



* `DispatcherServlet` is sub classing the HTTP `servlet` class.
* `DispatcherServlet` is basically prepared to handle all the HTTP requests that comes into the application. 
* Spring security was built in such a way that it takes advantage of filters.



### Filters

* we can use a filter to intercept HTTP requests before they actually arrive at the `DispatcherServlet` and that gives us an entry point for implementing security

### XML Application Context for Spring Security

#### Setting up an Filter \(web.xml\)

Goto \(_/main/webapp/WEB-INF/web.xml_\) and add the following code for the dispatcher servlet  

```text
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

```text
<servlet-mapping>
<servlet-name>MySpringSecurityDemoApp</servlet-name>
<url-pattern>/</url-pattern>
</servlet-mapping>
```

_enters the following code below &lt;/servlet-mapping&gt;:_  

```text
<filter>
<filter-name>springSecurityFilterChain</filter-name>
<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
```

Add the Filter Mapping

```text
<filter-mapping>
<filter-name>springSecurityFilterChain</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping
```

#### Creating an XML Application Context for Spring Security   \(web.xml\)

Adding Context Loader Listener 

```text
<listener>
<listener-class>org.springframework.web.context.Context LoaderListener</
</listener>
```

#### Add Context Param  \(web.xml\)

```text
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>
/WEB-INF/config/myDemoApp-appConfig.xml
</param-value>
</context-param>
```

Finally web.xml will look like this 

```text
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

#### Define Data Source at  "myDemoApp-appConfig.xml"

The file will look something like this  

```text
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

Now to create the filter we defined in web.xml to provide the actual security define another &lt;param-value &gt;

```text
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>

/WEB-INF/config/myDemoApp-appConfig.xml
/WEB-INF/config/myDemoApp-securityConfig.xml


</param-value>
</context-param>

```

in config folder create another  Spring Bean Configuration file for the "myDemoApp-securityConfig.xml"

After the File has been created  

* go to my **Namespaces** tab here at the bottom and I am going to check the security namespace; and then **Save** the file.
* Now go back to the **Source**, you can see now I have my `security` namespace set up. 
* Start providing configuration information from within this application context.

Now you can start providing security configuration from bean  

### Spring Security in Memory Authentication  

go to my **Namespaces** tab here at the bottom and I am going to check the security namespace; and then I am going to **Save** this file. And if I now go back to the **Source**, you can see now I have my `security` namespace set up. Now I can actually go ahead and start providing configuration information from within this application context.

New Web.xml 

```text
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

####  myDemoApp-appConfig.xml

```text
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

####  In-Memory authentication,

* `authentication-manager;`

```text
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

### Using HTTP Basic Authentication  

* Heading on to URL for the application will be prompted with the default layout of the login page

###  __

