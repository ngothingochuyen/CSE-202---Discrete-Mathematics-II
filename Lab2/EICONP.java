
import java.util.*;

public class EICONP {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int mEdges = sc.nextInt();
        Vertex[] vertices = readGraph(nVertices, mEdges);
        int count = 0;
        for (Vertex v : vertices) {
            if (!v.visited) {
                List<Vertex> visited = new ArrayList<>();
                dfs(v, visited);
                count++;
            }
        }
        System.out.println(count);

    }

    public static void dfs(Vertex v, List<Vertex> visited) {
        v.visited = true;
        visited.add(v);
        v.neighbors.sort((a, b) -> a.id - b.id);
        for (Vertex u : v.neighbors) {
            if (!u.visited) {
                dfs(u, visited);
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
