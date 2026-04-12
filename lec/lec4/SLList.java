public class SLList {

    private static class IntNode {

        public int item;
        public IntNode next;

        public IntNode(int f, IntNode r) {
            item = f;
            next = r;
        }
    }

    /* The first item, if it exists, is at sentinel.next */
    private IntNode sentinel;

    private int size;

    /* Creates a new SLList with one item, namely x */
    public SLList(int x) {
        sentinel = new IntNode(0, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /* Create an empty SLList */
    public SLList() {
        sentinel = new IntNode(0, null);
        size = 0;
    }

    /* Adds item x to the front of the list */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /* Gets the first item in the list */
    public int getFirst() {
        return sentinel.next.item;
    }

    /* Add x to the end of the list */
    public void addLast(int x) {
        size += 1;

        IntNode p = sentinel;

        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    /* Returns the size of the list */
    public int size() {
        return size;
        //return size(first);
    }

    // /* Returns the size of the list, starting at IntNode p */
    // private int size(IntNode p) {
    //     if  (p.next == null) {
    //         return 1;
    //     }
    //     return 1 + size(p.next);
    // }
}