
import java.util.*;

// 0 trong so
public class EIMINDISTA0 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = readGraph(n, m);

        if (n > 0) {
            vertices[0].distance = 0;
            bfs(vertices[0]);
        }

        for (int i = 1; i < n; i++) {
            if (vertices[i].distance == -1) {
                sb.append("-1 ");
            } else {
                sb.append(vertices[i].distance).append(" ");
            }
        }
        System.out.println(sb);
    }

    static void bfs(Vertex start) {
        PriorityQueue<Vertex> pVertices = new PriorityQueue<>((v1, v2) -> Long.compare(v1.distance, v2.distance));
        pVertices.add(start);

        while (!pVertices.isEmpty()) {
            Vertex cur = pVertices.poll();

            if (!cur.visited) {
                cur.visited = true;
                for (Vertex v : cur.neighbors) {
                    v.distance = v.distance == -1 ? cur.distance + 1 : Math.min(v.distance, cur.distance + 1);
                    pVertices.add(v);
                }
            }
        }
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int uId = sc.nextInt();
            int vId = sc.nextInt();

            Vertex u = vertices[uId];
            Vertex v = vertices[vId];

            u.addEdge(v);
            v.addEdge(u);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        long distance = -1;
        public List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
            this.visited = false;
        }

        public void addEdge(Vertex v) {
            neighbors.add(v);
        }
    }
}
