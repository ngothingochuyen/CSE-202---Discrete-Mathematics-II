
import java.util.*;

public class EICHTTRE {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int test = sc.nextInt();
        for (int t = 0; t < test; t++) {
            int count = 0;
            int n = sc.nextInt();
            int m = sc.nextInt();
            Vertex[] vertices = readGraph(n, m);
            if (m == n - 1) {
                for (int i = 0; i < n; i++) {
                    if (!vertices[i].visited) {
                        count++;
                        dfs(vertices[i]);
                    }
                }
                if (count == 1) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO" + "\n");
                }
            } else {
                sb.append("NO" + "\n");
            }
        }
        System.out.println(sb);

    }

    static void dfs(Vertex v) {
        v.visited = true;
        for (Vertex x : v.neighbors) {
            if (!x.visited) {
                dfs(x);
            }
        }
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[v].addNeighbor(vertices[u]);
            vertices[u].addNeighbor(vertices[v]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        public List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            if (!neighbors.contains(v)) {
                neighbors.add(v);
            }
        }

        public int getSize() {
            return neighbors.size();
        }
    }

}
