import java.util.Iterator;

public class ListStack<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    private class Node {
        Item item;
        Node next;
    }
    public boolean isEmpty() { return first == null; }
    public int size() { return N; }
    public void push(Item it) {
        Node temp = first;
        first = new Node();
        first.item = it;
        first.next = temp;
        N++;
    }
    public Item pop() {
        Item it =  first.item;
        first = first.next;
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
