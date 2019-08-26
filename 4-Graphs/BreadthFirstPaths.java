import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g, s);
    }

    /**
     * Breadth first search from v.
     * @param g the graph
     * @param v the vertex
     */
    private void bfs(Graph g, int v) {
        ListQueue<Integer> q = new ListQueue<Integer>();
        marked[v] = true;
        q.enqueue(v);
        while (!q.isEmpty()) {
            int w = q.dequeue();
            for (int i : g.adj(w)) {
                if (!marked[i]) {
                    marked[i] = true;
                    edgeTo[i] = w;
                    q.enqueue(i);
                }
            }
        }
    }

    /**
     * Is there a path from {@code s} to vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if the path exists, {@code false} otherwise
     */
    public boolean hasPathTo(int v) { return marked[v]; }

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
        BreadthFirstPaths search = new BreadthFirstPaths(g, s);

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
