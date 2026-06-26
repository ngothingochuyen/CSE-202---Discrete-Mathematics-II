
import java.io.*;
import java.util.*;

public class EIBIRTHDAY {

    static InputReader sc = new InputReader();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int curr = sc.nextInt();
        int k = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        for (int i = 0; i < n; i++) {
            sb.append(check(vertices[i], curr, k) + " ");
        }
        System.out.println(sb);
    }

    public static int check(Vertex v, int curr, int k) {
        int count = 0;
        for (Vertex u : v.neighbors) {
            if (k + curr <= 365) {
                if (u.dob >= curr && u.dob <= k + curr) {
                    count++;
                }
            } else {
                if ((u.dob >= curr && u.dob <= 365) || (u.dob >= 1 && u.dob <= k + curr - 365)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            int dob = sc.nextInt();
            vertices[i] = new Vertex(i, dob);
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

    static class Vertex {

        int id, dob;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int i, int d) {
            id = i;
            dob = d;
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
