import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    public DirectedDFS(Digraph g, Iterable<Integer> sources) {
        marked = new boolean[g.V()];
        for (int s : sources)
            if (!marked[s]) dfs(g, s);
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v))
            if (!marked[w]) dfs(g, w);
    }

    public boolean marked(int v) { return marked[v]; }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tinyG.txt"));
        Digraph g = new Digraph(br);
        ListBag<Integer> sources = new ListBag<>();
        sources.add(0);
        sources.add(9);
        DirectedDFS r = new DirectedDFS(g, sources);

        for (int v = 0; v < g.V(); v++)
            if (r.marked(v)) System.out.print(v + " ");
        System.out.println();
    }
}
