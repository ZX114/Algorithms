public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAP = 5000;
    private int N;  // number of key-value pairs
    private int M;  // size of the linear probing table
    private Key[] keys;
    private Value[] values;

    /**
     * Initialization with the given capacity
     */
    public LinearProbingHashST() { this(INIT_CAP); }
    public LinearProbingHashST(int CAP) {
        this.M = CAP;
        this.N = 0;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    /**
     * @return the number of key-value pairs
     */
    public int size() { return N; }
    public boolean isEmpty() { return size() == 0; }
    public boolean contains(Key k) { return get(k) != null; }

    /**
     * @param k the key
     * @return hash value between 0 and M-1
     */
    private int hash(Key k) { return (k.hashCode() & 0x7fffffff) % M; }

    /**
     * Resize the hash table to the given capacity
     * Re-hashing all of the keys
     *
     * @param CAP the updated capacity
     */
    private void resize(int CAP) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(CAP);
        for (int i=0; i<this.M; i++) {
            if (keys[i] != null) temp.put(keys[i], values[i]);
        }
        keys = temp.keys;
        values = temp.values;
        this.M = temp.M;
    }

    /**
     * Inserts the specified key-value pair
     *
     * @param k the key
     * @param v the value
     */
    public void put(Key k, Value v) {
        // double table size if 50% full
        if (N > M/2) resize(2*M);

        int i = hash(k);
        while (keys[i] != null) {
            if (keys[i].equals(k)) { values[i] = v; return; }
            i = (i + 1) % M;
        }
        keys[i] = k;
        values[i] = v;
        N++;
    }

    /**
     * @param k the key
     * @return the value associated with the specified key
     */
    public Value get(Key k) {
        int i = hash(k);
        while (keys[i] != null) {
            if (keys[i].equals(k)) { return values[i]; }
            i = (i + 1) % M;
        }
        return null;
    }

    public Iterable<Key> keys() {
        ListQueue<Key> queue = new ListQueue<>();
        for (int i=0; i<M; i++) {
            if (keys[i] != null) { queue.enqueue(keys[i]); }
        }
        return queue;
    }
}
