public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    private ListStack<Integer> cycle;

    public DirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        for (int s = 0; s < g.V(); s++)
            if (!marked[s]) dfs(g, s);
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : g.adj(v)) {
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
            else if (onStack[w]) {
                cycle = new ListStack<>();
                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
            }
        }
        onStack[v] = false;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasCycle() { return cycle != null; }
}
