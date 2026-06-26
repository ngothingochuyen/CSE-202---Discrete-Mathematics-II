
import java.io.*;
import java.util.*;

public class EIMKF {

    static InputReader sc = new InputReader();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        for (int i = 0; i < vertices.length; i++) {
            sb.append(check(vertices[i]) + "\n");
        }
        System.out.println(sb);

    }

    public static String check(Vertex v) {
        String s = "";
        s += v.id + " " + v.neighbors.size() + " ";
        if (v.neighbors.size() == 0) {
            return s;
        }
        if (v.neighbors.size() > 0) {
            v.neighbors.sort((v1, v2) -> {
                return v1.id - v2.id;
            });
            for (Vertex u : v.neighbors) {
                s += u.id + " ";
            }
        }
        return s;
    }

    public static Vertex[] readGraph(int n, int m) {
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

    public static class Vertex {

        int id;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            if (!neighbors.contains(v)) {
                neighbors.add(v);
            }
        }
    }

    static class InputReader {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) {
                        return "";
                    }
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
