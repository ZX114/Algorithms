import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DegreesOfSeparation {
    public static void main(String[] args) throws IOException {
        SymbolGraph sg = new SymbolGraph("movies.txt", "/");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Source: ");
        String s = br.readLine();
        if (!sg.contains(s)) { System.out.println("Not in database"); return; }
        int source = sg.index(s);
        BreadthFirstPaths search = new BreadthFirstPaths(sg.graph(), source);
        System.out.print("Query: ");
        while ((s = br.readLine()) != null) {
            if (sg.contains(s)) {
                int dest = sg.index(s);
                if (search.hasPathTo(dest)) {
                    for (int w : search.pathTo(dest))
                        System.out.println("\t" + sg.name(w));
                } else System.out.println("Not connected");
            } else System.out.println("Not in database");
            System.out.print("Query: ");
        }
    }
}
