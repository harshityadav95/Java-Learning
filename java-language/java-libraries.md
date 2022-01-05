# Java Libraries

## _**1) Project Lombok**_ <a href="#7694" id="7694"></a>

Tired of files that only have boilerplate methods like getters, setters, constructors, overridden equals methods, etc? Well, then Lombok is for you. Most IDE’s can auto-generate getter and setter methods with just 1 click of a button, but the difference is that IDE generates those methods in the java file itself and Lombok generates all methods directly to the class file. That's not only it, Lombok helps us to remove other boilerplate code also. Let's understand that better with some examples.

```
public class StudentServiceWithoutLombok implements IStudentService{private static final Logger log = Logger.getLogger(StudentServiceWithLombok.class);@Override public void method1() {  log.info("In method1 "); }}
```

The above code is without Lombok and now let's write the same code with the Lombok framework.

```
@Log4jpublic class StudentServiceWithLombok implements IStudentService{@Override public void method1() {  log.info("In method1 "); }}
```

You can see now that we don't have to initialize logger variable in this file, and it is just one line but in bigger projects with thousands of files, you won't have to write the same line again and again, and instead, you can concentrate on your business logic. Let me give you one more example below is one more class without Lombok.

```
public class Student{      private Integer rollNumber;    private String name;      public Student(Integer rollNumber, String name)    {        super();        this.rollNumber = rollNumber;        this.name = name;    }      public Integer getRollNumber()    {        return rollNumber;    }      public void setRollNumber(Integer rollNumber)    {        this.rollNumber = rollNumber;    }      public String getName()    {        return name;    }      public void setName(String name)    {        this.name = name;    }          @Override    public String toString()    {        return "Student ["            + "rollNumber=" + rollNumber            + ", name=" + name + ", "            + "]";    }}
```

Now if I have to write the same code with Lombok, it would be like below:

```
@AllArgsConstructor@Datapublic class Student{    private Integer rollNumber;    private String name;}
```

Shocked? Well, the code with Lombok doesn't have any boilerplate code and is much easier to read.

