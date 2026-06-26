
import java.util.*;

public class EIFBFs2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int female = 0;
    static int male = 0;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = read(n, m);
        for (int i = 1; i <= n; i++) {
            Vertex v = vertices[i];
            dfs(v);
            sb.append(v.id + " " + male + " " + female + "\n");
            male = 0;
            female = 0;
            for (int j = 1; j <= n; j++) {
                if (vertices[j].visited) {
                    vertices[j].visited = false;
                }
            }
        }

        System.out.println(sb);

    }

    static void dfs(Vertex v) {
        v.visited = true;
        if (v.gender.equals("Nu")) {
            female++;
        } else {
            male++;
        }
        for (Vertex u : v.neighbors) {
            if (!u.visited) {
                dfs(u);
            }
        }
    }

    static Vertex[] read(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            String gender = sc.next();
            vertices[i] = new Vertex(i, gender);
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
        String gender;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }
    }
}
