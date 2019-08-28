import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MSD {
    private static int R = 256;
    private static final int M = 0;
    private static String[] aux;
    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    /**
     * Rearranges the array of extended ASCII strings in ascending order,
     * Most-Significant-Digit First sort.
     *
     * @param a the array
     */
    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    /**
     * Sort string array {@code a} from {@code lo} to {@code hi},
     * starting at the dth character.
     *
     * @param a the string array
     * @param lo the lowest index
     * @param hi the highest index
     * @param d the starting index
     */
    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi - lo <= M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }

        int[] count = new int[R+2];
        // 1-frequency counts
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }

        // 2-cumulative counts
        for (int i = 0; i < R+1; i++) {
            count[i+1] += count[i];
        }

        // 3-group
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        // 4-copy back
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];  // aux start from 0
        }

        // 5-sort in each group
        for (int i = 0; i < R; i++) {
            if (count[i] < count[i+1] - 1) {
                sort(a, lo + count[i], lo + count[i+1] - 1, d+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // length of the array
        String[] a = new String[N];  // array of strings
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine();
        }
        MSD.sort(a);
        for (int i = 0; i < N; i++) {
            System.out.print(i);
            System.out.print(" " + a[i] + "\n");
        }
    }
}
