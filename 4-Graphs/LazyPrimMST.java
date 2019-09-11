import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LazyPrimMST {
    private boolean[] marked;
    private ListQueue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph g) {
        marked = new boolean[g.V()];
        mst = new ListQueue<>();
        pq = new MinPQ<>();

        visit(g, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;  // 失效的边

            mst.enqueue(e);
            if (marked[v]) visit(g, w);
            else visit(g, v);
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double w = 0;
        for (Edge e : mst)
            w += e.weight();
        return w;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tinyEWG.txt"));
        EdgeWeightedGraph g = new EdgeWeightedGraph(br);

        LazyPrimMST mst = new LazyPrimMST(g);
        for (Edge e : mst.edges())
            System.out.println(e);
        System.out.println("Total weight: " + mst.weight());
    }
}
