package deque;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>{

    private class Node {

        public T item;
        public Node next;
        public Node fron;

        public Node(T item, Node fron, Node next) {
            this.item = item;
            this.fron = fron;
            this.next = next;
        }

    }

    private Node sentinel;

    private int size;

    public LinkedListDeque61B() {

        this.sentinel = new Node(null, null, null);
        this.sentinel.fron = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;

    }

    @Override
    public void addFirst(T x) {

        Node added = new Node(x, sentinel, sentinel.next);
        sentinel.next.fron = added;
        sentinel.next = added;
        this.size++;

    }

    @Override
    public void addLast(T x) {

        Node added = new Node(x, sentinel.fron, sentinel);
        sentinel.fron.next = added;
        sentinel.fron = added;
        this.size++;

    }

    @Override
    public List<T> toList() {
        Node cur = sentinel.next;
        List<T> checkList = new ArrayList<>();
        while(cur != sentinel) {
            checkList.add(cur.item);
            cur = cur.next;
        }
        return checkList;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.next.item;
        Node next = sentinel.next.next;
        sentinel.next.next.fron = sentinel;
        sentinel.next.next =null;
        sentinel.next.fron= null;
        sentinel.next = next;
        size --;
        return item;

    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.fron.item;
        Node fron = sentinel.fron.fron;
        sentinel.fron.fron.next = sentinel;
        sentinel.fron.fron = null;
        sentinel.fron.next = null;
        sentinel.fron = fron;
        size --;
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node cur = sentinel;
        while (index != 0) {
            cur = cur.next;
            index --;
        }
        return cur.next.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size - 1){
            return null;
        }
        Node cur = sentinel;
        return getRecursiveInner(cur, index);
    }

    private T getRecursiveInner(Node cur, int index) {
        if (index == 0) {
            return cur.next.item;
        }
        return getRecursiveInner(cur.next, index - 1);
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private int index;
        private Node cur = sentinel;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            cur = cur.next;
            index++;
            return cur.item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedListDeque61B<?> other)) {
            return false;
        }
        if (this.size != other.size()) {
            return false;
        }
        for (int i = 0; i< size; i++) {
            if (!java.util.Objects.equals(this.get(i), other.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return toList().toString();
    }

    public static void main(String[] args) {
        // Test toString overriding
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        System.out.println(lld1);
    }
}
