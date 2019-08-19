public class DepthFirstSearch {
    private boolean[] marked;  // marked[v]: s and v are connected or not
    private int count;  // count the number of vertices connected to s

    /**
     * Search in the graph to mark all the vertices
     * that are connected with s.
     *
     * @param g the graph
     * @param s the source vertex
     */
    public DepthFirstSearch(Graph g, int s) {
        marked = new boolean[g.V()];
        count = 0;
        dfs(g, s);
    }

    /**
     * Depth first search from v.
     *
     * @param g the graph
     * @param v the vertex
     */
    private void dfs(Graph g, int v) {
        marked[v] = true;
        count++;
        for (int w : g.adj(v)) {
            if (!marked[w]) dfs(g, w);
        }
    }

    // access to private data
    boolean marked(int v) {return marked[v];}
    int count() {return count;}
}
