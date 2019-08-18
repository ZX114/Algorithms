/*
 * Sort 10,000,000 random double input
 * Comparison between Shell, Merge, Quick and Arrays.sort
 *
 * Shell: 34.8 s
 * Merge: 8.6 s
 * Quick: 5.0 s
 * Arrays.sort: 6.2 s
 *
 * */
import java.util.Random;

public class Quick {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = partition(a, lo, hi);
        sort(a, lo, mid-1);
        sort(a, mid+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable f = a[lo];
        int i = lo+1, j = hi;
        while (i <= j) {
            if (less(a[i], f)) i++;
            else if (less(f, a[j])) j--;
            else { exch(a, i, j); i++; j--; }
        }
        exch(a, lo, j);
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable p, Comparable q) { return p.compareTo(q) < 0; }

    public static void show(Comparable[] a) {
        for (Comparable i : a) System.out.print(i + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i=1; i<a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public static void main(String[] args) {
        Double[] a = new Double[10000000];
        Random r = new Random();
        for (int i=0; i<a.length; i++) a[i] = r.nextDouble();
        long startTime = System.currentTimeMillis();
        sort(a);
        assert isSorted(a);
        System.out.println("Done");
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime)/1000.0 + " s");
    }
}
