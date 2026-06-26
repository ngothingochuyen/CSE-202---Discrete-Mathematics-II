
import java.util.*;

public class EITREHE1 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int maxHeight = 0;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = n - 1;
        Vertex[] vertices = readGraph(n, m);
        dfs(vertices[0], 0);
        System.out.println(maxHeight);
    }

    static void dfs(Vertex v, int h) {
        v.visited = true;
        maxHeight = Math.max(h, maxHeight);
        for (Vertex x : v.neighbors) {
            if (!x.visited) {
                dfs(x, h + 1);
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
            vertices[v].addNeighbor(vertices[u]);
            vertices[u].addNeighbor(vertices[v]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        public List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            if (!neighbors.contains(v)) {
                neighbors.add(v);
            }
        }

        public int getSize() {
            return neighbors.size();
        }
    }

}
