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

### Compilation and Interpretation

1. The compiler checks that all the types in your program are compatible **before the program ever runs**

2. Compilation and Interpretation
   
    ```text
    Hello.java 
        ↓ javac (compiler)
    Hello.class 
        ↓ java (interpreter)
    Program execution
    ```

### Change file structure

    Ctrl + Shift + P
    >Java: Clean Java Language Server Workspace

- to clean Java language server cache (vscode)

###  Comments

1. single line
   
   ```java
   // The single line
   ```

2. mutiple line
   
   ```java
   /* 
   the first line
   the second line
   */
   ```

3. Doc comments
   
    ```java
    /** 
     * The first line
     * The second line
     */
    ```

###  Classes in Java

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

3. Seperate codes

    ```
    cs61b/
    └── lec/
        └── lec2/
            └── lec2_intro2/
                    ├── Dog.java
                    └── DogLauncher.java
    ```
    write this in the .java at the beginning

    ```java
    package lec2_intro2;
    ```
    run this in the terminal
    
    ```bash
    PS C:\...\cs61b\lec\lec2> javac lec2_intro2/*.java
    PS C:\...\cs61b\lec\lec2> java lec2_intro2.DogLuancher
    ```

4. Terminology [Click here to see Examples](./Blocks/Terminology.md)

    `Non-static method`, a.k.a `Instance method`. 
    
    (If the method is going to be invoked by an instance of the class, then it should be non-static)

    `Declaration`, `Instantiation` and `Assignment` can be combined like this 

    ```java
    Dog hugeDog = new Dog(150);
    ```

### Static vs. Non-Static
- Static methods are invoked using the class name

    ```java
    Dog.makeNoise();
    ```

- Instance methods are invoked using an instance name

    ```java
    Dog maya = new Dog(100);
    maya.makeNoise();
    ```

### Public vs. Private

- Use the **private** keyword to prevent code in other classes from using members (or constructors) of a class.
  
### Nested classes

- Allowing nested class definition.
- There are two types:
  - **Non-static inner class**: (can access outer instance fields)
  - **Static nested class**: (does not depend on the outer class) 


### Types

1. 8 primitive types in Java :

    `byte`, `short`, `int`, `long`, `float`, `double`, `boolean`, `char`

2. Everything else is a reference type
    
   - When we declare a variable of any reference type :

       - Java allocates exactly a box of size 64 bits, no matter what type of object.
       - These bits can be either set to : 
         - Null (all zeros)
         - The 64 bit "address" of a specific instance of that class (returned by new)

3. The Golden Rule of Equals (GRoE)
    
    y = x **copies** all the bits from x into y.

    (If x and y are reference types, the "address"" is copied)

4. Passing parameters obeys the same rule : 
   
    Simply **copy the bits** (also called pass by value) to the new scope.

### Generics

- Generics let classes, interfaces, and methods work with different data types while keeping type safety.
- A generic type uses a placeholder like `T` for the actual type.
<<<<<<< HEAD
=======

    ```java
    public class Box<T> {
        private T item;

        public Box(T i) {
            item = i;
        }

        public T get() {
            return item;
        }
    }
    ```

### Array
>>>>>>> b3c71699ac112bcfec3958fc32af767ddad4f532

    ```java
    public class Box<T> {
        private T item;

        public Box(T i) {
            item = i;
        }

        public T get() {
            return item;
        }
    }
    ```


