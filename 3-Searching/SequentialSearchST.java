public class SequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node first;
    private class Node {
        Key key;
        Value value;
        Node next;
        public Node(Key k, Value v, Node n) {
            this.key = k;
            this.value = v;
            this.next = n;
        }
    }

    public SequentialSearchST() { }

    public void put(Key k, Value v) {
        for (Node i=first; i!=null; i=i.next)
            if (k.equals(i.key)) { i.value = v; return; }

        Node oldFirst = first;
        first = new Node(k, v, oldFirst);
    }

    public Value get(Key k) {
        for (Node i=first; i!=null; i=i.next)
            if (k.equals(i.key)) return i.value;
        return null;
    }

    public boolean contains(Key k) {
        return get(k) != null;
    }

    public Iterable<Key> keys() {
        ListQueue<Key> queue = new ListQueue<Key>();
        for (Node i=first; i!=null; i=i.next) {
            queue.enqueue(i.key);
        }
        return queue;
    }
}
