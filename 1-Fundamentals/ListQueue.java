import java.util.Iterator;

public class ListQueue<Item> implements Iterable<Item> {
    // FIFO
    private Node first;
    private Node last;
    private int N;
    private class Node {
        Item item;
        Node next;
    }
    public boolean isEmpty() { return first == null; }
    public int size() { return N; }

    public void enqueue(Item it) {
        Node temp = last;
        last = new Node();
        last.item = it;
        last.next = null;
        if (isEmpty()) first = last;
        else temp.next = last;
        N++;
    }
    public Item dequeue() {
        Item it = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return it;
    }

    public Iterator<Item> iterator() { return new ListIterator(); }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current!=null; }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {}
    }
}
