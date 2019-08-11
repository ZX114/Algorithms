public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int N;
        boolean color;

        public Node(Key k, Value v, int N, boolean c) {
            this.key = k;
            this.value = v;
            this.N = N;
            this.color = c;
        }
    }
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

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
    public int size() { return size(root); }
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
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


}
