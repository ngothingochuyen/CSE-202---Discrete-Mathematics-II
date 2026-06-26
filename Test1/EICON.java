
import java.util.*;

public class EICON {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        Vertex[] vertices = read(n, m);
        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (vertices[a].neighbors.contains(vertices[b])) {
                sb.append("Y" + "\n");
            } else {
                sb.append("N" + "\n");
            }
        }
        System.out.println(sb);

    }

    static Vertex[] read(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[v].add(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int i) {
            id = i;
        }

        public void add(Vertex v) {
            neighbors.add(v);
        }
    }

}
