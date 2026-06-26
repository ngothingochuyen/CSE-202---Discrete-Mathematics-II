
import java.util.*;

public class EIMKF {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = read(n, m);
        for (int i = 0; i < n; i++) {
            sb.append(check(vertices[i]) + "\n");
        }
        System.out.println(sb);
    }

    static String check(Vertex v) {
        String s = v.id + " " + v.deg() + " ";
        if (v.neighbors.size() == 0) {
            return s;
        }
        if (v.neighbors.size() > 0) {
            v.neighbors.sort((a, b) -> a.id - b.id);
            for (Vertex u : v.neighbors) {
                s += u.id + " ";
            }
        }
        return s;
    }

    static Vertex[] read(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[v].add(vertices[u]);
            vertices[u].add(vertices[v]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int i) {
            id = i;
        }

        public void add(Vertex v) {
            if (!neighbors.contains(v)) {
                neighbors.add(v);
            }
        }

        public int deg() {
            return neighbors.size();
        }
    }

}
