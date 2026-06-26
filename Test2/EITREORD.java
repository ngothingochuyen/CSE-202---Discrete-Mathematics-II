
import java.util.*;

public class EITREORD {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] pre = new int[n];
        int[] in = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = sc.nextInt();
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            in[i] = sc.nextInt();
            map.put(in[i], i);
        }
        print(0, n - 1, pre, map);
        System.out.println(sb);
    }
    static int index = 0;

    static void print(int l, int r, int[] pre, HashMap<Integer, Integer> in) {
        if (l > r) {
            return;
        }
        int root = pre[index++];
        int mid = in.get(root);
        print(l, mid - 1, pre, in);
        print(mid + 1, r, pre, in);
        sb.append(root + " ");
    }

}
