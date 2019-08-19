import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tinyG.txt"));
        Graph g = new Graph(br);

        DepthFirstSearch search = new DepthFirstSearch(g, 9);

        for (int v=0; v<g.V(); v++) {
            if (search.marked(v)) System.out.print(v+" ");
        }
        System.out.println();

        if (search.count()!=g.V()) System.out.print("Not ");
        System.out.println("Connected");
    }
}
