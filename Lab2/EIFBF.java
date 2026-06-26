
import java.util.*;

public class EIFBF {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = read(n, m);
        List<Component> components = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            Vertex v = vertices[i];
            if (!v.visited) {
                Component c = new Component(v.id);
                dfs(v, c);
                components.add(c);
            }
        }
        components.sort((a, b) -> {
            return Integer.compare(a.id, b.id);
        });
        for (Component u : components) {
            sb.append(u.id + " " + u.male + " " + u.female + "\n");
        }

        System.out.println(sb);

    }

    static void dfs(Vertex v, Component c) {
        v.visited = true;
        if (v.gender.equals("Nu")) {
            c.female++;
        } else {
            c.male++;
        }
        c.id = Math.max(c.id, v.id);
        for (Vertex u : v.neighbors) {
            if (!u.visited) {
                dfs(u, c);
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

    static class Component {

        int id, female, male;

        public Component(int i) {
            id = i;
            female = 0;
            male = 0;
        }
    }
}
