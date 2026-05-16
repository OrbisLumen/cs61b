package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author OrbisLumen
 */

public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Invariable */
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    /* Instance Variables */
    private int size;
    private int capacity;
    private double factor;
    private Collection<Node>[] buckets;

    /** Constructors */
    public MyHashMap() {
        this(INITIAL_CAPACITY, LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        capacity = initialCapacity;
        factor = loadFactor;

        buckets = new Collection[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    @Override
    public void put(K key, V value) {
        int index = Math.floorMod(key.hashCode(), buckets.length);

        for (Node n : buckets[index]) {
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }

        if ((size + 1.0) / buckets.length > factor) {
            resize();
            index = Math.floorMod(key.hashCode(), buckets.length);
        }

        buckets[index].add(new Node(key, value));
        size++;
    }

    @Override
    public V get(K key) {
        for(Node n : buckets[Math.floorMod(key.hashCode(), buckets.length)]) {
            if(n.key.equals(key)){
                return n.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for(Node n : buckets[Math.floorMod(key.hashCode(), buckets.length)]) {
            if(n.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;

        for(int i = 0; i < buckets.length; i++){
            buckets[i] = createBucket();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        for (int i = 0; i < buckets.length; i++) {
            for (Node n : buckets[i]) {
                keys.add(n.key);
            }
        }

        return keys;
    }

    @Override
    public V remove(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);

        Node removeNode = null;

        for(Node n : buckets[index]) {
            if(n.key.equals(key)) {
                removeNode = n;
                break;
            }
        }

        if (removeNode == null) {
            return null;
        }

        buckets[index].remove(removeNode);
        size--;
        return removeNode.value;

    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    private void resize() {
        capacity *= 2;
        Collection<Node>[] newBuckets = new Collection[capacity];

        for(int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = createBucket();
        }

        for(int i = 0; i < buckets.length; i++) {
            for(Node n : buckets[i]) {
                newBuckets[Math.floorMod(n.key.hashCode(), newBuckets.length)].add(n);
            }
        }

        buckets = newBuckets;
    }

}
