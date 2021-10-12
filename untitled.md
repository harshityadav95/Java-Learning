# Main Method

## How does a Java program know where to begin execution?

Every program needs a place where it starts. Java begins execution with the first line of a "main" method which must have the following signature:

public static void main\(String\[\] args\)

This method can be defined in any class, usually public. When a class has a main method, it can be "run," and Java will begin execution with the first line of the main method in the class that is run. For example, you can run a Java class from the command line by typing:

java MyClass

Assuming MyClass is already compiled, and contains a main method, Java will begin by executing in the main method in MyClass.

Many IDEs, including Eclipse, allow you to run a class by selecting it in the GUI, and then clicking a "run" button. This process will also attempt to find the main method in the selected class, and then start executing with the first line of main.

## What if you try to execute a class that has no main method?

You will get an error. Java must have a main method to run a \(normal\) program. Note that running Applets and GUIs is different, and we'll see examples of this later in the course. Here we are talking about executing "plain" Java classes.

## Can you have a main method in more than one class? Does the main method always execute?

Yes, you can have a main method in more than one class, but only the main method in the class that is "run" will execute. For example if Class1 and Class2 both define a main method, then:

java Class1

will run the main method in Class1 \(and not the main method in Class2\) and

java Class2

will run the main method in Class2. This is true even if Class1 and Class2 create objects of the other class and call methods on them.

## What does the "static" mean in main's signature?

The keyword static, when applied to a method or a member variable, simply means that this method \(or member variable\) is defined for the class, but not for particular objects in the class. Thus, main is a general method. There is no "calling object" inside main. If you want to call instance methods from main, you must create objects and then call the instance methods on those objects. You can, however, call other static methods directly.

