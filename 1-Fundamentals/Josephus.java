import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Josephus {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ListQueue<Integer> q = new ListQueue<>();
        for (int i=0; i<N; i++) { q.enqueue(i); }
        while (!q.isEmpty()) {
            // 将幸存下来的人放到队尾
            for (int i=1; i<M; i++) {
                q.enqueue(q.dequeue());
            }
            System.out.println(q.dequeue());
        }
    }
}
