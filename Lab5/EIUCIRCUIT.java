
import java.util.*;

public class EIUCIRCUIT {

    static Scanner sc = new Scanner(System.in);
    static int[] parent;
    static boolean foundCircuit = false;
    static List<Integer> circuitNodes = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        Vertex[] vertices = readGraph(n, m);
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!vertices[i].visited && !foundCircuit) {
                dfs(vertices[i], null);
            }
        }

        if (foundCircuit) {
            for (int i = circuitNodes.size() - 1; i >= 0; i--) {
                sb.append(circuitNodes.get(i)).append(" ");
            }
            System.out.println(sb);
        } else {
            System.out.println("-1");
        }
    }

    static void dfs(Vertex u, Vertex p) {
        u.visited = true;
        if (p != null) {
            parent[u.id] = p.id;
        }
        for (Edge e : u.neighbors) {
            if (foundCircuit) {
                return;
            }
            Vertex v = e.end;
            if (!v.visited) {
                dfs(v, u);
            } else if (p == null || v.id != p.id) {
                foundCircuit = true;
                int curr = u.id;
                circuitNodes.add(curr);
                while (curr != v.id) {
                    curr = parent[curr];
                    circuitNodes.add(curr);
                }
                return;
            }
        }
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int uId = sc.nextInt();
            int vId = sc.nextInt();
            vertices[uId].addEdge(vertices[vId]);
            vertices[vId].addEdge(vertices[uId]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        public List<Edge> neighbors;

        public Vertex(int id) {
            this.id = id;
            this.visited = false;
            this.neighbors = new ArrayList<>();
        }

        public void addEdge(Vertex v) {
            neighbors.add(new Edge(v));
        }
    }

    static class Edge {

        Vertex end;

        public Edge(Vertex v) {
            this.end = v;
        }
    }
}
