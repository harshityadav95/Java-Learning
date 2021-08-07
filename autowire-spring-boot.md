# @AutoWire Spring Boot

### Dependency Injection is cool, but many misunderstand the purpose of it and most importantly don’t understand how to use it correctly. <a id="309b"></a>

[![Mohammed Atif](https://miro.medium.com/fit/c/43/43/0*9OieNUrLU4PUZfot.)](https://mohammed-atif.medium.com/?source=post_page-----93e6a01cb793--------------------------------)[Mohammed Atif](https://mohammed-atif.medium.com/?source=post_page-----93e6a01cb793--------------------------------)Follow[May 1](https://medium.com/engineering-zemoso/when-not-to-autowire-in-spring-spring-boot-93e6a01cb793?source=post_page-----93e6a01cb793--------------------------------) · 3 min read![](https://miro.medium.com/max/630/1*zVHrnSV40qK-BbGjvHlQRw.jpeg)

```text
Senior Dev : Why are you using field injection instead of constructor injection?Junior Dev : What is field injection? I am using @Autowired
```

A simple conversation that happens pretty often. Sounds trivial but have deep meaning within. Lack of knowledge on why and how things work can lead to catastrophic codes.

Let us dive deep into different type of dependency injections even before we discuss about when and why to use which one

## Types on Dependency Injection <a id="3337"></a>

1. Field Injection
2. Constructor Injection
3. Setter Injection

### Field Injection <a id="fab8"></a>

In simple words declaring a variable, hopefully private, and then annotating it with `@Autowired`

```text
@Autowired
private UserService userService;
```

### Constructor Injection <a id="884c"></a>

In simple words, letting the Spring Container inject the dependencies directly through constructor

```text
@RestController
public class UserController {
  private final UserService userService;
  public UserController(UserService userService){
    this.userService = userService;
  }
}
```

### Setter Injection — Rarely used <a id="0f31"></a>

It is similar to Field Injection but here setter is Annotated with `@Autowired` tag.

```text
private UserService userService;@Autowired
public void setUserService(UserService userService){
  this.userService = userService;
}
```

## How do they differ? <a id="98f5"></a>

Keeping it short,

* Field Injection uses [reflection](https://www.oracle.com/technical-resources/articles/java/javareflection.html) to set the values of private variables
* Constructor Injection happens at the time of creating the object itself
* Setter Injection uses setters to set the value

Now that you have got the gist of dependency injections you can explore more about it outside this blog.

## No more suspense. Jumping right to the point… <a id="4439"></a>

### Verdict <a id="d52e"></a>

Constructor Injection is always the first choice when it comes about Dependency Injection because of its reliable and strict nature.

Field Injection can also be used in the scenarios where Constructor Injection is absolutely not possible \(and Circular Dependencies have to be avoided even as a work around\).

### Other Considerations <a id="7a53"></a>

One key point that is widely discussed when talking about Field Injection vs Constructor Injection is _**Required and Optional Dependencies**_

* Many debate that we can use Field Injection for optional and Constructor Injection for required dependencies, but having optional dependency itself seems to be a _bad design in most of the cases._
* Since the scope of optional dependency is an _Unrestricted Territory_, any developer can randomly add multiple dependencies by calling it optional and eventually reduce the quality of the overall code.
* Personally I feel if you need a dependency in your code then it implies that it is required and if it is optional then why to even include it. This statement is debatable and can vary from person to person.

Let me know how you use dependency injection in your code and how it improves the overall quality of your project

