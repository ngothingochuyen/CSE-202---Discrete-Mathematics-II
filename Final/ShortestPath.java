
import java.util.*;

public class ShortestPath {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static Vertex[] vertices;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        readGraph(n, m);
        vertices[0].weight = 0;
        bfs(vertices[0]);
        for (int i = 1; i < n; i++) {
            sb.append(vertices[i].weight + " ");
        }
        System.out.println(sb);
    }

    static void bfs(Vertex v) {
        PriorityQueue<Vertex> q = new PriorityQueue<>((a, b) -> (a.weight - b.weight));
        q.add(v);
        while (!q.isEmpty()) {
            Vertex x = q.poll();
            if (!x.visited) {
                x.visited = true;
                for (Edge e : x.neighbor) {
                    Vertex c = e.des;
                    c.weight = c.weight == -1 ? x.weight + e.weight : (Math.min(e.weight + x.weight, c.weight));
                    q.add(c);
                }
            }
        }

    }

    static void readGraph(int n, int m) {
        vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            vertices[v].addNeighbor(vertices[u], w);
            vertices[u].addNeighbor(vertices[v], w);
        }
    }

    static class Edge {

        Vertex des;
        int weight;

        public Edge(Vertex v, int w) {
            des = v;
            weight = w;
        }
    }

    static class Vertex {

        int id;
        boolean visited;
        int weight = -1;
        List<Edge> neighbor = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v, int w) {
            neighbor.add(new Edge(v, w));
        }
    }

}
