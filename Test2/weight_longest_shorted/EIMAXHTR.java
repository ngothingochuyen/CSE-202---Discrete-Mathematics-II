package weight_longest_shorted;

import java.util.*;

public class EIMAXHTR {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int maxHeight = 0;
    static int id = -1;

    public static void main(String[] args) {
        int n = sc.nextInt();
        Vertex[] vertexs = readGraph(n);
        dfs(vertexs[0]);
        maxHeight = 0;
        int curr = id;
        for (Vertex v : vertexs) {
            v.height = 0;
            v.visited = false;
        }
        dfs(vertexs[curr]);
        System.out.println((curr > id ? id : curr) + " " + maxHeight);
    }

    static void dfs(Vertex v) {
        v.visited = true;
        if (v.height > maxHeight) {
            maxHeight = v.height;
            id = v.id;
        } else if (v.height == maxHeight) {
            id = Math.min(v.id, id);
        }
        for (Vertex e : v.neighbors) {
            if (!e.visited) {
                e.height = v.height + 1;
                dfs(e);
            }
        }
    }

    static Vertex[] readGraph(int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < n - 1; i++) {
            Vertex u = vertices[sc.nextInt()];
            Vertex v = vertices[sc.nextInt()];
            u.addEdge(v);
            v.addEdge(u);

        }
        return vertices;
    }

    static class Vertex {

        int id;
        boolean visited;
        int height;
        public List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
            visited = false;
            height = 0;
        }

        public void addEdge(Vertex v) {
            neighbors.add(v);
        }
    }

}
