# Annotations

Learn about the primary Java annotations and how to use them![](https://miro.medium.com/max/630/0*si8rjrJFWXtEV6KU.png)

🍎 If you have ever seen a Java codebase, there is a high chance you saw something like `@Override` or similar tags before a method or class. These tags are called **Annotations**. Annotations are metadata tags that help define additional information to classes, interfaces, methods, or fields. Annotations don’t add extra implementation to the functional code but help with…

* **Information for the compiler** — The compiler can use annotations to catch errors or suppress warnings.
* **Information for the developer** — When someone looks at the code, annotations help make it readable and easy to understand.
* **Compile-and-deployment time processing** — Software tools can process annotations to generate code, XML files, etc.
* **Runtime processing** — Annotations can be examined at runtime to run tests.

☕️ Java has a comprehensive list of predefined annotations to use and even lets you define your own. Here is a list of the most widely used and essential annotations.

## **Predefined Annotations** <a id="310a"></a>

### [@Override](https://javarevisited.blogspot.com/2012/11/why-use-override-annotation-in-java.html#axzz5WKm9BB8F) <a id="930f"></a>

🏎 The `@Override`annotation informs the compiler that the subclass element is overriding the parent class or superclass element. While it is not required to use this annotation when [overriding a method](https://www.java67.com/2019/04/difference-between-overloading-overriding-hiding-shadowing-and-obscuring-in-java-oop.html), it helps prevent errors. If a method marked with override fails to override a superclass method correctly, the compiler generates an error.

```text
class Avatar {    public void destroyEvil() { ... }          // overridden method
}
class Aang extends Avatar {     @Override                                   // overriding method
    public void destroyEvil() { ... }
}
```

### @SuppressWarnings <a id="7bda"></a>

⚠️ Compiler warnings are helpful if you read them, but they often create noise in the terminal. [`@SuppressWarnings`](https://javarevisited.blogspot.com/2015/09/what-is-suppresswarnings-annotation-in-java-unchecked-raw-serial.html) will suppress those warnings. The Java compiler can throw numerous warnings but with `@SuppressWarnings,` you can conceal all warnings or select the warnings you want to suppress.

`@SuppressWarnings({“unchecked”, “deprecated”})` will suppress _unchecked_ and _deprecated_ warnings.

```text
@SuppressWarnings("unchecked")
void uncheckedWarning() {    List words = new ArrayList();
    words.add("hello");                //throws an unchecked warning
}
```

### @deprecated <a id="32a4"></a>

☠️ The `@deprecated`annotation is also very common. It signifies that the annotated method has been deprecated and is not supported by the developers anymore. The [compiler ](https://www.java67.com/2013/03/helloworld-in-java-how-to-write-compile-example-tutorial.html)will not treat the deprecated method any differently from a regular method. So even though the method is callable, it may not return the ideal response. It is the documentation for developers.

```text
@depricated
public String prepareForY2K() { ... }
```

### @author <a id="df95"></a>

📝 The `@author` tag, a simple annotation, documents the author of the method or file. It typically gets paired with some more info like the version, release number, etc.

⭕️ Oracle recommends you should write tags in the order:

* `@author` — documents the author of the code
* `@version` — ensures only one update at a time\(avoids locking\)
* `@param` — documents the name and the description of parameters
* `@return` — documents the return value; omit if void
* `@throws` — documents checked exceptions \(declared in the throws clause\)
* `@see` — link or text that points to a reference
* `@since` — documents the product version when the feature gets added
* `@deprecated` — documents if the code is no longer supported

## Test Annotations <a id="13f1"></a>

🧑🏽‍🏫️ Writing tests is a crucial aspect of the development cycle and is as \(if not more\) important as writing the base code itself. There are various annotations built explicitly for tests.

### [@Test](https://javarevisited.blogspot.com/2012/06/junit4-annotations-test-examples-and.html) <a id="d2a6"></a>

🖋 `@Test` tells JUnit that the annotated method should be executed as a test. To run the method, [JUnit](https://medium.com/javarevisited/5-courses-to-learn-junit-and-mockito-in-2019-best-of-lot-f217d8b93688) constructs a new instance of the class and then invokes the test method.

You can attest two optional parameters to the annotation:

🕒 **`@Timeout`** causes a test method to fail if the execution takes longer than the designated time measured on the clock in milliseconds.

For Example, the following fails\(after 0.1 seconds\):

```text
@Test(timeout=100)
public void toInfinityAndBeyond() {    while(true);
}
```

📣 **`@Expected`** declares that the [test method must throw a specific exception](https://javarevisited.blogspot.com/2013/04/JUnit-tutorial-example-test-exception-thrown-by-java-method.html), or else the test fails.

For Example, the following fails:

```text
@Test(expected=NullPointerException.class)
public void outOfBounds(){     new ArrayList<Object>().get(1);
}
```

### @Ignore <a id="6af6"></a>

🤷🏽‍♀️ The `@Ignore` test annotation [ignores a test or a group of tests ](http://javarevisited.blogspot.sg/2015/02/how-to-disable-junit-test-ignore.html#axzz569M76Trp)to avoid a potential execution failure.

You can `@Ignore` tests in two possible scenarios:

1. To ignore a test method with `@Test`
2. To ignore all the tests at the class level.

```text
@Ignore
@Test(expected=NullPointerException.class)   //obviously wrong test
public void outOfBounds(){ 

    new ArrayList<Object>().get(1); 
}
```

### @Before <a id="378a"></a>

⏪ Methods annotated with `@Before` execute before each test. It is useful when you want to execute some code before running a test, like setting up the test environment. `@Before` was renamed to `@BeforeEach`, which also works.

⏮ The sibling annotation `@BeforeAll` or `@BeforeClass` is used when an expensive operation needs to be performed before a series of tests, like starting a server or making a database change.

### @After <a id="127a"></a>

⏩ `@After` is the opposite of the previous tag. All the methods annotated `@After` will be run after the test.

⏭ `@AfterAll` or `@AfterClass` methods are executed after all the tests of the class have been run.

> All the `@beforeAll` and `@afterall` annotated methods have to be static, so they are executed before running the class’s tests.  
> However, `@before` and `@after` methods should not be [static](https://javarevisited.blogspot.com/2011/11/static-keyword-method-variable-java.html), else the compiler will throw an error.

```text
public class OutputFileTest{    @BeforeAll
    public static void startServer() { ... }    @Before    
    public void createTestLogFile() { ... }

    @After
    public void deleteTestLogFile() { ... }

    @Test 
    public void test1() { ... }    @Test 
    public void test2() { ... }    @AfterAll
    public static void stopServer() { ... }}
```

The above code will execute in the order:

🟢 0️⃣ `startServer()` 1️⃣ `createTestLogFile()` 2️⃣ `test1()` 3️⃣ `deleteTestLogFile()`4️⃣ `createTestLogFile()` 5️⃣ `test2()` 6️⃣ `deleteTestLogFile()` 7️⃣`stopServer()`🔴

🍏 This list nowhere near being comprehensive, but it covers the most basic ones. Use the above examples to get a headstart on [coding in Java](https://javarevisited.blogspot.com/2018/07/top-5-websites-to-learn-coding-in-java.html) with the best practices.

## Custom Annotations <a id="6deb"></a>

☕️ Along with the plethora of predefined annotations that Java offers, we can define our custom annotations. Custom annotations help:

* Reduce the effort of writing code, by adding default behavior to methods
* Add custom behavior to classes and interfaces
* Save the effort of writing XML descriptors and marker interfaces

✍🏽 To define any custom annotation, we first need to declare it using an `@interface` tag. Then we define the target and scope using meta annotations. I have explained the use of the meta annotations\(Retention, Target, Inherited\) in the second half of this article. Custom annotations can be defined on three levels:

* Class Level
* Field Level
* Method Level

### Class Level <a id="17a1"></a>

```text
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.Type)
public @interface CustomAnnotation{ ... }
```

🧑🏽‍🏫 The above `CustomAnnotation` has a runtime retention policy, and we can apply it to all classes. Since it has no methods, it serves as a simple marker to mark the classes we need. One thing to note here is that any class-level custom annotation cannot have any parameters and must not throw any exception. Additionally, the return value types are restricted to [primitives](https://javarevisited.blogspot.com/2015/09/difference-between-primitive-and-reference-variable-java.html), [String](https://medium.com/javarevisited/top-21-string-programming-interview-questions-for-beginners-and-experienced-developers-56037048de45), [Class](https://www.java67.com/2017/08/difference-between-abstract-class-and-interface-in-java8.html), [enums](https://javarevisited.blogspot.com/2011/08/enum-in-java-example-tutorial.html), annotations, their [arrays](https://medium.com/javarevisited/20-array-coding-problems-and-questions-from-programming-interviews-869b475b9121), and the default value cannot be null.

### Field Level <a id="9572"></a>

🏟 Similar to the class level, we can define field level annotations and limit the scope to them.

```text
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomAnnotation{ ... }
```

### **Method Level** <a id="674e"></a>

👨🏾‍🔧 We can also declare an annotation with runtime retention to apply to our classes’ methods as such:

```text
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomAnnotation{ ... }
```

### Using Custom Annotations <a id="1de6"></a>

🪛 Here is an example to demonstrate the use of custom annotation.

It is about 2 classes, Car and Engine. Suppose we have a requirement such that BasicEngine needs to apply to all types of cars. In that case, we can develop custom annotation such as `@BasicEngine`and annotate all kinds of Car implementations \(e.g., HatchBackCars, SportsCars, SedanCars, etc.,\) with `BasicEngine`.

> Custom Annotation Class\(interface\):

```text
import java.lang.annotation.*;
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@interface BasicEngine {
    String mileage() default "20";
    String fuelType() default "Gasoline";
}
```

> Class using the custom annotation\(no need to import\):

```text
@BasicEngine(bId="30", bName="BioDiesel")
public class Car {
    String make;
    String model;    public Car(String make, String model){
        this.make = make;
        this.model = model;
    }

    public void getCarDetails(){
        System.out.println("Car Manufacturer: " + make);
        System.out.println("Car Model: " + model);
    }
}
```

> Driver class to test out the above:

```text
import java.lang.annotation.Annotation;
public class TestCustomAnnotationBasicEngine {public static void main(String[] args) throws Exception{
        Car car = new Car("32", "Diesel");
        car.getCarDetails();
        Class carClass = car.getClass();        Annotation testAnn = carClass.getAnnotation(BasicEngine.class);        BasicEngine engine = (BasicEngine)testAnn;        System.out.println("Mileage: " + engine.mileage());
        System.out.println("Fuel Type: " + engine.fuelType());
    }
}
```

## Meta Annotations <a id="16f5"></a>

🏴 Meta annotations are annotations applied to other annotations to increase their scope. It is very important as it allows us to describe annotations using other annotations, and compose annotations.

♨️ Java incorporates significant meta-annotations directly within the language blueprint:

### @Inherited <a id="a7c4"></a>

👨‍👦 By default, an annotation cannot inherit from its superclass. However, if we need to inherit an annotation from a superclass to a subclass, we use the `@Inherited` annotation.

```text
@Inherited
public @interface CustomAnnotation { ... }@CustomAnnotation
public class ParentClass { ... }public class ChildClass extends ParentClass { ... }
```

Here, the ChildClass class will automatically get the CustomAnnotation since it is inheriting from the ParentClass. ChildClass will be able to call any of CustomAnnotations functionality

### @Target <a id="c6a5"></a>

🎯 Annotations’ scopes are based on the requirements of the method or file, like constructors or declarations. We can restrict an annotation to be applied to specific targets using the `@Target` annotation.

```text
@Target(ElementType.METHOD)
public @interface CustomAnnotation{ ... }
```

In the code above, `CustomAnnotation` is only restricted to methods, i.e. fields, packages, etc, won’t be annotated with it.

> The annotation can be used for any element if the target type isn’t defined.

### @Retention <a id="42cf"></a>

🤔 `@Retention` specifies the level up to which an annotation will be available.  
There are three levels up to which [Java](https://medium.com/javarevisited/3-best-spring-professionals-certification-books-and-courses-for-java-developers-935296c3709) allows us to define retention policies:

* **`RetentionPolicy.SOURCE`**— available at the source level and ignored by the compiler
* **`RetentionPolicy.CLASS`**— available to the compiler at compile-time and ignored by the JVM
* **`RetentionPolicy.RUNTIME`**— available to the [JVM](https://medium.com/javarevisited/7-best-courses-to-learn-jvm-garbage-collection-and-performance-tuning-for-experienced-java-331705180686?source=---------8------------------)

```text
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation{ ... }
```

🍏 In conclusion, annotations are a handy tool that Java provides, and learning them is crucial to being a better and wiser developer!

