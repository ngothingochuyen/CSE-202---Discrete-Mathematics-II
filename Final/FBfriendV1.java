
import java.util.*;

public class FBfriendV1 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        List<Component> components = new ArrayList<>();
        for (int i = 1; i < vertices.length; i++) {
            Vertex v = vertices[i];
            if (!v.visited) {
                Component c = new Component(v.id);
                dfs(v, c);
                components.add(c);
            }
        }
        components.sort((a, b) -> (a.id - b.id));
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
        for (Vertex x : v.neighbor) {
            if (!x.visited) {
                dfs(x, c);
            }
        }

    }

    static Vertex[] readGraph(int n, int m) {
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
        boolean visited;
        String gender;
        List<Vertex> neighbor = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addNeighbor(Vertex v) {
            neighbor.add(v);
        }

        public int getDeg() {
            return neighbor.size();
        }
    }

    static class Component {

        int id, male, female;

        public Component(int id) {
            this.id = id;
            female = 0;
            male = 0;
        }
    }

}
