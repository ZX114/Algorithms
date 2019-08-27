public class LSD {
    /**
     * Least-Significant-Digit First sort.
     *
     * @param a the array of string to be sorted
     * @param W the number of characters per string
     */
    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
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
}
