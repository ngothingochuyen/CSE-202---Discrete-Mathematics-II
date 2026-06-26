
import java.util.*;

public class EITRGROUP {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int level = 0;
        Vertex[] vertices = read(n, m);
        for (Vertex v : vertices) {
            if (!v.noLe) {
                dfs(v);
                break;
            }
        }
        for (Vertex v : vertices) {
            level = level > v.level ? level : v.level;
        }
        System.out.println(level + 1);

    }

    static void dfs(Vertex v) {
        v.visited = true;
        for (Vertex x : v.neighbors) {
            if (!x.visited) {
                x.level = v.level + 1;
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
            vertices[v].noLe = true;
        }
        return vertices;
    }

    static class Vertex {

        int id, level;
        boolean noLe;
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
