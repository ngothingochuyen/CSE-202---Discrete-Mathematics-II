
import java.util.*;

public class EITREORD {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] pre = new int[n];
        int[] in = new int[n];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = sc.nextInt();
        }

        HashMap<Integer, Integer> inMap = new HashMap();
        for (int i = 0; i < in.length; i++) {
            in[i] = sc.nextInt();
            inMap.put(in[i], i);
        }

        printPostOrder(0, n - 1, pre, inMap);
        System.out.println(sb);

    }

    static int preIndex = 0;

    public static void printPostOrder(int left, int right, int[] pre, HashMap<Integer, Integer> in) {
        if (left > right) {
            return;
        }
        int root = pre[preIndex++];
        int mid = in.get(root);

        printPostOrder(left, mid - 1, pre, in);
        printPostOrder(mid + 1, right, pre, in);

        sb.append(root).append(" ");

    }

}
