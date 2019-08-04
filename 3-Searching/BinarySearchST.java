public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;
    public BinarySearchST(int cap) {
        keys = (Key[]) new Comparable[cap];
        values = (Value[]) new Object[cap];
    }
    public int size() { return N; }

    /**
     * Returns the value associated with the given key in this symbol table.
     *
     * @param k the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     */
    public Value get(Key k) {
        if (isEmpty()) return null;
        int i = rank(k);
        if (i < N && keys[i].compareTo(k) == 0) return values[i];
        else return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     *
     * @param k the key
     * @param v the value
     */
    public void put(Key k, Value v) {
        int i = rank(k);
        if (i < N && keys[i].compareTo(k) == 0) { values[i] = v; return; }
        for (int j=N; j>i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = k;
        values[i] = v;
        N++;
    }

    public boolean isEmpty() { return N == 0; }

    /**
     * Returns the number of keys in this symbol table strictly less than {@code k}.
     *
     * @param k the key
     * @return the number of keys in the symbol table strictly less than {@code k}
     */
    private int rank(Key k) {
        int lo = 0, hi = N-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            int cmp = keys[mid].compareTo(k);
            if (cmp < 0) lo = mid+1;
            else if (cmp > 0) hi = mid-1;
            else return mid;
        }
        return lo;
    }

    public Iterable<Key> keys() {
        ListQueue<Key> queue = new ListQueue<>();
        for (int i=0; i<N; i++) { queue.enqueue(keys[i]); }
        return queue;
    }

    public boolean contains(Key k) { return get(k) != null; }
}
