
import java.util.*;

public class IsTree {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int test = sc.nextInt();
        for (int t = 0; t < test; t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Vertex[] vertices = readGraph(n, m);
            dfs(vertices[0]);
            boolean check = true;
            for (Vertex v : vertices) {
                if (m != n - 1 || !v.visited) {
                    check = false;
                    break;
                }
            }
            if (!check) {
                sb.append("NO" + "\n");
            } else {
                sb.append("YES" + "\n");
            }
        }

        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        for (Vertex x : v.neighbor) {
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
        List<Vertex> neighbor = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            neighbor.add(v);
        }

        public int getDeg() {
            return neighbor.size();
        }
    }

}
