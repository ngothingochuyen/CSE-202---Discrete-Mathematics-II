
import java.util.*;

public class EIUMLMK2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int n;
    static Vertex[] vertices;
    static long p;

    public static void main(String[] args) {
        readGraph();

        calculateSubtreeSize(vertices[0], null);
        dfs(vertices[0], null, p);

        for (int i = 0; i < n; i++) {
            sb.append(vertices[i].profits).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    static void readGraph() {
        n = sc.nextInt();
        vertices = new Vertex[n];

        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }

        for (int i = 0; i < n; i++) {
            vertices[i].limit = sc.nextLong();
        }

        p = sc.nextLong();
    }

    static void calculateSubtreeSize(Vertex v, Vertex parent) {
        v.subtreeSize = 1;

        for (Vertex u : v.neighbors) {
            if (u != parent) {
                calculateSubtreeSize(u, v);
                v.subtreeSize += u.subtreeSize;
            }
        }
    }

    static boolean dfs(Vertex v, Vertex parent, long currentPrice) {
        if (currentPrice > v.limit) {
            v.profits = 0;
            return false;
        }

        long nextPrice = currentPrice + currentPrice / 10;
        int sold = 0;

        for (Vertex u : v.neighbors) {
            if (u != parent) {
                if (dfs(u, v, nextPrice)) {
                    sold += u.subtreeSize;
                }
            }
        }

        v.profits = v.subtreeSize - sold;
        return true;
    }

    static class Vertex {

        int id;
        long limit;
        int subtreeSize;
        int profits;
        List<Vertex> neighbors = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void addNeighbor(Vertex v) {
            neighbors.add(v);
        }
    }
}
