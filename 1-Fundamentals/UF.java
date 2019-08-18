import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UF {
    private int[] id;
    private int[] sz;
    private int count;
    public UF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i=0; i<N; i++) { id[i] = i; sz[i] = 1; }
    }
    public int count() { return count; }
    public boolean connected(int p, int q) { return find(p) == find(q); }

    // union_find
    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if(sz[i] > sz[j]) { id[j] = i; sz[i] += sz[j];}
        else { id[i] = j; sz[j] += sz[i]; }
        count--;
    }

//    // quick_union
//    public int find(int p) {
//        while (p != id[p]) p = id[p];
//        return p;
//    }
//    public void union(int p, int q) {
//        id[find(p)] = find(q);
//        count--;
//    }

//    // quick_find
//    public int find(int p) { return id[p]; }
//    public void union(int p, int q) {
//        int pid = id[p];
//        for (int i=0; i<id.length; i++)
//            if (id[i] ==  pid) id[i] = id[q];
//        count--;
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("largeUF.txt"));
        int N = Integer.parseInt(br.readLine());
        UF uf = new UF(N);
        String s;
        while ((s = br.readLine()) != null) {
            int p = Integer.parseInt(s.split("\\s+")[0]);
            int q = Integer.parseInt(s.split("\\s+")[1]);
            if (uf.connected(p, q)) continue;
            else {
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
        }
        System.out.println(uf.count() + "components");
    }
}
