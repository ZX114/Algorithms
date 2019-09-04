import java.io.BufferedReader;
import java.io.IOException;

public class Digraph {
    private int V;
    private int E;
    private ListBag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (ListBag<Integer>[]) new ListBag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ListBag<>();
    }
    public Digraph(BufferedReader br) throws IOException {
        this(Integer.parseInt(br.readLine()));
        int E = Integer.parseInt(br.readLine());
        String s;
        while ((s = br.readLine()) != null) {
            int p = Integer.parseInt(s.split("\\s+")[0]);
            int q = Integer.parseInt(s.split("\\s+")[1]);
            this.addEdge(p, q);
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) { return adj[v]; }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj[v])
                R.addEdge(w, v);
        return R;
    }

    public int V() { return V; }
    public int E() { return E; }
}
