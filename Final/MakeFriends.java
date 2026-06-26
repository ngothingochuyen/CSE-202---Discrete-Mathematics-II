// XỬ LÝ TRÙNGGGGGGGGGGGGGGGG

import java.util.*;

public class MakeFriends {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        for (Vertex v : vertices) {
            sb.append(check(v) + "\n");
        }
        System.out.println(sb);
    }

    static String check(Vertex v) {
        String s = "";
        s += v.id + " " + v.getDeg() + " ";
        if (v.getDeg() == 0) {
            return s;
        }
        if (v.getDeg() > 0) {
            v.neighbor.sort((a, b) -> (a.id - b.id));
            for (Vertex x : v.neighbor) {
                s += x.id + " ";
            }
        }
        return s;

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
