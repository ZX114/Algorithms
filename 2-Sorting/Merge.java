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

public class Merge {
    private static Comparable[] aux;
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i= lo, j = mid + 1;
        for (int k=lo; k <= hi; k++) aux[k] = a[k];
        for (int k=lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
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
