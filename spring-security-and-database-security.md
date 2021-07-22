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

\_\_

\_\_

