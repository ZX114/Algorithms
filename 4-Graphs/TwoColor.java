import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TwoColor {
    private boolean[] marked;
    private boolean[] colors;
    private boolean isTwoColor;

    public TwoColor(Graph g) {
        marked = new boolean[g.V()];
        colors = new boolean[g.V()];
        colors[0] = true;
        for (int s = 0; s < g.V(); s++)
            if (!marked[s]) dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                colors[w] = !colors[v];
                dfs(g, w);
            } else if (colors[w] == colors[v]) {
                isTwoColor = false;
            }
        }
    }

    public boolean isTwoColor() {
        return isTwoColor;
    }

    public static void main(String[] args) throws IOException {
        Graph g = new Graph(new BufferedReader(new FileReader("tinyG.txt")));

        TwoColor tc = new TwoColor(g);
        if (tc.isTwoColor())
            System.out.println("The graph is bipartite");
        else
            System.out.println("This graph is not bipartite");
    }
}
