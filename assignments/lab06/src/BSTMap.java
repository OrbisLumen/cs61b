import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K k, V v) {
            key = k;
            value = v;
        }

    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    @Override
    public V get(K key) {
        BSTNode node = getNode(root, key);
        if (node == null) {
            return null;
        } else {
            return node.value;
        }
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode node = getNode(root, key);
        if (node == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<>();
        keyCollect(s, root);
        return s;
    }

    @Override
    public V remove(K key) {
        BSTNode node = getNode(root, key);
        if (node == null) {
            return null;
        }

        V value = node.value;
        root = remove(root, key);
        size--;
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        List<K> list = new ArrayList<>();
        buildList(root, list);
        return list.iterator();
    }

    /**
     * Put helper, to put one map recursively.
     */
    private BSTNode put(BSTNode node, K k, V v) {

        if (node == null) {
            size++;
            return new BSTNode(k, v);
        }

        int cmp = k.compareTo(node.key);

        if (cmp < 0) {
            node.left = put(node.left, k, v);
        } else if (cmp > 0) {
            node.right = put(node.right, k, v);
        } else {
            node.value = v;
        }

        return node;
    }

    /**
     * Get one map node recursively by Key.
     * If not exists, return null.
     */
    private BSTNode getNode(BSTNode node, K k) {
        if (node == null) {
            return null;
        }

        int cmp = k.compareTo(node.key);

        if (cmp < 0) {
            return getNode(node.left, k);
        } else if (cmp > 0) {
            return getNode(node.right, k);
        } else {
            return node;
        }
    }

    /**
     * Collect all the Key in a built-in set.
     */
    private void keyCollect(Set<K> s, BSTNode node) {
        if (node != null) {
            keyCollect(s, node.left);
            s.add(node.key);
            keyCollect(s, node.right);
        }
    }

    /**
     * Remove helper, recursively remove the target from the BSTMap and return the root.
     */
    private BSTNode remove(BSTNode node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        }
        else if (cmp > 0) {
            node.right = remove(node.right, key);
        }
        else {
            // FOUND node to delete

            // case 1: no left child
            if (node.left == null) return node.right;

            // case 2: no right child
            if (node.right == null) return node.left;

            // case 3: two child
            BSTNode successor = getMin(node.right);

            successor.right = removeMin(node.right);
            successor.left = node.left;
            return successor;
        }

        return node;
    }

    /**
     * Remove helper's method, to get successor. (The most left child in the remove node's right)
     */
    private BSTNode getMin(BSTNode node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    /**
     * Remove helper's method, to remove the successor from the returned BSTMap.
     */
    private BSTNode removeMin(BSTNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * Iterator helper, return a list which stores the key's value in order.
     */
    private void buildList(BSTNode node, List<K> list) {
        if (node == null) return;

        buildList(node.left, list);
        list.add(node.key);
        buildList(node.right, list);
    }

}