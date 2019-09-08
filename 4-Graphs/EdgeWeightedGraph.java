import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EdgeWeightedGraph {
    private final int V;  // number of vertices
    private int E;  // number of edges
    private ListBag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (ListBag<Edge>[]) new ListBag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ListBag<>();
    }

    public EdgeWeightedGraph(BufferedReader br) throws IOException {
        this(Integer.parseInt(br.readLine().split("\\s+")[0]));
        int E = Integer.parseInt(br.readLine());
        String s;
        while ((s = br.readLine()) != null) {
            String[] t = s.split("\\s+");
            int v = Integer.parseInt(t[0]);
            int w = Integer.parseInt(t[1]);
            double wt = Double.parseDouble(t[2]);
            addEdge(new Edge(v, w, wt));
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) { return adj[v]; }

    public Iterable<Edge> edges() {
        ListBag<Edge> b = new ListBag<>();
        for (int x = 0; x < V; x++) {
            for (Edge e : adj(x))
                if (e.other(x) > x) b.add(e);
        }
        return b;
    }

    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ":";
            for (Edge e : adj(v))
                s += "  " + e;
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tinyEWG.txt"));
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(br);
        System.out.println(ewg);
    }
}
