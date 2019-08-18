import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
    private final int V;  // number of vertices
    private int E;  // number of edges
    private ListBag<Integer>[] adj;

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges
     *
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (ListBag<Integer>[]) new ListBag[V];
        for (int v=0; v<V; v++) { adj[v] = new ListBag<Integer>(); }
    }

    /**
     * @return the number of vertices in this graph
     */
    public int V() { return V; }

    /**
     * @return the number of edges in this graph
     */
    public int E() { return E; }

    /**
     * Adds an undirected edge v-w to this graph
     *
     * @param v one vertex
     * @param w the other vertex
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * @param v the vertex
     * @return the vertices adjcent to vertex {@code v}, as an iterable
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * @return a string representation of this graph
     */
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v=0; v<V; v++) {
            s += v + ":";
            for (int w : adj[v]) { s += " " + w; }
            s += "\n";
        }
        return s;
    }

    public int degree(int v) {
        return adj[v].size();
    }

    public int maxDegree() {
        int max = 0;
        for (int v=0; v<V; v++) {
            int degree = degree(v);
            if (degree > max) max = degree;
        }
        return max;
    }

    public double avgDegree() {
        return 2*E/V;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tinyG.txt"));
        int N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        Graph g = new Graph(N);
        String s;
        while ((s = br.readLine()) != null) {
            int p = Integer.parseInt(s.split("\\s+")[0]);
            int q = Integer.parseInt(s.split("\\s+")[1]);
            g.addEdge(p, q);
        }

        System.out.println(g);
    }
}
