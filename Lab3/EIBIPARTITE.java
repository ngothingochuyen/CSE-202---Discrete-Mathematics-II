
import java.util.*;

public class EIBIPARTITE {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int test = sc.nextInt();
        for (int t = 1; t <= test; t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Vertex[] vertices = read(n, m);
            boolean check = true;
            for (Vertex v : vertices) {
                if (v.group == 0) {
                    if (!bfs(v)) {
                        check = false;
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
        while (!q.isEmpty()) {
            Vertex cur = q.poll();
            for (Vertex x : cur.neighbors) {
                if (x.group == 0) {
                    x.group = (cur.group == 1) ? 2 : 1;
                    q.add(x);
                } else {
                    if (x.group == cur.group) {
                        return false;
                    }
                }
            }
        }
        return true;
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

        int id, group;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Vertex v) {
            neighbors.add(v);
        }
    }

}
