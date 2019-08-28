import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LSD {
    private static int R = 256;
    /**
     * Least-Significant-Digit First sort.
     *
     * @param a the array of string to be sorted
     * @param W the number of characters per string
     */
    public static void sort(String[] a, int W) {
        int N = a.length;
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--) {
            int[] count = new int[R+1];
            // frequency counts
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d)+1]++;
            }

            // cumulative counts
            for (int i = 0; i < R; i++) {
                count[i+1] += count[i];
            }

            // move to aux
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // length of the array
        int W = Integer.parseInt(br.readLine());  // width of each string
        String[] a = new String[N];  // array of strings
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine();
        }
        LSD.sort(a, W);
        for (int i = 0; i < N; i++) {
            System.out.print(i);
            System.out.print(" " + a[i] + "\n");
        }
    }
}
