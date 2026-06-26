
import java.util.*;

public class EIKARY {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        Vertex[] vertices = readGraph(n);
        for (Vertex v : vertices) {
            if (checkTreeK(k, v, vertices)) {
                System.out.println("Yes");
                break;
            } else {
                System.out.println("No");
                break;
            }
        }

    }

    public static boolean checkTreeK(int k, Vertex root, Vertex[] graph) {
        for (Vertex v : graph) {
            if (v.id == root.id && v.neighbors.size() > k) {
                return false;
            }
            if (v.neighbors.size() - 1 > k) {
                return false;
            }
        }
        return true;
    }

    public static Vertex[] readGraph(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            Vertex uVertex = vertices[u];
            Vertex vVertex = vertices[v];

            uVertex.addNeighbor(vVertex);
            vVertex.addNeighbor(uVertex);

        }
        return vertices;
    }

    static class Vertex {

        public int id;
        public int group;
        public List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
            this.group = 0;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }

        public int getDegree() {
            return neighbors.size();
        }
    }

}
