import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DepthFirstPaths {
    private boolean[] marked;  // marked[v]: s and v are connected
    private int[] edgeTo;  // edgeTo[v]: last edge on s-v path
    private final int s;  // the source vertex

    /**
     * Computes paths between {@code s} and other vertices in this graph.
     *
     * @param g the graph
     * @param s the source vertex
     */
    public DepthFirstPaths(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
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
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
                edgeTo[w] = v;
            }
        }
    }

    /**
     * Is there a path from {@code s} to vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if the path exists, {@code false} otherwise
     */
    public boolean hasPathTo(int v) { return  marked[v]; }

    /**
     * Returns a path between {@code s} and vertex {@code v},
     * null if no such path.
     *
     * @param v the vertex
     * @return a sequence representation of the
     *         path between {@code s} and vertex {@code v}
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        ListStack<Integer> stack = new ListStack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tinyCG.txt"));
        Graph g = new Graph(br);
        int s = 0;
        DepthFirstPaths search = new DepthFirstPaths(g, s);

        for (int v=0; v<g.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            for (int x : search.pathTo(v)) {
                if (x == s) System.out.print(x);
                else System.out.print("-" + x);
            }
            System.out.println();
        }

    }
}
