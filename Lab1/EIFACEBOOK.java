
import java.io.*;
import java.util.*;

public class EIFACEBOOK {

    static InputReader sc = new InputReader();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        for (int i = 1; i <= n; i++) {
            sb.append(check(vertices[i]) + " ");
        }
        System.out.println(sb);
    }

    public static int check(Vertex v) {
        int count = 0;
        if (v.neighbors.size() > 0) {
            for (Vertex u : v.neighbors) {
                if (v.gender != u.gender) {
                    count++;
                }
            }
        }
        return count;
    }

    public static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i, sc.next().equals("Nam") ? 0 : 1);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (!vertices[u].neighbors.contains(vertices[v])) {
                vertices[u].addNeighbor(vertices[v]);
                vertices[v].addNeighbor(vertices[u]);
            }
        }
        return vertices;
    }

    public static class Vertex {

        int id;
        int gender;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id, int gender) {
            this.id = id;
            this.gender = gender;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
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
