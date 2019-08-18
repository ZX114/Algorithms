public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int N;
        private boolean color;

        public Node(Key k, Value v, int N, boolean c) {
            this.key = k;
            this.value = v;
            this.N = N;
            this.color = c;
        }
    }


    /*************************************************************************
     *  Helper methods.
     *************************************************************************/
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
    public int size() { return size(root); }
    public boolean isEmpty() { return root == null; }
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }


    /*************************************************************************
     *  Insertions.
     *************************************************************************/

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting
     * the old value.
     *
     * @param k the key
     * @param v the value
     */
    public void put(Key k, Value v) { root = put(root, k, v); root.color=BLACK; }
    private Node put(Node h, Key k, Value v) {
        if (h == null) return new Node(k, v, 1, RED);

        int cmp = k.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, k, v);
        else if (cmp > 0) h.right = put(h.right, k, v);
        else h.value = v;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


    /*************************************************************************
     *  Helper functions.
     *************************************************************************/
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /*************************************************************************
     *  BST search.
     *************************************************************************/
    public Value get(Key k) { return get(root, k); }
    private Value get(Node x, Key k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) return get(x.left, k);
        else if (cmp > 0) return get(x.right, k);
        else return x.value;
    }
    public boolean contains(Key k) { return get(k) != null; }

    /************************************************************************
     * Range count.
     ************************************************************************/
    public Iterable<Key> keys() {
        ListQueue<Key> queue = new ListQueue<Key>();
        keys(root, queue);
        return queue;
    }
    private void keys(Node x, ListQueue<Key> queue) {
        if (x == null) return;
        keys(x.left, queue);
        queue.enqueue(x.key);
        keys(x.right, queue);
    }

}
