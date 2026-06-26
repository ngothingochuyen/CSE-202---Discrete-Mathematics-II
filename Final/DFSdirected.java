
import java.util.*;

public class DFSdirected {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Vertex> list = new ArrayList<>();
        Vertex[] vertices = readGraph(n, m);
        dfs(vertices[0], list);
        for (Vertex v : list) {
            sb.append(v.id + " ");
        }
        System.out.println(sb);
    }

    static List<Vertex> dfs(Vertex v, List<Vertex> list) {
        v.visited = true;
        list.add(v);
        v.neighbor.sort((a, b) -> (a.id - b.id));
        for (Vertex x : v.neighbor) {
            if (!x.visited) {
                dfs(x, list);
            }
        }
        return list;
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
