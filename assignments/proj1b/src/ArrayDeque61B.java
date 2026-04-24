import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] arr;
    private int startSize = 8;
    private int capacity; // array length
    private int size; // The size of the deque
    private int front; // The index of the ArrayDeque's front
    private int back;  // The index of the ArrayDeque's back

    public ArrayDeque61B () {
        capacity = startSize;
        arr = (T[]) new Object[capacity];
        size = 0;
        front = 0;
        back = -1;
    }

    // to check the array full
    private boolean checkFull() {
        return size == capacity;
    }

    // to check the array empty
    private boolean checkEmpty() {
        return size == 0;
    }

    // to resize up the arr to imitate larger deque with O(1)
    private void resize() {
        int newSize = capacity * 2;
        T[] newArr = (T[]) new Object[newSize];
        for (int i = 0; i< size; i++) {
            newArr[i] =(T) get(i);
        }
        capacity = newSize;
        front = 0;
        back = size - 1;
        arr = newArr;
    }

    // to check whether the arr need to size down to save memory
    private boolean downFlag() {
        return size < capacity / 4 && capacity > startSize;
    }

    // to resize down the arr to save memory
    private void resizeDown() {
        int newSize = capacity / 2;
        T[] newArr = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++){
            newArr[i] = (T) get(i);
        }
        capacity = newSize;
        front = 0;
        back = size - 1;
        arr = newArr;
    }

    @Override
    public void addFirst(T x) {
        if (checkFull()){
            resize();
        }
        size++;
        front--;
        arr[Math.floorMod(front, capacity)] = x;
    }

    @Override
    public void addLast(T x) {
        if (checkFull()){
            resize();
        }
        size++;
        back++;
        arr[Math.floorMod(back, capacity)] = x;
    }

    @Override
    public List<T> toList() {

        List<T> toL = new ArrayList<>();
        if (checkEmpty()) {
            return toL;
        }
        for(int i = 0; i< size; i++) {
            toL.add(get(i));
        }

        return toL;

    }

    @Override
    public boolean isEmpty() {
        return checkEmpty();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {

        if (checkEmpty()) {
            return null;
        }
        T temp = arr[Math.floorMod(front, capacity)];
        arr[Math.floorMod(front, capacity)] = null;
        size--;
        front++;
        if (downFlag()) {
            resizeDown();
        }
        return temp;

    }

    @Override
    public T removeLast() {
        if (checkEmpty()) {
            return null;
        }
        T temp = arr[Math.floorMod(back, capacity)];
        arr[Math.floorMod(back, capacity)] = null;
        size--;
        back--;
        if (downFlag()) {
            resizeDown();
        }
        return temp;

    }

    @Override
    public T get(int index) {
        if (checkEmpty() || index < 0 || index > size - 1) {
            return null;
        }
        return arr[Math.floorMod(index + front, capacity)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
