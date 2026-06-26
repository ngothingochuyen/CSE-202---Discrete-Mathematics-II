
import java.util.*;

public class EILOCALA {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int maxLength = 0;
    static int id = -1;

    public static void main(String[] args) {
        int n = sc.nextInt();
        Vertex[] vertices = readGraph(n);
        dfs(vertices[0]);
        int currId = id;
        for (Vertex v : vertices) {
            v.visited = false;
            v.weight = 0;
        }
        dfs(vertices[id]);
        System.out.println((currId < id ? currId : id) + " " + maxLength);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        if (maxLength < v.weight) {
            maxLength = v.weight;
            id = v.id;
        } else if (v.weight == maxLength) {
            id = Math.min(id, v.id);
        }
        for (Edge e : v.neighbors) {
            if (!e.end.visited) {
                e.end.weight = e.weight + v.weight;
                dfs(e.end);
            }
        }
    }

    static Vertex[] readGraph(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < n - 1; i++) {
            Vertex u = vertices[sc.nextInt()];
            Vertex v = vertices[sc.nextInt()];

            int cost = sc.nextInt();
            u.addEdge(v, cost);
            v.addEdge(u, cost);

        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        int weight;
        public List<Edge> neighbors;

        public Vertex(int id) {
            this.id = id;
            visited = false;
            neighbors = new ArrayList<>();
            weight = 0;
        }

        public void addEdge(Vertex v, int cost) {
            neighbors.add(new Edge(v, cost));
        }
    }

    static class Edge {

        Vertex end;
        int weight;

        public Edge(Vertex v, int weight) {
            this.end = v;
            this.weight = weight;
        }
    }

}
