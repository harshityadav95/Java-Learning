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

#### Setting up an Filter

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

#### Creating an XML Application Context for Spring Security  

Adding Context Loader Listener 

```text
<listener>
<listener-class>org.springframework.web.context.Context LoaderListener</
</listener>
```





