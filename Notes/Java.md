# Java

### Basic
1. In java, all code must be part of a `class`
2. Classes are difined with

    ```java
    public class CLASSNAME
    ```
3. Using `{}` to delineate the beginning and ending of things
4. End lines with a semicolon  `;` 
5. The code we want to run must be inside 

    ```java
    public static void main(String[] args)
    ```
6. **Java is `statically` typed** 
7. The compiler checks that all the types in your program are compatible **before the program ever runs**
8. Compilation and Interpretation
   
    ```text
    Hello.java 
        ↓ javac (compiler)
    Hello.class 
        ↓ java (interpreter)
    Program execution
    ```
### Object-Oriented Programming
1. Constructors
    ```java
    public class Car{
        String model;
        int gas;

        public Car(String m, int n){
            this.model = m;
            this.gas = n;
        }
    }
    ```
2. Create new object
    ```java
    Car c1 = new Car("Toyato Supra", 98)
    ```



 