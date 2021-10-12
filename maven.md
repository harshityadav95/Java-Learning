# Maven

![](.gitbook/assets/image%20%2813%29.png)

## Dependency vs Plugin

* [https://stackoverflow.com/questions/11881663/what-is-the-difference-in-maven-between-dependency-and-plugin-tags-in-pom-xml/11883925\#11883925](https://stackoverflow.com/questions/11881663/what-is-the-difference-in-maven-between-dependency-and-plugin-tags-in-pom-xml/11883925#11883925)

So, we can say, plugin is a Jar file which executes the task, and dependency is a Jar which provides the class files to execute the task.

## Group ID and Artifact ID

![](.gitbook/assets/image%20%2814%29.png)

Maven uses a set of identifiers, also called coordinates, to uniquely identify a project and specify how the project artifact should be packaged:

* groupId – a unique base name of the company or group that created the project
* artifactId – a unique name of the project

## Command to Create a Maven project

```text
mvn archetype:generate -DgroupId=com.example.demo -DartifactId=demo -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false -DarchtypeVersion=1.0
```

this will create the java program with hello world with pom.xml and defined folder structure

## Maven Package

The following command will compile and generate the compiled file in the target folder

```text
mvn package
```

but before that add the following java version target in the pom xml

```text
  <build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.6.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## Run Project

navigate into the target folder and then enter the following command to execute the created project

```text
java -cp demo-1.0-SNAPSHOT.jar com.example.demo.App
```

## Reference :

* [https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

