package weight_longest_shorted;

import java.util.*;
// co trong so

public class EIMINDISTA {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static Vertex[] vertices;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        read(n, m);
        if (n > 0) {
            vertices[0].distance = 0;
            bfs(vertices[0]);
        }
        for (int i = 1; i < n; i++) {
            if (vertices[i].distance == -1) {
                sb.append(-1 + " ");
            } else {
                sb.append(vertices[i].distance + " ");
            }
        }

        System.out.println(sb);
    }

    static void bfs(Vertex v) {
        PriorityQueue<Vertex> q = new PriorityQueue<>((a, b) -> Long.compare(a.distance, b.distance));
        q.add(v);
        while (!q.isEmpty()) {
            Vertex u = q.poll();
            if (!u.visited) {
                u.visited = true;
                for (Edge e : u.neighbors) {
                    Vertex curr = e.des;
                    curr.distance = curr.distance == -1 ? u.distance + e.weight : Math.min(curr.distance, u.distance + e.weight);
                    q.add(curr);
                }
            }
        }
    }

    static void read(int n, int m) {
        vertices = new Vertex[n];
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

    }

    static class Edge {

        int weight;
        Vertex des;

        public Edge(Vertex v, int w) {
            this.des = v;
            weight = w;
        }
    }

    static class Vertex {

        int id;
        long distance = -1;
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
