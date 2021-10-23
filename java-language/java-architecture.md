# Java Architecture

## &#x20;Example, I have created a simple java program with the name of FirstJavaProgram with**.java **Formate. <a href="f5f7" id="f5f7"></a>

![](https://miro.medium.com/max/60/1\*yx4x7dQs1Rf7nTPhhsz29A.png?q=20)![](https://miro.medium.com/max/1540/1\*yx4x7dQs1Rf7nTPhhsz29A.png)Simple Java Program

Now after creating this program whenever we compile this program by using java Compile **javac** it will generate a Bytecode file with the class name and with **.class** formate. For example below image showing a compilation of the above java program.

![](https://miro.medium.com/max/60/1\*uVhntaxHt\_iL1oBp8d5czA.png?q=20)![](https://miro.medium.com/max/1540/1\*uVhntaxHt\_iL1oBp8d5czA.png)Compile Java Program By **javac**

After Compilation of the java Program, We can see that bytecode is generated as shown in the below image.

![](https://miro.medium.com/max/60/1\*Eyis6h6b5\_RzmxSfhpbw8g.png?q=20)![](https://miro.medium.com/max/1540/1\*Eyis6h6b5\_RzmxSfhpbw8g.png)Before javac (Before Compiler compile program)![](https://miro.medium.com/max/60/1\*VqVmTuoYHlQBbhH\_0PavNg.png?q=20)![](https://miro.medium.com/max/1540/1\*VqVmTuoYHlQBbhH\_0PavNg.png)After javac (After Compiler compile program)

### **Why Java is Platform Independent?** <a href="8eae" id="8eae"></a>

We can see that after compilation of the java program there is a **.class** generated automatically. now, this .class file is **interpreted **by **JVM** and JVM converts this .class file into machine code(Binary Code). So this is the reason why java is a **platform-independent** language because it first generates Byte code this Bytecode is further interpreted by JVM which is a **machine depended.**

Now let's see What is JVM(Java Virtual Machine) but before going to understand we should clear understanding of the whole java program process which is shown in the below Image.

The following figure represents the **Java Architecture** in which each step is elaborate graphically.

![](https://miro.medium.com/max/60/1\*1TBq8MCT\_7nLPKlL9XKV\_w.png?q=20)![](https://miro.medium.com/max/1540/1\*1TBq8MCT\_7nLPKlL9XKV\_w.png)Java Architecture

## Summary Of Java Architecture <a href="157e" id="157e"></a>

**Java Architecture** is a collection of components, i.e., **JVM, JRE,** and **JDK**. It integrates the process of **interpretation and compilation**. It defines all the processes involved in creating a Java program. **Java Architecture** explains each and every step of how a program is compiled and executed.

**Java Architecture** can be explained by using the following steps:

* There is a process of compilation and interpretation in Java.
* Java compiler converts the Java code into byte code.
* After that, the JVM converts the byte code into machine code.
* The machine code is then executed by the machine.

## JVM(Java Virtual Machine) <a href="165a" id="165a"></a>

The main feature of Java is **Write Once Run Anywhere**. The feature states that we can write our code once and use it anywhere or on any operating system. Our Java program can run any of the platforms only because of the Java Virtual Machine. It is a Java platform component that gives us an environment to execute java programs. JVM’s main task is to convert byte code into machine code.

![](https://miro.medium.com/max/60/1\*bxcFVf489fOC\_2wWu1X7eQ.png?q=20)![](https://miro.medium.com/max/1540/1\*bxcFVf489fOC\_2wWu1X7eQ.png)Java Virtual Machine

now lets see every component one by one.

### **ClassLoader Sub-System** <a href="2275" id="2275"></a>

classLoader SubSystem is Responsible for the following** three** Activities.

**1)Loading**

**2)Linking**

_2.1 )Verification_

_2.2)Preparation_

_2.3)Resolution_

**3)Initialization**

### **1.Loading** <a href="a9d4" id="a9d4"></a>

Loading means reading Class files and** store** corresponding** Binary Data in Method Area.**

For each Class file, JVM stores the following method in **Method Area.**

1. Fully Qualified Name of the loaded** Class or Interface or Enum.**
2. Fully Qualified Name of its immediate Parent class.
3. Variable information
4. Method information
5. Modifiers information
6. Constant Pool Information

After loading .class files into Method Area Immediately JVM will create an object of The type **Class** to represent Class level binary information on **Heap Memory.**

For, Example

