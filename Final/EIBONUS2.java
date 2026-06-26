
import java.util.*;

public class EIBONUS2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        double money = sc.nextDouble();
        Vertex[] vertices = readGraph(n);
        vertices[0].money = money;
        dfs(vertices[0]);

        for (Vertex v : vertices) {
            if (v.neighbor.size() == 0) {
                sb.append(Math.round(v.money) + "\n");
            }
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        for (Vertex x : v.neighbor) {
            v.child_score += x.score;
        }
        for (Vertex x : v.neighbor) {
            x.money = v.money * (x.score / v.child_score);
            if (!x.visited) {
                dfs(x);
            }
        }
    }

    static Vertex[] readGraph(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 1; i < n; i++) {
            vertices[i].score = sc.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].addNeighbor(vertices[v]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        double money;
        double score, child_score;
        boolean visited;
        List<Vertex> neighbor = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            neighbor.add(v);
        }
    }

}
