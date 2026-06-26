
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EIHCON {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        for (int i = 1; i <= q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            boolean check = isPath(vertices[u], vertices[v]);
            if (check) {
                sb.append("Y" + " ");
            } else {
                sb.append("N" + " ");
            }
        }
        System.out.println(sb);

    }
// Tìm đường đi

    public static boolean isPath(Vertex u, Vertex v) {
        if (u.neighbors.contains(v)) {
            return true;
        }
        for (Vertex x : u.neighbors) {
            if (x.neighbors.contains(v)) {
                return true;
            }
        }
        return false;
    }

    public static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    public static class Vertex {

        int id;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }

        public int getDegree() {
            return neighbors.size();
        }
    }

}
