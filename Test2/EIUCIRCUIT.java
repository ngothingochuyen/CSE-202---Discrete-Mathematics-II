
import java.util.*;

public class EIUCIRCUIT {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static Vertex[] vertices;
    static boolean check;
    static List<Integer> cirList = new ArrayList<>();
    static int[] parents;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        read(n, m);
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!vertices[i].visited && !check) {
                dfs(vertices[i], null);
            }
        }
        if (check) {
            for (int i = cirList.size() - 1; i >= 0; i--) {
                sb.append(cirList.get(i) + " ");
            }
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    static void dfs(Vertex v, Vertex p) {
        v.visited = true;
        if (p != null) {
            parents[v.id] = p.id;
        }
        for (Edge e : v.neighbors) {
            if (check) {
                return;
            }
            Vertex u = e.des;
            if (!u.visited) {
                dfs(u, v);
            } else if (p == null || u.id != p.id) {
                check = true;
                int c = v.id;
                cirList.add(c);
                while (c != u.id) {
                    c = parents[c];
                    cirList.add(c);
                }
                return;
            }
        }
    }

    static void read(int n, int m) {
        vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].add(vertices[v]);
            vertices[v].add(vertices[u]);
        }
    }

    static class Vertex {

        int id;
        boolean visited;
        List<Edge> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Vertex v) {
            neighbors.add(new Edge(v));
        }
    }

    static class Edge {

        Vertex des;

        public Edge(Vertex v) {
            this.des = v;
        }
    }
}
