public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;  // from pq[1] ... pq[N]
    public void MaxPQ(int max) { pq = (Key[]) new Comparable[max+1]; }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }
    public Key delMax() {
        Key t = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return t;
    }
    private boolean less(int i, int j) { return pq[i].compareTo(pq[j]) < 0; }
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    private void swim(int k) {
        while (k > 1&& less(k/2, k)) {
            exch(k/2, k);
            k /= 2;
        }
    }
    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }
}
