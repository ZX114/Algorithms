public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_SIZE = 1000;
    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() { this(INIT_SIZE); }
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i=0; i<M; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }

    /**
     * @param k the key
     * @return hash value between 0 and M-1
     */
    private int hash(Key k) { return (k.hashCode() & 0x7fffffff) % this.M; }

    /**
     * @param k the key
     * @return the value associated with {@code k} in the symbol table;
     *         {@code null} if no such value
     */
    public Value get(Key k) { return st[hash(k)].get(k); }

    public boolean contains(Key k) { return get(k) != null; }

    /**
     * Inserts the specified key-value pair
     * Double M if average length of list >= 10
     * @param k the key
     * @param v the value
     */
    public void put(Key k, Value v) {
        if (N > 10*M) resize(2*M);
        int i = hash(k);
        if (!st[i].contains(k)) N++;
        st[i].put(k, v);
    }

    private void resize(int Mnew) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(Mnew);
        for (Key k : this.keys()) temp.put(k, this.get(k));
//        for (int i=0; i<this.M; i++) {
//            for (Key k : st[i].keys()) temp.put(k, st[i].get(k));
//        }

        this.N = temp.N;
        this.M = temp.M;
        this.st = temp.st;
    }

    /**
     * @return number of chains
     */
    public int size() { return this.M; }

    /**
     * @return a ListQueue for iterations
     */
    public Iterable<Key> keys() {
        ListQueue<Key> queue = new ListQueue<Key>();
        for (int i=0; i<M; i++) {
            for (Key k : st[i].keys()) queue.enqueue(k);
        }
        return queue;
    }

}
