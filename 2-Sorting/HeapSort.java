import java.util.Random;

public class HeapSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k=0; k<N; k++) {
            swim(a, k, N);
        }
        while (N > 0) {
            exch(a, 0, --N);
            sink(a, 0, N);
        }
    }
    private static int parent(int k) { return ((k+1)/2 - 1);}
    private static int lcd(int k) { return ((k+1)*2 - 1);}

    private static void sink(Comparable[] a, int k, int N) {
        while (lcd(k) < N) {
            int j = lcd(k);
            if (j < N-1 && less(a, j, j+1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }
    private static void swim(Comparable[] a, int k, int N) {
        while (k > 0 && less(a, parent(k), k)) {
            exch(a, k, parent(k));
            k = parent(k);
        }
    }

    private static boolean less(Comparable[] a, int i, int j) { return a[i].compareTo(a[j]) < 0; }
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static boolean isSorted(Comparable[] a) {
        for (int i=1; i<a.length; i++)
            if (less(a, i, i-1)) return false;
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
