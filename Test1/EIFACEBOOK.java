
import java.util.*;

public class EIFACEBOOK {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = read(n, m);
        for (int i = 1; i <= n; i++) {
            sb.append(count(vertices[i]) + " ");
        }
        System.out.println(sb);
    }

    static int count(Vertex v) {
        int count = 0;
        for (Vertex x : v.neighbors) {
            if (v.gender != x.gender) {
                count++;
            }
        }
        return count;
    }

    static Vertex[] read(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            String gender = sc.next();
            vertices[i] = new Vertex(i);
            if (gender.equals("Nu")) {
                vertices[i].gender = 1;
            } else {
                vertices[i].gender = 0;
            }
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

        int id, gender;

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
