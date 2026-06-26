
import java.util.*;

public class EIMULEMA {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        Vertex[] v = read(n);
        dfs(v[0]);
        for (Vertex x : v) {
            sb.append(x.id + " " + x.profit + "\n");
        }
        System.out.println(sb);

    }

    static void dfs(Vertex v) {
        v.visited = true;
        v.profit = (int) (v.amount * 0.15);
        for (Vertex e : v.neighbors) {
            if (!e.visited) {
                dfs(e);
            }
            if (v.neighbors.size() > 0) {
                v.profit += e.profit / 2;
            }
        }
    }

    static Vertex[] read(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            int amount = sc.nextInt();
            vertices[i] = new Vertex(i, amount);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].add(vertices[v]);
        }
        return vertices;

    }

    static class Vertex {

        int id, amount, profit;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id, int s) {
            this.id = id;
            this.amount = s;
        }

        public void add(Vertex v) {
            neighbors.add(v);
        }
    }
}
