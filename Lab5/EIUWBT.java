
import java.util.*;

public class EIUWBT {

    static Scanner sc = new Scanner(System.in);
    static long totalWeight = 0;
    static long minDiff = Long.MAX_VALUE;
    static int id = -1;
    static long ansMin = 0, ansMax = 0;

    public static void main(String[] args) {
        int n = sc.nextInt();

        Vertex[] vertices = readGraph(n);

        dfs(vertices[0]);

        if (id == -1) {
            System.out.println(-1);
        } else {
            System.out.println(id + " " + ansMin + " " + ansMax);
        }
    }

    static void dfs(Vertex v) {
        v.visited = true;
        v.totalWeight = v.weight;

        List<Vertex> children = new ArrayList<>();

        for (Vertex u : v.adj) {
            if (!u.visited) {
                dfs(u);
                v.totalWeight += u.totalWeight;
                children.add(u);
            }
        }

        if (v.adj.size() == 2) {
            long w1 = 0, w2 = 0;

            if (v.id == 1) {
                if (children.size() == 2) {
                    w1 = children.get(0).totalWeight;
                    w2 = children.get(1).totalWeight;
                }
            } else {
                if (children.size() == 1) {
                    w1 = children.get(0).totalWeight;
                    w2 = totalWeight - v.totalWeight;
                }
            }

            if (w1 > 0 && w2 > 0) {
                long currDiff = Math.abs(w1 - w2);

                if (currDiff < minDiff || (currDiff == minDiff && (id == -1 || v.id < id))) {
                    minDiff = currDiff;
                    id = v.id;
                    ansMin = Math.min(w1, w2);
                    ansMax = Math.max(w1, w2);
                }
            }
        }
    }

    static Vertex[] readGraph(int n) {
        Vertex[] vertices = new Vertex[n];

        for (int i = 0; i < n; i++) {
            long w = sc.nextLong();
            vertices[i] = new Vertex(i + 1, w);
            totalWeight += w;
        }

        for (int i = 0; i < n - 1; i++) {
            int uId = sc.nextInt() - 1;
            int vId = sc.nextInt() - 1;
            vertices[uId].adj.add(vertices[vId]);
            vertices[vId].adj.add(vertices[uId]);
        }

        return vertices;
    }

    static class Vertex {

        int id;
        long weight;
        boolean visited;
        long totalWeight;
        List<Vertex> adj = new ArrayList<>();

        public Vertex(int id, long weight) {
            this.id = id;
            this.weight = weight;
            this.visited = false;
        }
    }
}
