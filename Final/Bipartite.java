
import java.util.*;

public class Bipartite {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int test = sc.nextInt();
        for (int t = 0; t < test; t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Vertex[] vertices = readGraph(n, m);
            boolean check = true;
            for (Vertex v : vertices) {
                if (!v.visited) {
                    check = bfs(v);
                    if (!check) {
                        break;
                    }
                }
            }
            if (check) {
                sb.append("Yes" + "\n");
            } else {
                sb.append("No" + "\n");
            }
        }

        System.out.println(sb);
    }

    static boolean bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(v);
        v.visited = true;
        while (!q.isEmpty()) {
            Vertex x = q.poll();
            for (Vertex u : x.neighbor) {
                if (u.group == 0) {
                    u.group = (x.group == 1) ? 2 : 1;
                    q.add(u);
                } else {
                    if (x.group == u.group) {
                        return false;
                    }
                }
            }
        }
        return true;
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
        int group;
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
