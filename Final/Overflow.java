
import java.util.*;

public class Overflow {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = n - 1;
        Vertex[] vertices = readGraph(n, m);
        dfs(vertices[0]);
        for (Vertex v : vertices) {
            if (v.water > 0 && v.getDeg() == 0) {
                sb.append(v.id + " " + v.water + "\n");
            }
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        for (Vertex x : v.neighbor) {
            if (!x.visited) {
                x.water += v.water / v.getDeg();
                dfs(x);
            }
        }
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            double water = sc.nextDouble();
            vertices[i] = new Vertex(i, water);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        double water;
        List<Vertex> neighbor = new ArrayList<>();

        public Vertex(int id, double water) {
            this.id = id;
            this.water = water;
        }

        public void addNeighbor(Vertex v) {
            if (!neighbor.contains(v)) {
                neighbor.add(v);
            }
        }

        public int getDeg() {
            return neighbor.size();
        }
    }

}
