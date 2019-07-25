import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {
    public static void fib(int N) {
        // display fibonacci sequence from 1 to N
        long f1 = 1;
        long f2 = 1;
        long f;
        if (N < 1) System.out.println("N must be positive");
        else {
            for (int i=1; i<N+1; i++) {
                if (i<3) {
                    f = 1;
                    System.out.println(i + " " + f);
                } else {
                    f = f1+f2;
                    System.out.println(i + " " + f);
                    f1 = f2;
                    f2 = f;
                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        fib(N);
    }
}
