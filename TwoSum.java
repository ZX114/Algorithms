import java.util.Arrays;
import java.util.Random;

public class TwoSum {
    // 必须保证所有输入整数各不相同
    // 否则计数会偏少
    public static int count_NlogN(int sum, int[] a) {
        int cnt = 0;
        Arrays.sort(a);
        for (int i=0; i<a.length; i++) {
            if (BinarySearch.rank(sum - a[i], a) > i) cnt++;
        }
        return cnt;
    }
    // 没有必须各不相同的限制
    // 复杂度为 O(N^2)
    public static int count(int sum, int[] a) {
        int cnt = 0;
        for (int i=0; i<a.length; i++) {
            for (int j=i+1; j<a.length; j++) {
                if (a[i] == sum - a[j]) cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[] a = new int[100];
        Random r = new Random();
        for (int i=0; i<a.length; i++)
            a[i] = r.nextInt(10);
        System.out.println(count(10, a));
    }
}
