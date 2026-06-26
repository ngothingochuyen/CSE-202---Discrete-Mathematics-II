
import java.util.*;

public class EIGREENCITY {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n);

        int count = 0;
        for (Vertex v : vertices) {
            if (v.neighbors.size() == 0 || (v.neighbors.size() == 1 && v.id != m)) {
                count++;
            }
        }

        for (int i = 0; i < count; i++) {
            int u = sc.nextInt();
            long q = sc.nextLong();
            vertices[u].tree = q;
        }

        dfs(vertices[m]);

        for (Vertex v : vertices) {
            sb.append(v.id + " " + v.tree + "\n");
        }
        System.out.print(sb);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        for (Vertex e : v.neighbors) {
            if (!e.visited) {
                dfs(e);
                v.tree += e.tree;
            }
        }
    }

    static Vertex[] readGraph(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < n - 1; i++) {
            Vertex u = vertices[sc.nextInt()];
            Vertex v = vertices[sc.nextInt()];
            u.addNeighbor(v);
            v.addNeighbor(u);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        long tree;
        public List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
            visited = false;
            tree = 0;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }
    }
}
