
import java.util.*;

public class ConnectedComp2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        for (Vertex v : vertices) {
            if (!v.visited) {
                List<Vertex> list = new ArrayList<>();
                dfs(v, list);
                list.sort((a, b) -> (a.id - b.id));
                sb.append(list.get(0).id + " " + list.size() + "\n");
            }
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v, List<Vertex> list) {
        v.visited = true;
        list.add(v);
        for (Vertex x : v.neighbor) {
            if (!x.visited) {
                dfs(x, list);
            }
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
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        String gender;
        List<Vertex> neighbor = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            if (!neighbor.contains(v)) {
                neighbor.add(v);
            }
        }

        public int getDeg() {
            return neighbor.size();
        }
    }

}
