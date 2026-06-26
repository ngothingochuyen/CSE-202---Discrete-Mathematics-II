package weight_longest_shorted;

import java.util.*;

public class EILOCALA {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int max = 0;
    static int id = -1;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = n - 1;
        Vertex[] vertices = read(n, m);
        dfs(vertices[0]);
        int curr = id;
        for (Vertex v : vertices) {
            v.visited = false;
            v.weight = 0;
        }
        dfs(vertices[curr]);
        System.out.println((curr > id ? id : curr) + " " + max);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        if (v.weight > max) {
            max = v.weight;
            id = v.id;
        } else if (v.weight == max) {
            id = Math.min(id, v.id);
        }
        for (Edge e : v.neighbors) {
            Vertex u = e.v;
            if (!u.visited) {
                u.weight = e.weight + v.weight;
                dfs(u);
            }
        }
    }

    static Vertex[] read(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            vertices[u].add(vertices[v], w);
            vertices[v].add(vertices[u], w);
        }
        return vertices;
    }

    static class Edge {

        int weight;
        Vertex v;

        public Edge(Vertex v, int w) {
            this.v = v;
            this.weight = w;
        }
    }

    static class Vertex {

        int id;
        int weight;
        boolean visited;
        List<Edge> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Vertex v, int w) {
            neighbors.add(new Edge(v, w));
        }
    }

}
