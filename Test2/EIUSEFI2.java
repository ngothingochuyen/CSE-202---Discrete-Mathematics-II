
import java.util.*;

public class EIUSEFI2 {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static List<Vertex> list = new ArrayList<>();
    static HashMap<String, Vertex> map = new HashMap<>();
    static Vertex rootVertex;

    public static void main(String[] args) {
        int n = sc.nextInt();
        readGraph(n);
        String root = sc.next();
        String s = sc.next();
        rootVertex = map.get(root);
        dfs(rootVertex, s);
        for (Vertex v : list) {
            sb.append(v.name + " " + v.count + "\n");
        }
        System.out.println(sb);
    }

    public static void dfs(Vertex v, String s) {
        v.visited = true;
        v.neighbors.sort((a, b) -> a.name.compareToIgnoreCase(b.name));
        for (Vertex u : v.neighbors) {
            if (!u.visited) {
                dfs(u, s);
                if (u.getSize() == 1 && u.name.toLowerCase().contains(s)) {
                    v.count++;
                }
                v.count += u.count;
            }
        }
        if ((v.getSize() > 1 || rootVertex == v) && v.count > 0) {
            list.add(v);
        }
    }

    public static void readGraph(int n) {
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

    public static class Vertex {

        int count;
        String name;
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
