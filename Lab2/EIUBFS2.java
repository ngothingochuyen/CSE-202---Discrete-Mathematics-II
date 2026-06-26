
import java.util.*;

public class EIUBFS2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = read(n, m);
        for (Vertex v : vertices) {
            v.neighbors.sort((a, b) -> (a.id - b.id));
        }
        List<Vertex> visited = bfs(vertices[0]);
        for (Vertex v : visited) {
            sb.append(v.id + " ");
        }
        System.out.println(sb);

    }

    static List<Vertex> bfs(Vertex v) {
        Queue<Vertex> q = new ArrayDeque<>();
        q.add(v);
        v.visited = true;
        List<Vertex> visiting = new ArrayList<>();
        while (!q.isEmpty()) {
            Vertex cur = q.poll();
            visiting.add(cur);
            for (Vertex x : cur.neighbors) {
                if (!x.visited) {
                    x.visited = true;
                    q.add(x);
                }
            }
        }
        return visiting;
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

        public Vertex(int i) {
            id = i;
        }

        public void add(Vertex v) {
            neighbors.add(v);
        }
    }

}
