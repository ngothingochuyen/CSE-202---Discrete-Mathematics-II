package weight_longest_shorted;

import java.util.*;

public class EIMINSPAN {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(prim(readGraph(n, m)));
    }

    static long prim(Vertex[] vertices) {
        PriorityQueue<Edge> q = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        vertices[0].cost = 0;
        q.add(new Edge(vertices[0], vertices[0].cost));
        int total = 0;
        int count = 0;
        while (!q.isEmpty()) {
            Edge e = q.poll();
            Vertex v = e.des;
            if (!v.visited) {
                v.visited = true;
                total += e.weight;
                count++;
                for (Edge x : v.neighbors) {
                    Vertex u = x.des;
                    if (!u.visited && u.cost > x.weight) {
                        u.cost = x.weight;
                        q.add(new Edge(u, x.weight));
                    }
                }
            }
        }
        return count == vertices.length ? total : -1;
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            vertices[u].addNeighbor(vertices[v], w);
            vertices[v].addNeighbor(vertices[u], w);
        }
        return vertices;
    }

    static class Edge {

        int weight;
        Vertex des;

        public Edge(Vertex v, int w) {
            this.des = v;
            this.weight = w;
        }
    }

    static class Vertex {

        int id;
        boolean visited;
        int cost = Integer.MAX_VALUE;
        List<Edge> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v, int w) {
            neighbors.add(new Edge(v, w));
        }
    }
}
