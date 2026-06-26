
import java.util.*;

public class Bridge {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        for (int i = 0; i < q; i++) {
            boolean flag = true;
            int a = sc.nextInt();
            int b = sc.nextInt();
            vertices[a].neighbor.remove(vertices[b]);
            vertices[b].neighbor.remove(vertices[a]);
            dfs(vertices[0]);
            for (Vertex v : vertices) {
                if (!v.visited) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sb.append("N" + "\n");
            } else {
                sb.append("Y" + "\n");
            }
            vertices[a].addNeighbor(vertices[b]);
            vertices[b].addNeighbor(vertices[a]);
            for (Vertex v : vertices) {
                v.visited = false;
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
            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
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
    }

}
