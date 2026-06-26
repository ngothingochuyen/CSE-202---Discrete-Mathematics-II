
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class TopologicalOrder {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static ArrayList<vertices> list = new ArrayList<>();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        vertices[] vertex = readGraph(n, m);
        BFS(vertex);
        for (vertices v : list) {
            sb.append(v.id).append(" ");
        }
        System.out.println(sb);
    }

    static void BFS(vertices[] vertex) {
        ArrayDeque<vertices> q = new ArrayDeque<>();
        for (vertices v : vertex) {
            if (v.indegree == 0) {
                q.add(v);
                list.add(v);
            }
        }
        if (q.isEmpty()) {
            list.add(new vertices(-1));
            return;
        }
        while (!q.isEmpty()) {
            vertices current = q.poll();
            for (vertices nei : current.neighbors) {
                nei.indegree--;
                if (nei.indegree == 0) {
                    q.add(nei);
                    list.add(nei);
                }
            }
        }
        if (list.size() != vertex.length) {
            list.add(new vertices(-1));
            return;
        }

    }

    static vertices[] readGraph(int n, int m) {
        vertices[] vertex = new vertices[n];
        for (int i = 0; i < n; i++) {
            vertex[i] = new vertices(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertex[u].neighbors.add(vertex[v]);
            vertex[v].indegree++;
        }
        return vertex;
    }

    static class vertices {

        int id;
        int indegree;
        ArrayList<vertices> neighbors = new ArrayList<>();

        public vertices(int id) {
            this.id = id;
        }

        public void addNeighbor(vertices v) {
            neighbors.add(v);
        }
    }
}
