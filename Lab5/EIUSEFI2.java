
import java.util.*;

public class EIUSEFI2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static List<Vertex> list = new ArrayList<>();
    static HashMap<String, Vertex> map = new HashMap<>();

    public static void main(String[] args) {
        int n = sc.nextInt();
        readGraph(n);
        String root = sc.next();
        String find = sc.next();
        dfs(map.get(root), find);
        for (Vertex v : list) {
            if (v.count > 0 && v.getSize() > 1) {
                sb.append(v.name + " " + v.count + "\n");
            }
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v, String s) {
        v.visited = true;
        v.neighbors.sort((a, b) -> (a.name.compareToIgnoreCase(b.name)));
        for (Vertex u : v.neighbors) {
            if (!u.visited) {
                dfs(u, s);
                if (u.name.toLowerCase().contains(s.toLowerCase()) && u.getSize() == 1) {
                    v.count++;
                }
                v.count += u.count;
            }
        }
        if (v.neighbors.size() > 1 && v.count > 0) {
            list.add(v);
        }
    }

    static void readGraph(int n) {
        for (int i = 0; i < n - 1; i++) {
            String u = sc.next();
            String v = sc.next();
            Vertex uVertex = map.get(u);
            Vertex vVertex = map.get(v);
            if (uVertex == null) {
                uVertex = new Vertex(u);
                map.put(u, uVertex);
            }
            if (vVertex == null) {
                vVertex = new Vertex(v);
                map.put(v, vVertex);
            }
            uVertex.addNeighbor(vVertex);
            vVertex.addNeighbor(uVertex);
        }
    }

    static class Vertex {

        String name;
        int count;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(String s) {
            name = s;
        }

        public void addNeighbor(Vertex v) {
            if (!neighbors.contains(v)) {
                neighbors.add(v);
            }
        }

        public int getSize() {
            return neighbors.size();
        }
    }

}
