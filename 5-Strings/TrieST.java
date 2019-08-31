import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class TrieST<Value> {
    private static int R = 256;
    private int N;
    private Node root;

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        else return (Value) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;

        if (d == key.length()) return x;
        else {
            int i = key.charAt(d);
            return get(x.next[i], key, d + 1);
        }
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) { x = new Node(); }

        if (d == key.length()) {
            if (x.value == null) N++;
            x.value = val;
            return x;
        } else {
            int i = key.charAt(d);
            x.next[i] = put(x.next[i], key, val, d + 1);
            return x;
        }
    }

    public int size() { return N; }

    public boolean isEmpty() { return size() == 0; }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;

        if (d == key.length()) {
            if (x.value != null) N--;
            x.value = null;
        }
        else {
            int i = key.charAt(d);
            x.next[i] = delete(x.next[i], key, d+1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.value != null) return x;
        for (int i = 0; i < R; i++)
            if (x.next[i] != null)
                return x;
        return null;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        ListQueue<String> q = new ListQueue<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node x, String pre, ListQueue<String> q) {
        if (x == null) return;
        if (x.value != null) q.enqueue(pre);
        for (char i = 0; i < R; i++) {
            collect(x.next[i], pre + i, q);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        TrieST<Integer> st = new TrieST<>();
        while ((s = br.readLine()) != null) {
            if (!st.contains(s)) st.put(s, 1);
            else st.put(s, st.get(s)+1);
        }

        st.delete("String");
        System.out.println(st.get("string"));
        System.out.println(st.size());
    }
}
