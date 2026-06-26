
import java.util.*;

public class EICHTTRE {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int test = sc.nextInt();
        for (int t = 0; t < test; t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Vertex[] vertices = read(n, m);
            if (m == n - 1) {
                int count = 0;
                for (int i = 0; i < n; i++) {
                    if (!vertices[i].visited) {
                        count++;
                        dfs(vertices[i]);
                    }
                }
                sb.append((count == 1 ? "YES" : "NO") + "\n");
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

    static Vertex[] read(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].add(vertices[v]);
            vertices[v].add(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Vertex v) {
            neighbors.add(v);
        }
    }

}
