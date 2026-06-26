
import java.util.*;

public class SpanningTree {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static Vertex[] vertices;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        readGraph(n, m);
        System.out.println(prim());

    }

    static long prim() {
        PriorityQueue<Edge> q = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        vertices[0].weight = 0;
        q.add(new Edge(vertices[0], 0));
        int count = 0;
        int total = 0;
        while (!q.isEmpty()) {
            Edge u = q.poll();
            Vertex v = u.des;
            if (!v.visited) {
                v.visited = true;
                total += u.weight;
                count++;
                for (Edge e : v.neighbor) {
                    Vertex c = e.des;
                    if (!c.visited && c.weight > e.weight) {
                        c.weight = e.weight;
                        q.add(new Edge(c, e.weight));
                    }
                }
            }
        }
        return count == vertices.length ? total : -1;
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
        int weight = Integer.MAX_VALUE;
        List<Edge> neighbor = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v, int w) {
            neighbor.add(new Edge(v, w));
        }
    }

}
