
import java.util.*;

public class EIUEASPOST {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        Node[] nodes = read(n);
        print(nodes[0]);
        System.out.println(sb);

    }

    static void print(Node n) {
        if (n != null) {
            print(n.left);
            print(n.right);
            sb.append(n.id + " ");
        }
    }

    static Node[] read(int n) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
        for (int i = 0; i < n; i++) {
            int l = sc.nextInt();
            nodes[i].left = l > 0 ? nodes[l - 1] : null;
            int r = sc.nextInt();
            nodes[i].right = r > 0 ? nodes[r - 1] : null;
        }
        return nodes;
    }

    static class Node {

        int id;
        Node left;
        Node right;

        public Node(int i) {
            id = i;
        }
    }

}
