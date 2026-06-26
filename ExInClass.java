
import java.util.*;

public class ExInClass {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int mEdges = sc.nextInt();
        Vertex[] vertices = readGraph(nVertices, mEdges);
        List<Vertex> visitedVertices = new ArrayList<>();
        dfsLargest(vertices[3], visitedVertices);
        dfsSmallest(vertices[2], visitedVertices);
        for (Vertex v : visitedVertices) {
            sb.append(v.id).append(" ");
        }
        System.out.println(sb);
    }

    public static void dfsLargest(Vertex v, List<Vertex> visitedVertices) {
        v.visited = true;
        visitedVertices.add(v);
        Collections.sort(v.neighbors, (v1, v2) -> Integer.compare(v2.id, v1.id)); //largest - sort giảm dần
        for (Vertex w : v.neighbors) {
            if (!w.visited) {
                dfsLargest(w, visitedVertices);
            }
        }

    }

    public static void dfsSmallest(Vertex v, List<Vertex> visitedVertices) {
        v.visited = true;
        visitedVertices.add(v);
        Collections.sort(v.neighbors, (v1, v2) -> Integer.compare(v1.id, v2.id)); //smallest - sort tăng dần
        for (Vertex w : v.neighbors) {
            if (!w.visited) {
                dfsSmallest(w, visitedVertices);
            }
        }

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
            vVertex.addNeighbor(uVertex);
        }

        return vertices;

    }

    public static class Vertex {

        public int id;
        public boolean visited;
        public List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }
    }

}
