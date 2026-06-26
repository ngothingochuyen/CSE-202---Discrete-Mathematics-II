
import java.util.*;

public class EICONP3 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int mEdges = sc.nextInt();
        Vertex[] vertices = readGraph(nVertices, mEdges);
        for (Vertex v : vertices) {
            if (!v.visited) {
                List<Vertex> visited = new ArrayList<>();
                dfs(v, visited);
                int min = Integer.MAX_VALUE;
                for (Vertex u : visited) {
                    min = Math.min(min, u.id);
                }
                int edges = 0;
                for (Vertex u : visited) {
                    edges += u.neighbors.size();
                }
                edges /= 2; // undirected nên cạnh được tính 2 lần
                sb.append(min + " " + visited.size() + " " + edges + "\n");
                // đỉnh nhỏ nhất + số đỉnh + số cạnh
            }
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v, List<Vertex> visited) {
        v.visited = true;
        visited.add(v);
        for (Vertex u : v.neighbors) {
            if (!u.visited) {
                dfs(u, visited);
            }
        }
    }

    static Vertex[] readGraph(int n, int m) {
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

    static class Vertex {

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
