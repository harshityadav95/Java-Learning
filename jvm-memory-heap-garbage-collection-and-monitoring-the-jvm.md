# JVM Memory , Heap , Garbage Collection and Monitoring the JVM

Components in the JVM Memory model

* Heap
* Stack
* Method Area
* Native Memory
* PC Register

### Optimising Java Code&#x20;



## How to Improve Java Backend Performance — W**riting code that just runs is not enough!** <a href="#e6dc" id="e6dc"></a>

### A walkthrough of finding common backend bottlenecks and solutions. <a href="#3d37" id="3d37"></a>

[![Asel Siriwardena](https://miro.medium.com/fit/c/96/96/1\*Rhm9wLkGjW1c8\_T-CVpdYw.jpeg)](https://aselsiriwardena.medium.com/?source=post\_page-----d5c29392f62c-----------------------------------)[Asel Siriwardena](https://aselsiriwardena.medium.com/?source=post\_page-----d5c29392f62c-----------------------------------)Follow[Jul 22](https://levelup.gitconnected.com/how-to-improve-java-backend-performance-writing-code-that-just-runs-is-not-enough-d5c29392f62c?source=post\_page-----d5c29392f62c-----------------------------------) · 8 min read![](https://miro.medium.com/max/1400/1\*XS9T7h4Y22IzeQYodwjbzA.jpeg)Photo by [starline](https://www.freepik.com/starline) on [Freepick](https://www.freepik.com/free-vector/rocket-launch-cloud-sky-background\_13514235.htm#page=1\&query=rocket\&position=17)

In this story, I will be talking about the ways of performance tuning of Java applications. After you build an application, the performance can be different than you expected due to various reasons. Sometimes you may not find them even though you did load testing and all, before going into production.

The main topics that I’m gonna discuss are:

* Finding Bottlenecks
* Common Tweaks to Apply
* JDBC Related Code Changes and Tunings
* JVM Tuning

I must say these are some of the common tweaks you can make and it all depends on your business logic. Some of the optimizations may seem unnecessary for your use case. Okay, let's dig in.

## Finding Performance Bottlenecks <a href="#0d21" id="0d21"></a>

To finetune the backend, first, you need to find what are the bottlenecks in your application. In that case, you can either blindly follow hunches or use a profiler to get better insights of your application.

### Using Java Profilers <a href="#58e6" id="58e6"></a>

If you are going to use a profiler, [**JProfiler**](https://www.ej-technologies.com/products/jprofiler/overview.html) **or** [**JavaMelody**](https://github.com/javamelody/javamelody/wiki) are some great tools that you can use. Find more about free java profilers in [DZone](https://dzone.com/articles/top-9-free-java-process-monitoring-tools-amp-how-t). Specially JProfiler will provide detailed views when profiling databases with JDBC connections, JPA, and also NoSQL. Because most of the time the bottlenecks happen in the Database layer.

### Using Java Heap Dump Analyzers <a href="#86c9" id="86c9"></a>

This is the second option that can be used to find bottlenecks in your backend by analyzing the JVM heap area. This tool is specially used in production to find memory issues that couldn't find in the testing phase.

[**Eclipse Memory Analyzer (MAT)**](https://www.eclipse.org/mat/) is one of the easiest tools that you can use. [**jhat**](https://docs.oracle.com/javase/7/docs/technotes/tools/share/jhat.html) and [**JVisualVM**](https://visualvm.github.io) **** are some alternatives. Using MAT, it is easier to analyze productively heap dumps with millions of objects and see who is preventing the Garbage Collector from collecting objects. This also offers to run a report to automatically extract leak suspects.

![](https://miro.medium.com/max/60/1\*c-tR4UTnpmq\_z49yIrUhgg.png?q=20)![](https://miro.medium.com/max/1540/1\*c-tR4UTnpmq\_z49yIrUhgg.png)Eclipse Memory Analyzer- image from [eclipse.org](https://www.eclipse.org/mat/about/overview.png)

## Common tweaks you can make <a href="#2a64" id="2a64"></a>

The tweaks can be made on code level, JVM level, and database level. I must say again these solely depend on your business requirements and working on the biggest bottlenecks will be easier to get things done faster.

## Do’s and don'ts in code level <a href="#35b7" id="35b7"></a>

> The DZone performance monitoring survey referenced earlier cites code-level problem as the top cause of application performance issues. Most code-level issues are due to bugs in the code constructs, such as long waits, poor iteration, inefficient code algorithms, bad choice of data structures, etc.\
> — [eginnovations.com](https://www.eginnovations.com/blog/top-10-java-performance-problems/#Java-Code)

### Using String Builder instead of using String concatenation <a href="#8507" id="8507"></a>

This is for the cases like when you need to modify a string repeatedly. If you modify only once it will be almost the same. As you know string objects are immutable. So if you modify a string won't get modified actually. What happens is it will create a new string and the overhead associated with creating a new String object can be costly. If it happens repeatedly, new memory allocation over and over is required which is not a good practice.

If you have a single line statement like `String s = "a" + "b" + "c"` you can use concatenation because the compiler will use `StringBuilder` automatically.

### Go for primitives and the stack when possible <a href="#2573" id="2573"></a>

Java heap space and the java stack memory are the two main types of memory in a JVM. Stack memory follows “last-in, first-out” (LIFO) basis in allocation memory and it is the fastest in-memory allocation and memory accessing. Stack has less memory compared to heap and you can use `-Xss` JVM option to increase the stack size. Stack memory stores primitive types and the addresses of objects. So if you use primitives, automatically you are using stack memory which has faster memory access.

![](https://miro.medium.com/max/60/1\*PECJvd7oCb3wtyt1VwZg\_Q.jpeg?q=20)![](https://miro.medium.com/max/1540/1\*PECJvd7oCb3wtyt1VwZg\_Q.jpeg)Relationship of Heap and Stack

### Additional precisions — is it really needed? <a href="#7d28" id="7d28"></a>

Do not use `BigInteger` or `BigDecimal` unless your numbers exceed `long` or `double` ranges. These types require more memory spaces and it slowdowns calculations significantly.

### “finalize” should not be used to set fields to “null” <a href="#0b0c" id="0b0c"></a>

It is unnecessary to use finalize to set fields to null. The garbage collector will automatically remove them when needed. Doing so may actually cause extra work for the garbage collector and maybe the object will live a little longer.

### Use Stream Objects if Possible <a href="#7243" id="7243"></a>

Java stream objects perform better than reader/writer objects because they do not have to deal with string conversion to bytes. As an example `PrintWriter` can be replaced with `OutputStream` .

### Breaking large requests <a href="#2a12" id="2a12"></a>

This is a common abuse in the backend when requests are larger and it takes lots of time to process. You can eliminate it by reducing redundant requests and breaking down huge requests into smaller ones.

## Into JDBC and Database Layers <a href="#6877" id="6877"></a>

Usually, the biggest bottlenecks are in between JDBC and the database layers. Connection pooling and statement pooling can be used to reuse existing connections/prepared statements, eliminating the costs of initiating new connections and parsing SQL.

### Connection pooling <a href="#399d" id="399d"></a>

Creating JDBC connections takes a lot of resources, especially when the JDBC API is utilized in a middle-tier server environment like DataDirect Connect for JDBC or DataDirect SequeLink for JDBC on a Java-enabled web server. So implementing connection pooling can save time ahead of creating new connections when it is needed. The connection pooling module will be created as a layer on top of any standard JDBC driver product. When the connection is released, it can be used by other clients from the pool.

Apache Commons DBCP, HikariCP are some JDBC frameworks that can be used for connection pooling.

### Statement Pooling <a href="#ba25" id="ba25"></a>

Same as connection pooling, statement pooling also can save lots of transaction time. `OraclePreparedStatement` and `OracleCallableStatement` can be cached in statement pool and JDBC driver will automatically search the cache for a matching statement and will reuse them.

![](https://miro.medium.com/max/60/1\*D\_G6GYxStH2HcYG408sBog.png?q=20)![](https://miro.medium.com/max/1540/1\*D\_G6GYxStH2HcYG408sBog.png)JDBC Pooling

### Using Datastores like Infinispan/Redis <a href="#5c8f" id="5c8f"></a>

There are so many in-memory open-source caching interfaces that work with Java. The main goal of these is to provide a fast in-memory cache of frequently accessed data. **Ehcache, Hazelcast, Memcached, AWS’s ElastiCacheare** some other caching options you can go for. They can be used as embedded or distributed as for the requirement.

## JVM Tuning <a href="#455d" id="455d"></a>

Before going into Garbage collection I’ll give a brief into java memory models. Java runtime memory is categorized into five parts. **Heap area, Method area, JVM Stack, Native method stack, and PC registers** are them. In this story, I will be mainly discussing the Heap area because it has a direct effect on the performance of your application. This section may be longer than others because I am hoping to cover some concepts in Java memory management as well as Garbage collection.

![](https://miro.medium.com/max/1400/1\*A1EixgbIiil1BI2zIiwcVQ.jpeg)JVM Heap memory

The Java heap is where the objects of a Java program live. If you get an error like **`java.lang.OutOfMemoryError`** that’s because of heap memory is full. It is a repository for live objects, dead objects, and free memory. The Heap area is divided into **Young generation and Old generation,** this is the workspace of the Garbage Collector (GC).

Young generation is the area that newly created objects are kept. It is further divided into, **EdenSpace and two Survivor spaces**. Those two are called **FromSpace(S0) and ToSpace(S1).** A lifetime of an object starts in the Eden Space by the initial memory allocation. GC is performed once Eden space is filled. That iteration will delete all the dead objects and move live objects to S0. Next new objects will be created in Eden and once again GC will be performed and dead objects will be deleted and live objects will be moved into S1. So this is a repetitive task and JVM will keep empty one survivor space always. This process is called **Minor GC.** Minor GC will continue for a certain number of times and these objects will be moved to Old generation space. This scenario is called a **promotion**. That threshold is called **tenure age** and it can be changed using **** `MaxTenuringThreshold` parameter. GC in Old generation is called **Major GC** and affects application performance. ( Objects that in Old generation are long-lived and that GC takes a long time compared to GC in Young generation.)

The next memory space is Permanent Generation (PermGen). This contains metadata required by the JVM to describe the classes and methods used in the application such as Constant Pool (Memory Pool), Field & Method Data, and Code. Starting from Java 8, PermGen is replaced with Metaspace where the memory can automatically resize, unlimited by default.

All the Garbage Collections are “Stop the World” events. When GC is happening all the threads in the application will be stopped. For the logs, you can enable GC logs by specifying **`-XX`**`:+PrintGC`` `**`-XX`**`:+PrintGCDetails`` `**`-XX`**`:+PrintGCTimeStamps`` `**`-Xloggc`**`:<`**`filename`**`>` .

### JVM Performance Goals <a href="#03d9" id="03d9"></a>

There are three goals that you can consider before start tuning JVM for performance. these goals have to be chosen according to your business need.

* **Latency** — **A**mount of time required to run a GC event.
* **Throughput** — The percentage of time the VM spends executing the application versus time spent performing GC.
* **Footprint** — Amount of memory required by the garbage collector to run smoothly.

### **JVM Tuning Principles** <a href="#ab3e" id="ab3e"></a>

JVM tuning is not a straightforward thing, however, there are some principles that you can follow to make easier that task.

* **Minor GC collection** — GC should collect as many dead objects in one run to reduce GC frequency.
* **GC memory maximization** — it says that the more memory the GC can access during a cycle, the more efficient the cleanup and the lower the collection frequency.
* **Two out of three** — You need to pick two out of the three performance goals that are most relevant to your business need. As an example, high throughput and low latency result in higher memory usage.

You can follow the trial and error method by changing different memory sizes for heap areas and different garbage collectors. I’m hoping to add a story in garbage collectors soon.

## Conclusion <a href="#89ed" id="89ed"></a>

Thanks for reading. I believe this article can help you to optimize your Java application performance. For further details, you can visit referenced sites also. Happy coding!

### References <a href="#9e97" id="9e97"></a>

* [Top 10 Easy Performance Optimisations in Java](https://blog.jooq.org/2015/02/05/top-10-easy-performance-optimisations-in-java/)
* [Top 10 Java Performance Problems and How to Solve Them](https://www.eginnovations.com/blog/top-10-java-performance-problems/#Java-Code)
* [JDBC Connection Pooling](https://www.progress.com/tutorials/jdbc/jdbc-jdbc-connection-pooling)
* [Infinispan Technical Overview](https://infinispan.org/docs/stable/titles/overview/overview.html)
* [J2EE application performance optimization](https://www.infoworld.com/article/2074843/j2ee-application-performance-optimization.html?page=2)
* [How to Properly Plan JVM Performance Tuning](https://dzone.com/articles/how-to-properly-plan-jvm-performance-tuning)
* [Tuning the Java Runtime System](https://docs.oracle.com/cd/E26576\_01/doc.312/e24936/tuning-java.htm#GSPTG00006)
* [Java Garbage Collection Basics](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html)
* [JVM Tuning: How to Prepare Your Environment for Performance Tuning](https://sematext.com/blog/jvm-performance-tuning/)
