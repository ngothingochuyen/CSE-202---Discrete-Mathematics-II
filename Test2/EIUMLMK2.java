
import java.util.*;

public class EIUMLMK2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        Vertex[] vertices = read(n);
        long p = sc.nextLong();
        count(vertices[0], null);
        dfs(vertices[0], null, p);
        for (Vertex v : vertices) {
            sb.append(v.pro + " ");
        }
        System.out.println(sb);
    }

    static boolean dfs(Vertex v, Vertex u, long p) {
        if (v.limit < p) {
            v.pro = 0;
            return false;
        }
        p += p / 10;
        int sold = 0;
        for (Vertex x : v.neighbors) {
            if (x != u && dfs(x, v, p)) {
                sold += x.sub;
            }
        }
        v.pro = v.sub - sold;
        return true;

    }

    static void count(Vertex v, Vertex u) {
        v.sub = 1;
        for (Vertex x : v.neighbors) {
            if (x != u) {
                count(x, v);
                v.sub += x.sub;
            }
        }
    }

    static Vertex[] read(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].add(vertices[v]);
            vertices[v].add(vertices[u]);
        }
        for (int i = 0; i < n; i++) {
            vertices[i].limit = sc.nextLong();
        }
        return vertices;
    }

    static class Vertex {

        int id, sub, pro;
        long limit;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Vertex v) {
            neighbors.add(v);
        }

    }
}
