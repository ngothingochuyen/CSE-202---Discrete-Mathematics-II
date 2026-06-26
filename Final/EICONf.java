
import java.io.*;
import java.util.*;

public class EICONf {

    static InputReader sc = new InputReader();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        for (int i = 1; i <= q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            sb.append(((vertices[u].neighbor.contains(vertices[v])) ? "Y" : "N") + "\n");
        }
        System.out.println(sb);
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {

        int id;
        List<Vertex> neighbor = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void addNeighbor(Vertex v) {
            neighbor.add(v);
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