![](https://miro.medium.com/max/60/1\*qLhIfZqyNg2DIecfS1D8ug.png?q=20)![](https://miro.medium.com/max/1540/1\*qLhIfZqyNg2DIecfS1D8ug.png)

The **Class Object** can be used by Programmer to get **Class Level Information** Like Fully Qualified Name of the Class, Parent Name, Methods and Variables Information Etc.

**Note:** For Every loaded .class file **Only One Class Object** will be created, even though we are using Class Multiple Times in Our Application.

### 2.Linking <a href="e911" id="e911"></a>

Linking Consists of 3 Activities

1. Verification
2. Preparation
3. Resolution

### Verification <a href="2a6e" id="2a6e"></a>

It is the process of ensuring that the Binary Representation of a Class is Structurally Correct OR Not.

That is JVM will Check whether .class File generated by Valid Compiler OR Not.i.ewhether .class File is Properly Formatted OR Not. 

Internally **Byte Code Verifier** which is Part of ClassLoader Sub System is Responsible for this Activity.

If Verification Fails then we will get Runtime Exception Saying **java. lang.VerifyError.**

### Preparation <a href="1e0a" id="1e0a"></a>

In this phase, JVM will **allocate Memory** for **Class Level Static variables** and assign default values **(Not Original Value).**

**Note:** Original Value Assign into_** initialization**_** **part.

### **Resolution** <a href="e2d9" id="e2d9"></a>

It is the process of Replaced Symbolic References used by the loaded type with original References.

Symbolic References are Resolved into Direct References by searching through Method Area to Locate the Referenced Entity.

For Example;

![](https://miro.medium.com/max/60/1\*tt-fPBuKjEmpZ90lX7aqkg.png?q=20)![](https://miro.medium.com/max/1540/1\*tt-fPBuKjEmpZ90lX7aqkg.png)Program for Understanding Resolution

In the above Program, There are Three Classes

1. String Class
2. Student Class
3. Object Class (Object class is Parent Class of all java Classes)

For the above program ClassLoader SubSystem Loads Student.class, String.class and Object.class.

The Name of These Class Names is stored in** the Constant Pool of Student Class.**

In Resolution Phase these names are Replaced with Actual References from the method Area.

### 3.Initialization <a href="3ce9" id="3ce9"></a>

In this Phase, All Static Variables will be assigned with Original Values and Static Blocks will be executed from top to bottom and from Parent to Child.

so summary of the ClassLoader subsystem is shown below figure.

![](https://miro.medium.com/max/60/1\*m\_lDNdJnoxaPXbslc46Ktg.png?q=20)![](https://miro.medium.com/max/1540/1\*m\_lDNdJnoxaPXbslc46Ktg.png)Summary Of ClassLoader-SubSystem

### Types Of ClassLoader <a href="5a22" id="5a22"></a>

ClassLoader SubSystem Contains the Following three ClassLoaders.

1. Bootstrap ClassLoader OR Primordial ClassLoader
2. Extension ClassLoader
3. Application ClassLoader

### BootStrap ClassLoader <a href="67d9" id="67d9"></a>

This ClassLoader is Responsible to load Classes from** jdk\jre\lib.**

All the core java API classes are present in **rt.jar**.which is present in this location only. Hence all API classes like(**String, StringBuffer**) will be loaded by BootStrap ClassLoader only.

It is implemented in Native languages like** C** and **C++**.

### Extension ClassLoader <a href="bc64" id="bc64"></a>

It is the Child of Bootstrap ClassLoader.

ThisClassLoader is Responsible for Load Classes from **jdk\jre\lib\ext.**

### Application ClassLoader <a href="a6c0" id="a6c0"></a>

It is the Child of Extension ClassLoader.

This ClassLoader is Responsible for Load Classes from Application Class Path (Current Working Directory).

It Internally Uses Environment Variable Class Path.

Let's see an example of All ClassLoader

For example,

![](https://miro.medium.com/max/60/1\*E58D6yFIrrRxNeV39n2g0w.png?q=20)![](https://miro.medium.com/max/1540/1\*E58D6yFIrrRxNeV39n2g0w.png)here getClassLoader give the Path of ClassLoader

lets Output of the Above program is

![](https://miro.medium.com/max/60/1\*1YOwGBuN3ObT5JghwlEbUg.png?q=20)![](https://miro.medium.com/max/1448/1\*1YOwGBuN3ObT5JghwlEbUg.png)The output of the Above Java Program

For** String** Class From Bootstrap Class-Path by Bootstrap ClassLoader Output is **null **because we see above BootStrap classpath is implemented in **C **and **C++** so that** **Bootstrap ClassLoader is Not Java Object. Hence we are getting null in the 1st Case.

While** Ketan class** is part of Application ClassLoader because we can get **Ketan.class file in the Current Working Directory.**

## Various Memory Area Of JVM <a href="f07c" id="f07c"></a>

Whole Loading and Running a Java Program JVM required Memory to Store Several Things Like Byte Code, Objects, Variables, Etc.

Total JVM Memory is organized into the following 5 Categories:

1. Method Area
2. Heap Area OR Heap Memory
3. Java Stacks Area
4. PC Registers Area
5. Native Method Stacks Area

### Method Area <a href="ebc5" id="ebc5"></a>

Method Area will be Created at the Time of JVM Start-Up. 

It will be Shared by All Threads (Global Memory). 

This Memory Area Need Not be Continuous. 

Method area shows runtime constant pool.

Total Class Level Binary Information including Static Variables Stored in Method Area.

![](https://miro.medium.com/max/60/1\*y-RBffSu0b141jAjdmf7Vg.png?q=20)![](https://miro.medium.com/max/1019/1\*y-RBffSu0b141jAjdmf7Vg.png)Method Area

### Heap Area <a href="d410" id="d410"></a>

Programmer Point of View Heap Area is Consider an Important Memory Area.

Heap Area can be accessed by All Threads (Global OR Sharable Memory).

Heap Area Need Not be Continuous.

All Objects and corresponding Instance Variables will be stored in the Heap Area.

Every Array in Java is an Object and Hence Arrays Also will be stored in Heap Memory Only.

![](https://miro.medium.com/max/60/1\*EYEeXvhXJMIV50X4I5r4Sg.png?q=20)![](https://miro.medium.com/max/979/1\*EYEeXvhXJMIV50X4I5r4Sg.png)Heap Memory

### Stack Memory <a href="e56d" id="e56d"></a>

For Every Thread, JVM will Create a Separate Runtime Stack.

Runtime Stack will be Created Automatically at the Time of Thread Creation.

All **Method Calls **and corresponding **Local Variables**, **Intermediate Results** will be stored in the Stack.

For Every Method Call, a Separate Entry will be added to the Stack and that Entry is Called Stack Frame. After completing that Method Call the corresponding Entry from the Stack will be removed.

The Data stored in the Stack can be **accessed by Only the corresponding Thread** and it is** Not Available** to **Other Threads.**

![](https://miro.medium.com/max/60/1\*SZWQothZ06x-1SbEDlvq9A.png?q=20)![](https://miro.medium.com/max/1540/1\*SZWQothZ06x-1SbEDlvq9A.png)Stack Area

### **PC Register Area(Program Counter)** <a href="a2d7" id="a2d7"></a>

For Every Thread, a Separate PC Register will be Created at the Time of Thread Creation.

PC Registers contains Address of Current executing Instruction.

Once Instruction Execution Completes Automatically PC Register will be incremented to Hold Address of Next Instruction.

### Native Method Stack <a href="f4ea" id="f4ea"></a>

For Every Thread, JVM will Create a Separate Native Method Stack.

All Native Method Calls invoked by the Thread will be stored in the corresponding Native Method Stack.

### Note: <a href="b54c" id="b54c"></a>

Method Area and Heap Area are for JVM. Whereas Stack Area, PC Registers Area, and Native Method Stack Area are for Thread.

That is  One Separate Heap for Every JVM 

One Separate Method Area for Every JVM 

One Separate Stack for Every Thread

One Separate PC Register for Every Thread

One Separate Native Method Stack for Every Thread

### Summary: <a href="2372" id="2372"></a>

**Static Variables** will be stored in** Method Area.**

**Instance Variables** will be stored in **Heap Area.**

**Local Variables **will be stored in **Stack Area**.

## Execution Engine <a href="4e65" id="4e65"></a>

This is the Central Component of JVM.

Execution Engine is Responsible to Execute Java Class Files.

Execution Engine contains 2 Components for executing Java Classes.

1. **Interpreter**
2. **JIT Compiler**

### Interpreter <a href="e802" id="e802"></a>

It is Responsible to Read Byte Code and Interpret (Convert) into Machine Code (Native Code) and Execute that Machine Code Line by Line.

The Problem with Interpreters is it Interpreters **Every Time Even the Same Method Multiple Times**. Which_** Reduces the Performance of the System.**_

To Overcome this Problem SUN People Introduced** JIT Compilers** in 1.1 Version.

**JIT Compiler**

The Main Purpose of JIT Compiler is to** Improve Performance** which is a disadvantage of java Interpreter.

Internally JIT Compiler Maintains a Separate **Count for Every Method** whenever JVM Come Across any Method Call.

First, that Method will be interpreted normally by the Interpreter, and JIT Compiler Increments the corresponding Count Variable.

This process will be continued for Every Method.

Once if any Method Count Reaches **Threshold** (The Starting Point for a New State) Value, then JIT Compiler Identifies that Method Repeatedly used Method.

Immediately JIT Compiler Compiles that Method and Generates the corresponding Native Code. Next Time JVM Come Across that Method Call then JVM Directly Uses Native Code and Executes it Instead of interpreting Once Again. So that Performance of the System will be Improved.

The** Threshold Count Value** varied from **JVM to JVM.**

Some Advanced JIT Compilers will Re-compile generated Native Code if Count Reaches Threshold Value Second Time So that More optimized Machine Code will be generated.

### Summary of Execution Engine <a href="2a5b" id="2a5b"></a>

JVM Interprets Total Program Line by Line at least Once.

JIT Compilation is Applicable Only for Repeatedly invoked Methods. But Not for Every Method.
