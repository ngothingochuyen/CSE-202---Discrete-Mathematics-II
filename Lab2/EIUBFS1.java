
import java.util.*;

public class EIUBFS1 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int mEdges = sc.nextInt();
        Vertex[] vertices = readGraph(nVertices, mEdges);
        for (Vertex v : vertices) {
            v.neighbors.sort((a, b) -> (a.id - b.id));
        }
        List<Vertex> visited = bfs(vertices[0]);
        for (Vertex v : visited) {
            sb.append(v.id + " ");
        }
        System.out.println(sb);

    }

    static List<Vertex> bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(v);
        v.visited = true;
        List<Vertex> visiting = new ArrayList<>();
        while (!q.isEmpty()) {
            Vertex curr = q.poll();
            visiting.add(curr);
            for (Vertex x : curr.neighbors) {
                if (!x.visited) {
                    x.visited = true;
                    q.add(x);
                }
            }
        }
        return visiting;
    }

    public static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            Vertex uVertex = vertices[u];
            Vertex vVertex = vertices[v];

            uVertex.addNeighbor(vVertex);
        }
        return vertices;
    }

    static class Vertex {

        public int id;
        public boolean visited;
        public List<Vertex> neighbors = new ArrayList<>();

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
