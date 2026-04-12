# Data Structure

## Linked Data Structures

1. IntNode (Basic Structure)

    ```java
    public class IntNode {

        public int item;
        public IntNode next;

        public IntNode(int f, IntNode r) {
            item = f;
            next = r;
        }
    }
    ```
2. Generics List

    - Using `Generics` to build list

3. SLList (Singly Linked List) [(Here is the code)](./Blocks/SLList.md)
   
    ```
    (sentinel)
    +--------+     +--------+     +--------+     +--------+
    |   0    | --> |  item  | --> |  item  | --> |  item  | --> null
    +--------+     +--------+     +--------+     +--------+
    ```
    - Promotions :
      - `size`
      - `sentinel`
  
4. DLList (Doubly Linked List) (Circular Sentinel)
    
    ```
        +--------+      +--------+      +--------+      +--------+
        |sentinel| <--> |  item  | <--> |  item  | <--> |  item  |
        +--------+      +--------+      +--------+      +--------+
            ^                                               ^
            |_______________________________________________|
    ```

    - Promotions :
       - `last` (quicker access)

## Array (with java)

1. Arrays are a special kind of object which consists of a **numbered** sequence of memory boxes.
2. Array's length cannot be changed.
3. Arrays do not have method.
4. Create a new array :
    ```java
    int[] x = new int[5];                   
    int[] y = new int[]{0, 1, 2, 95, 4};
    int[] z = {0, 1, 2, 3, 4};
    ```
5. Arrays have an attribute `length`.
6. Copy arrays
    ```java
    System.arraycopy(a, 0, b, 2, 3); //Java
    ```
    ```python 
    b[2:5] = a[0:3] "Python"
    ```
