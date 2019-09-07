import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SymbolDigraph {
    private RedBlackBST<String, Integer> st;  // string -> index
    private String[] keys;  // index -> string
    private Digraph g;  // underlying graph

    public SymbolDigraph(String filename, String delim) throws IOException {
        st = new RedBlackBST<>();
        // 第一遍：得到索引和符号名的关联表，图的顶点数目
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s;
        while ((s = br.readLine()) != null) {
            String[] t = s.split(delim);
            for (int i = 0; i < t.length; i++)
                if (!st.contains(t[i])) st.put(t[i], st.size());
        }
        keys = new String[st.size()];
        for (String name : st.keys())
            keys[st.get(name)] = name;
        g = new Digraph(st.size());
        // 第二遍：构造图并连接顶点
        br = new BufferedReader(new FileReader(filename));
        while ((s = br.readLine()) != null) {
            String[] t = s.split(delim);
            int v = st.get(t[0]);
            for (int i = 1; i < t.length; i++)
                g.addEdge(v, st.get(t[i]));
        }
    }

    public int index(String key) { return st.get(key); }

    public String name(int v) { return keys[v]; }

    public boolean contains(String key) { return st.contains(key); }

    public Digraph graph() { return g; }
}
