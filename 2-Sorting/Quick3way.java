import java.util.Random;

public class Quick3way {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while (i < gt) {
            if (less(a[i], v)) { exch(a, lt, i); lt++; i++; }
            else if (less(v, a[i])) { exch(a, gt, i); gt--; }
            else i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
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
