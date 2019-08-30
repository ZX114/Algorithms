import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quick3string {
    private static final int M = 10;  // switch to insertion sort
    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }
    /**
     * Rearranges the array of strings in ascending order,
     * Quick3way sort.
     *
     * @param a the array
     */
    public static void sort(String[] a) {
        sort(a, 0, a.length-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi - lo <= M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }

        int f = charAt(a[lo], d);
        int lt = lo, i = lo + 1, gt = hi;
        while (i <= gt) {
            int cmp = charAt(a[i], d) - f;
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, gt--, i);
            else i++;
        }
        sort(a, lo, lt-1, d);
        if (f >= 0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }

    private static void exch(String[] a, int i, int j) {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // is the array sorted
    private static boolean isSorted(String[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(a[i-1]) < 0) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // length of the array
        String[] a = new String[N];  // array of strings
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine();
        }
        Quick3string.sort(a);
        if (!isSorted(a)) System.out.print("Not ");
        System.out.println("Sorted");
    }
}
