
import java.io.*;
import java.util.*;

public class EIUSUFBF {

    static InputReader sc = new InputReader();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        for (int i = 0; i < n; i++) {
            sb.append(check(vertices[i], l) + "\n");
        }
        System.out.println(sb);

    }

    public static String check(Vertex v, int l) {
        String s = "";
        s += v.id + " ";
        v.neighbors.sort((u1, u2) -> u1.id - u2.id);
        for (Vertex u : v.neighbors) {
            if (u.getDegree() < l) {
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
            if (!vertices[u].neighbors.contains(vertices[v])) {
                vertices[u].addNeighbor(vertices[v]);
                vertices[v].addNeighbor(vertices[u]);
            }
        }
        return vertices;
    }

    public static class Vertex {

        int id;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int i) {
            id = i;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }

        public int getDegree() {
            return neighbors.size();
        }
    }

    static class InputReader {

        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16]; // 64KB
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        int nextInt() {
            int c, sign = 1, num = 0;
            try {
                c = read();
                while (c <= ' ') {
                    c = read(); // skip space
                }
                if (c == '-') {
                    sign = -1;
                    c = read();
                }

                while (c > ' ') {
                    num = num * 10 + c - '0';
                    c = read();
                }
            } catch (IOException e) {
                return -1;
            }
            return num * sign;
        }
    }

}
