import java.util.Iterator;

public class MinPQ<Key extends Comparable<Key>> implements Iterable<Key> {
    private Key[] pq;
    private int N = 0;
    private int cap;

    public MinPQ(int cap) {
        this.cap = cap;
        pq = (Key[]) new Comparable[cap];
    }

    public boolean isEmpty() { return N == 0; }

    public int size() { return N; }

    public Key min() {
        if (isEmpty())
            throw new RuntimeException("Empty priority queue");
        return pq[1];
    }

    public void insert(Key k) {
        if (N + 1 == cap) resize(2*cap);
        pq[++N] = k;
        swim(N);
    }

    public Key delMin() {
        if (isEmpty())
            throw new RuntimeException("Empty priority queue");
        Key min = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return min;
    }

    private void resize(int newCap) {
        Key[] temp = (Key[]) new Comparable[newCap];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean less(int i, int j) { return pq[i].compareTo(pq[j]) < 0; }

    private void swim(int k) {
        while (k > 1 && less(k, k/2)) {
            exch(k, k/2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j+1, j)) j++;
            if (!less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        MinPQ mpq = new MinPQ<Integer>(2);
        for (int i = 10; i > 0; i--)
            mpq.insert(i);
        for (int i = 0; i < 10; i++)
            System.out.println(mpq.delMin());
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MinPQ<Key> copy;

        public HeapIterator() {
            copy = new MinPQ<>(size());
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }
        public Key next() {
            return copy.delMin();
        }

    }
}