Lombok can remove a lot of your boilerplate code and you can concentrate on business logic and your code will also look neater. There is much more to Lombok than we discussed in this article you can check all those cool features of the Lombok project from their official [website](https://projectlombok.org).

## _**2) MapStruct**_ <a href="#e4b2" id="e4b2"></a>

Are you using multilayered architecture for your java project? If yes then you should definitely think to use MapStruct. In multilayered architecture, all layers of your application are loosely coupled, so there will be times when you have to map your object which lives in one layer to another layer object, a common example can be when your entities that reside in the persistence layer will be mapped to DTOs in the application layer. Mapping simple POJO with another POJO can require a lot of boilerplate code. Consider below two classes, here we have to map student’s entity class with its DTO.

```
@Entity@Datapublic class Student{    private Integer rollNumber;    private String name;}@Datapublic class StudentDTO{    private Integer rollNumber;    private String name;}
```

If you have to map two classes then probably you will write a convertor class that will have a function that will take Student as input and return StudentDTO as output, something like below

```
public class StudenttoStudentDTOconvertor{  public StudentDTO convert(Student student) {    StudentDTO studentDTO = new StudenDTO();    studentDTO.setName(student.getName);    studentDTO.setRollNo(student.getRollNo());    return studentDTO; }}
```

Student was a simple class with only 2 fields then also you can see we have to write 4 lines of code and if this class had a large number of fields number of lines in the code of convertor will also increase. To avoid writing this simple boilerplate code, we have MapStruct which will automatically generate code. Now let's rewrite our convertor class code with MapStruct.

```
@Mapper public interface StudenttoStudentDTOMapper {     StudentDTO map(Student student);     }
```

Well, that's really it. You really won't have to implement this interface MapStruct will do it automatically for you. By any chance, if in the future you decide to add 1 more new field, say father name, in entity class, then you can add the same field in DTO and you really won't have to do any change in Mapper class. Co,ol no? To know more about other cool features of MapStruct please do check their official [website](https://mapstruct.org).

## _**3) Guava**_ <a href="#d4ac" id="d4ac"></a>

I cannot explain guava better than how it is defined in guava’s GitHub repository. So here is the definition which I have just copied from Github:

> Guava is a set of core Java libraries from Google that includes new collection types (such as multimap and multiset), immutable collections, a graph library, and utilities for concurrency, I/O, hashing, caching, primitives, strings, and more! It is widely used on most Java projects within Google, and widely used by many other companies as well.

Guava sounds very cool right? Guava has utilities that can help you to solve not only simple problems in java but also very complex also. Not able to understand me? Let’s understand with an example

```
public void checkAge(int age) {      if(age<0)      {        throw new IllegalArgumentException(“age id invalid”);      }    }
```

same program can be written with guava library as:

```
public void checkAge(int age) {      Preconditions.checkArgument(age > 0, “Invalid Age”);    }
```

Both programs are doing the same work, but code which is utilizing guava library is cleaner. It may not look much but there is much more in Guava. Let’s take one more example.

```
public void example() {    // Initialising Guava LinkedHashMap Collection    Map<String, String> myMap = Maps.newLinkedHashMap();    myMap.put(“name”, “abc”);    myMap.put(“rollno”, “123”);    String delimiter = “&”;    String separator = “=”;    String result = Joiner.on(delimiter).withKeyValueSeperator(separator).join(myMap);    String expected = “name=abc&rollno=123”;    assertThat(result, expected);}
```

From the above program, you can see how easy it was with the guava to convert a map into the string and that's not it if you have any problem in java be it related to caching, concurrency, or any other domain, most of the time you will find a solution for it in guava library. To know more about the guava library don't forget to check their Github repo.

## _**4)**** ****Feign**_ <a href="#3316" id="3316"></a>

If you are working as a java developer, you would definitely have encountered situations where you have to call some REST API. There are many HTTP clients like _OkHttpClient_ which can be used to call rest API’s but they usually require a lot of code which is not contributing at all to our business needs and if you are calling multiple REST APIs your code will also be duplicated. On top of HTTP client libraries, we also have wrapper libraries like RestTemplate, making our life a little easier. Sample code written with _OkHttpClient will look something like below._

```
OkHttpClient okHttpClient = new OkHttpClient();Request request = new Request.Builder()    .url("https://127.0.0.1/posts/1")    .build();Call call = okHttpClient.newCall(request);try (Response response = call.execute();     ResponseBody body = response.body()) {String string = body.string();  System.out.println(string);} catch (IOException e) {  throw new RuntimeException(e);}
```

Notice how we have to handle exception handling and we also have to convert the response to our Objects so that we can use it. Don't you think we are writing a lot of code for calling a simple Rest API? let's see how the code will look when using RestTemplate.

```
final String uri = "https://127.0.0.1/posts/1"RestTemplate restTemplate = new RestTemplate();PostDTO result = restTemplate.getForObject(uri,PostDTO.class);
```

Well in RestTemplate it's very simple, and your response is also converted into our Object which we can actually use cool right? but still, if we are calling multiple Rest APIs in every API we have to get URL from properties and call the REST API programmatically and I even haven't mentioned that we still have to do error handling there are 2 ways error can be handled 1 is simply writing try-catch but then same code have to be written for every call and another option is exception handler but in both, we have to write a lot of more code then this. Now let's see how can we call Rest API by using feign.

```
@FeignClient(name="content-service", url="127.0.0.1")public interface ContentService {  @RequestLine("GET posts/{postNumber}")  String getDocumentByType(@Param("contentType") String postNumber);}
```

Believe it or not, that is it. Feign will automatically generate code to call Rest API and we as developers will not need to worry about anything. If more API needs to be added we can add methods and we won't have to rewrite code to call REST API, very cool right? and feign has a lot more features we can easily change URL by changing in configuration if you want to retry some API in case of some particular error feign can easily help you do it. It is very easy to test your code if you are using feign. Check out more cool features about feign from their GitHub [repo](https://github.com/OpenFeign/feign#why-feign-and-not-x).

## _**5) Hibernate**_ <a href="#a02d" id="a02d"></a>

Hey, do you hate writing SQL queries in your code? Do you sometimes forget to close DB connection after running a query? then this library is just for you. When you are trying to access the database there is a lot of boilerplate code involved you have to open connection, run query, convert ResultSet to entities, and close connection, and apart from that you also have to write efficient queries. If you will use hibernate you won't have to worry about any of the above things and you can concentrate on your business rather than all these. you can learn more about hibernate from [here](https://medium.com/javarevisited/top-5-hibernate-online-training-courses-for-beginners-and-advance-java-programmers-469460596b2b).
