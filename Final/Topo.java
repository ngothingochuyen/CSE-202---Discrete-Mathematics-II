
import java.util.*;

public class Topo {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static List<Vertex> list = new ArrayList<>();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        bfs(vertices);
        for (Vertex v : list) {
            sb.append(v.id + " ");
        }
        System.out.println(sb);
    }

    static void bfs(Vertex[] vertexs) {
        Queue<Vertex> q = new ArrayDeque<>();
        for (Vertex v : vertexs) {
            if (v.deg == 0) {
                q.add(v);
                list.add(v);
            }
        }
        if (q.isEmpty()) {
            list.add(new Vertex(-1));
            return;
        }
        while (!q.isEmpty()) {
            Vertex u = q.poll();
            for (Vertex x : u.neighbor) {
                x.deg--;
                if (x.deg == 0) {
                    q.add(x);
                    list.add(x);
                }
            }
        }
        if (list.size() != vertexs.length) {
            list.add(new Vertex(-1));
            return;
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
            vertices[v].deg++;
        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        int deg;
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
