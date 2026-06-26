
import java.util.*;

public class EIUEASPOST {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nNode = sc.nextInt();
        Node[] nodes = ReadTree(nNode);
        printPostOrder(nodes[0]);
        System.out.println(sb);

    }

    public static void printPostOrder(Node node) {
        if (node != null) {
            printPostOrder(node.Left);
            printPostOrder(node.Right);
            sb.append(node.Id).append(" ");
        }
    }

    public static Node[] ReadTree(int nNode) {
        Node[] nodes = new Node[nNode];
        for (int i = 0; i < nNode; i++) {
            nodes[i] = new Node(i + 1);
        }
        for (int i = 0; i < nNode; i++) {
            int leftIndex = sc.nextInt();
            nodes[i].Left = leftIndex > 0 ? nodes[leftIndex - 1] : null;
            int rightIndex = sc.nextInt();
            nodes[i].Right = rightIndex > 0 ? nodes[rightIndex - 1] : null;
        }
        return nodes;
    }

    public static class Node {

        public int Id;
        public Node Left;
        public Node Right;

        public Node(int id) {
            Id = id;
        }
    }

}
