import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FrequencyCounter {
    public static void main(String[] args) throws FileNotFoundException {
        int minLen = 5;
//        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(20000);
//        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
//        BST<String, Integer> st = new BST<String, Integer>();
        RedBlackBST<String, Integer> st = new RedBlackBST<>();

        long startTime = System.currentTimeMillis();
        Scanner in = new Scanner(new FileReader("leipzig1M.txt"));
        while (in.hasNext()) {
            String word = in.next();
            if (word.length() < minLen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word)+1);
        }
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max)) max = word;

        System.out.println(max + " " + st.get(max));
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime)/1000.0 + " s");
    }
}
