import java.util.Iterator;

public class ResizingStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i=0; i<N; i++) temp[i] = a[i];
        a = temp;
    }

    public boolean isEmpty() { return N == 0; }

    public int size() { return N; }

    public void push(Item it) {
        if (N == a.length) resize(2*a.length);
        a[N++] = it;
    }

    public Item pop() {
        Item it = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return it;
    }

    public Iterator<Item> iterator() { return new ReverseArrayIterator(); }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        public boolean hasNext() { return i > 0; }
        public Item next() { return a[--i]; }
        public void remove() {}
    }
    public ResizingStack(int cap){
        a = (Item[]) new Object[cap];
        N = 0;
    }

    public ResizingStack() {
        this(10);
    }
}
