import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 事件 A 在一次试验中发生的概率为 p，独立重复试验 N 次
 * 以 X 记 A 在 N 次试验中发生的次数，X=k（即发生k次）的概率记为 p(X=k)
 */
public class Binomial {
    private static int cnt = 0;
    // direct method
    // P(X=k) = C_n^k * p^k * (1-p)^(n-k)
    public static double binomial_direct(int N, int k, double p) {
        if (N < k) return 0.0;

        int Cnk = fac(N)/(fac(k)*fac(N-k));
        return Cnk*Math.pow(p, k)*Math.pow(1.0-p, N-k);
    }
    // factorial
    private static int fac(int n) {
        if (n <= 0) return 0;

        int f = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    // storage-recursive
    public static double binomial_storage(int N, int k, double p) {
        double[][] mat = new double[N+1][k+1];
        for (int i=0; i<=N; i++) {
            for (int j=0; j<=k; j++) {
                mat[i][j] = -1.0;
            }
        }
        return bin(N, k, p, mat);
    }
    private static double bin(int N, int k, double p, double[][] mat) {
        cnt++;
        if (N < 0 || k < 0) return 0.0;
        if (N == 0 && k == 0) return 1.0;
        if (mat[N][k] < 0.0) {
            mat[N][k] = bin(N - 1, k - 1, p, mat) * p + bin(N - 1, k, p, mat) * (1.0 - p);
        }
        return mat[N][k];
    }

    // recursive way
    public static double binomial_recursive(int N, int k, double p) {
        cnt++;
        if (N < 0 || k < 0) return 0.0;
        if (N == 0 && k == 0) return 1.0;
        return binomial_recursive(N-1, k-1, p)*p + binomial_recursive(N-1, k, p)*(1.0-p);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        double p = Double.parseDouble(br.readLine());
        long startTime = System.currentTimeMillis();
        System.out.println(binomial_storage(N, k, p));
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime)/1000.0 + " s");
        System.out.println("Number: " + cnt);
    }
}
