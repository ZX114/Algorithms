public class CC {
    private boolean[] marked;
    private int[] id;  // id[v] = id of connected component containing v
    private int count;  // number of connected components

    /**
     * Computes the connected components of the undirected graph.
     * @param g the graph
     */
    public CC(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }

    /**
     * Depth first search in graph {@code g} from vertex {@code v}.
     * @param g the graph
     * @param v the vertex
     */
    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    /**
     * Whether vertices two vertices are connected or not.
     *
     * @param v one vertex
     * @param w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in
     *         the same connected component; {@code false} otherwise
     */
    public boolean connected(int v, int w) { return id[v] == id[w]; }

    public int id(int v) { return id[v]; }

    public int count() { return count; }
}
