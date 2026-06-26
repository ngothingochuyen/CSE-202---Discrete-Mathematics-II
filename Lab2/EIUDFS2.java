
import java.util.*;

public class EIUDFS2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = read(n, m);
        List<Vertex> visited = new ArrayList<>();
        dfs(vertices[0], visited);
        for (Vertex v : visited) {
            sb.append(v.id + " ");
        }
        System.out.println(sb);

    }

    public static void dfs(Vertex v, List<Vertex> visited) {
        v.visited = true;
        visited.add(v);
        v.neighbors.sort((a, b) -> a.id - b.id);
        for (Vertex u : v.neighbors) {
            if (!u.visited) {
                dfs(u, visited);
            }
        }
    }

    public static Vertex[] read(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    public static class Vertex {

        int id;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int i) {
            id = i;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }

    }
}
