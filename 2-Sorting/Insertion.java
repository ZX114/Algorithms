import java.util.Random;

public class Insertion {
    /**
     * Insertion sort the Comparable array a, in ascending order.
     *
     * @param a the Comparable array
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int j = i;
            while (j > 0 && less(a[j], a[j-1])) {
                exch(a, j, j - 1);
                j--;
            }
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable p, Comparable q) { return p.compareTo(q) < 0; }

    public static void show(Comparable[] a) {
        for (Comparable i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    /**
     * insertion sort a[lo..hi], starting at dth character.
     *
     * @param a the array
     * @param lo the lowest index
     * @param hi the highest index
     * @param d the starting index
     */
    public static void sort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            int j = i;
            while (j > lo && less(a[j], a[j-1], d)) {
                exch(a, j, j-1);
                j--;
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    public static void main(String[] args) {
        Double[] a = new Double[100000];
        Random r = new Random();
        for (int i=0; i<a.length; i++) a[i] = r.nextDouble();
        long startTime = System.currentTimeMillis();
        sort(a);
        if (isSorted(a)) System.out.println("Done");
        else System.out.println("Fail");
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime)/1000.0 + " s");
    }
}
