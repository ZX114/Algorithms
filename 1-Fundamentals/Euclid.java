import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 计算 a 和 b 的最大公约数
 */
public class Euclid {
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a%b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        System.out.println(gcd(a, b));
    }
}