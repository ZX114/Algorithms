import java.io.IOException;

public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph g) {
        DirectedCycle dc = new DirectedCycle(g);
        if (!dc.hasCycle()) {
            DepthFirstOrder dfo = new DepthFirstOrder(g);
            order = dfo.reversePost();
        }
    }

    public Iterable<Integer> order() { return order; }
    public boolean isDAG() { return order != null; }

    public static void main(String[] args) throws IOException {
        SymbolDigraph sg = new SymbolDigraph("jobs.txt", "/");

        Topological top = new Topological(sg.graph());
        for (int v : top.order())
            System.out.println(sg.name(v));
    }
}
