public class DepthFirstOrder {
    private boolean[] marked;
    private ListQueue<Integer> pre;
    private ListQueue<Integer> post;
    private ListStack<Integer> reversePost;

    public DepthFirstOrder(Digraph g) {
        marked = new boolean[g.V()];
        pre = new ListQueue<>();
        post = new ListQueue<>();
        reversePost = new ListStack<>();
        for (int s = 0; s < g.V(); s++)
            if (!marked[s]) dfs(g, s);
    }

    private void dfs(Digraph g, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : g.adj(v))
            if (!marked[w]) dfs(g, w);
        post.enqueue(v);
        reversePost.push(v);
    }
    public Iterable<Integer> pre() { return pre; }
    public Iterable<Integer> post() { return post; }
    public Iterable<Integer> reversePost() { return reversePost; }
}
