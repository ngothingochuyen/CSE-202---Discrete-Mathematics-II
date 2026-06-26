
import java.util.*;

public class TreeHeight {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int count = 0;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = n - 1;
        Vertex[] vertices = readGraph(n, m);
        dfs(vertices[0], 0);
        System.out.println(count);
    }

    static void dfs(Vertex v, int h) {
        v.visited = true;
        count = Math.max(count, h);
        for (Vertex x : v.neighbor) {
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
        List<Vertex> neighbor = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            neighbor.add(v);
        }

        public int getDeg() {
            return neighbor.size();
        }
    }

}
