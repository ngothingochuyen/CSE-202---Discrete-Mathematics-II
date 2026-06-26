
import java.util.*;

public class EIFOLTRE {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static HashMap<String, Vertex> map = new HashMap<>();

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = n - 1;
        read(m);
        String root = sc.next();
        dfs(map.get(root));
        System.out.println(sb);

    }

    public static void dfs(Vertex v) {
        v.visited = true;
        v.neighbors.sort((a, b) -> (a.name.compareToIgnoreCase(b.name)));
        sb.append("-");
        for (int i = 0; i < v.level; i++) {
            sb.append("---");
        }
        sb.append(v.name + "\n");
        for (Vertex u : v.neighbors) {
            if (!u.visited) {
                u.level = v.level + 1;
                dfs(u);
            }
        }
    }

    public static void read(int m) {
        for (int i = 0; i < m; i++) {
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

        String name;
        int level;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();

        public Vertex(String n) {
            name = n;
        }

        public void addNeighbor(Vertex v) {
            if (!v.neighbors.contains(v)) {
                neighbors.add(v);
            }
        }
    }

}
