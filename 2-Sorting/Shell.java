import java.util.Random;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h > N/3) h = 3*h +1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) exch(a, j, j - h);
            }
            h /= 3;
        }
    }
    private static boolean less(Comparable p, Comparable q) { return p.compareTo(q) < 0; }
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
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
        Double[] a = new Double[100000];
        Random r = new Random();
        for (int i=0; i<a.length; i++) a[i] = r.nextDouble();
        sort(a);
        assert isSorted(a);
        System.out.println("Done");
    }
}
