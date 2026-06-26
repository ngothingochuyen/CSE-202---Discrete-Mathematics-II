
import java.util.*;

public class EIMULEMA {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        Vertex[] vertices = readGraph(n);
        dfs(vertices[0]);
        for (Vertex v : vertices) {
            sb.append(v.id + " " + v.profits + "\n");
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        v.profits = (int) (v.sales * 0.15);
        for (Vertex u : v.neighbors) {
            if (!u.visited) {
                v.level++;
                dfs(u);
            }
            if (v.neighbors.size() > 0) {
                v.profits += u.profits / 2;
            }
        }
    }

    static Vertex[] readGraph(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            int sales = sc.nextInt();
            vertices[i] = new Vertex(i, sales);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].addNeighbor(vertices[v]);
        }
        return vertices;
    }

    static class Vertex {

        int id, sales, profits, level;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id, int sales) {
            this.id = id;
            this.sales = sales;
        }

        public void addNeighbor(Vertex v) {
            if (!neighbors.contains(v)) {
                neighbors.add(v);
            }
        }
    }
}
