
import java.util.*;

public class EIGREENCITY {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertexs = read(n);
        int count = 0;
        for (Vertex v : vertexs) {
            if (v.neighbors.size() == 0 || (v.neighbors.size() == 1 && v.id != m)) {
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            int u = sc.nextInt();
            int q = sc.nextInt();
            vertexs[u].tree = q;
        }
        dfs(vertexs[m]);
        for (Vertex v : vertexs) {
            sb.append(v.id + " " + v.tree + "\n");
        }
        System.out.println(sb);

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

    static Vertex[] read(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].add(vertices[v]);
            vertices[v].add(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        long tree;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Vertex v) {
            neighbors.add(v);
        }
    }
}
