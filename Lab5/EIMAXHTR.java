
import java.util.*;

public class EIMAXHTR {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int maxHeight = 0;
    static int id = -1;

    public static void main(String[] args) {
        int n = sc.nextInt();
        Vertex[] vertices = read(n);
        dfs(vertices[0]);
        maxHeight = 0;
        int curr = id;
        for (Vertex v : vertices) {
            v.visited = false;
            v.height = 0;
        }
        dfs(vertices[curr]);
        System.out.println((curr > id ? id : curr) + " " + maxHeight);

    }

    static void dfs(Vertex v) {
        v.visited = true;
        if (v.height > maxHeight) {
            maxHeight = v.height;
            id = v.id;
        } else if (v.height == maxHeight) {
            id = Math.min(id, v.id);
        }
        for (Vertex e : v.neighbors) {
            if (!e.visited) {
                e.height = v.height + 1;
                dfs(e);
            }
        }
    }

    static Vertex[] read(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].add(vertices[v]);
            vertices[v].add(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        int height;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Vertex v) {
            neighbors.add(v);
        }
    }

}
