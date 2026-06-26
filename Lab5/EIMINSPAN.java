
import java.util.*;

public class EIMINSPAN {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        System.out.println(prim(vertices));
    }

    static long prim(Vertex[] vertices) {

        PriorityQueue<Pair> q = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        vertices[0].cost = 0;
        q.add(new Pair(0, 0));

        long total = 0;
        int count = 0;

        while (!q.isEmpty()) {

            Pair p = q.poll();
            Vertex u = vertices[p.id];

            if (!u.visited) {
                u.visited = true;
                total += p.cost;
                count++;

                for (Edge e : u.neighbors) {

                    Vertex v = e.endpoint;

                    if (!v.visited && v.cost > e.weight) {
                        v.cost = e.weight;
                        q.add(new Pair(v.id, e.weight));
                    }
                }
            }
        }

        return count == vertices.length ? total : -1;
    }

    static Vertex[] readGraph(int n, int m) {

        Vertex[] vertices = new Vertex[n];

        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            vertices[u].neighbors.add(new Edge(vertices[v], w));
            vertices[v].neighbors.add(new Edge(vertices[u], w));
        }

        return vertices;
    }

    static class Vertex {

        int id;
        int cost = Integer.MAX_VALUE;
        boolean visited;
        List<Edge> neighbors = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }
    }

    static class Edge {

        Vertex endpoint;
        int weight;

        Edge(Vertex endpoint, int weight) {
            this.endpoint = endpoint;
            this.weight = weight;
        }
    }

    static class Pair {

        int id;
        int cost;

        Pair(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }
}
