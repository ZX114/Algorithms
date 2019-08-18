import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        public Node(Key k, Value v) {
            this.key = k;
            this.value = v;
        }
    }

    /**
     * Get a value with a given key, return null if not found.
     *
     * @param k the key
     * @return the value
     */
    public Value get(Key k) { return get(root, k); }
    private Value get(Node x, Key k) {
        if (x==null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) return get(x.left, k);
        else if (cmp > 0) return get(x.right, k);
        else return x.value;
    }
    public void put(Key k, Value v) { root = put(root, k, v); }
    private Node put(Node x, Key k, Value v) {
        if (x == null) return new Node(k, v);
        int cmp = k.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, k, v);
        else if (cmp > 0) x.right = put(x.right, k, v);
        else x.value = v;
        return x;
    }

    /**
     * @return the smallest value
     */
    public Key min() {
        Key k = null;
        Node x = root;
        while (x != null) {
            k = x.key;
            x = x.left;
        }
        return k;
    }
    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }
    public boolean contains(Key k) { return get(k) != null; }
    public Key floor(Key k) {
        Node x = floor(root, k);
        if (x == null) return null;
        else return x.key;
    }
    private Node floor(Node x, Key k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp < 0) return floor(x.left, k);
        else {
            Node t = floor(x.right, k);
            if (t != null) return t;
            else return x;
        }
    }

    /**
     * Delete the minimum element.
     */
    public void deleteMin() { root = deleteMin(root); }
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    public void delete(Key k) { root = delete(root, k); }
    private Node delete(Node x, Key k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, k);
        else if (cmp > 0) x.right = delete(x.right, k);
        else {
            if (x.right == null) return x.left;
            else if (x.left == null) return x.right;
            else {
                Node t = x;
                x = min(t.right);
                x.right = deleteMin(x.right);
                x.left = t.left;
            }
        }
        return x;
    }

    /**
     * Enable foreach iterations.
     *
     * @return a ListQueue for iteration
     */
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        BST<String, Integer> st = new BST<String, Integer>();
        while ((s = br.readLine()) != null) {
            if (!st.contains(s)) st.put(s, 1);
            else st.put(s, st.get(s)+1);
        }
        st.deleteMin();
        st.delete(st.min());
        System.out.println(st.min());
    }
}
