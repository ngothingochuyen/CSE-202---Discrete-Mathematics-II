
import java.util.*;

public class EICON {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
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

    static public Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];

        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            Vertex uVertex = vertices[u];
            Vertex vVertex = vertices[v];

            vVertex.addNeighbor(uVertex);
        }

        return vertices;
    }

    static class Vertex {

        int id;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }
    }

}
