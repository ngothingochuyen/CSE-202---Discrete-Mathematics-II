
import java.util.*;

public class EIPEOYMK {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        int mEdges = sc.nextInt();
        Vertex[] vertices = readGraph(nVertices, mEdges);
        int root = sc.nextInt();
        int noQueries = sc.nextInt();
        Map<Integer, TreeSet<Vertex>> friendsLevels = bfs(vertices[root]);
        for (int i = 0; i < noQueries; i++) {
            int kLevel = sc.nextInt();
            TreeSet<Vertex> levelSet = friendsLevels.get(kLevel);
            if (levelSet == null || levelSet.isEmpty()) {
                sb.append(-1);
            } else {
                for (Vertex v : levelSet) {
                    sb.append(v.id).append(" ");
                }
            }
            sb.append("\n");

        }
        System.out.println(sb);

    }

    public static Map<Integer, TreeSet<Vertex>> bfs(Vertex v) {
        Map<Integer, TreeSet<Vertex>> vertexMap = new TreeMap<>();
        Queue<Vertex> q = new ArrayDeque<>();
        v.visited = true;
        v.level = 0;
        q.add(v);
        TreeSet<Vertex> rootMap = new TreeSet<>((s1, s2) -> Integer.compare(s1.id, s2.id));
        rootMap.add(v);
        vertexMap.put(0, rootMap);
        while (!q.isEmpty()) {
            Vertex cur = q.poll();
            TreeSet<Vertex> tempNeighbors = vertexMap.getOrDefault(cur.level + 1, new TreeSet<>((s1, s2) -> Integer.compare(s1.id, s2.id)));
            for (Vertex x : cur.neighbors) {
                if (!x.visited) {
                    x.visited = true;
                    x.level = cur.level + 1;
                    q.add(x);
                    tempNeighbors.add(x);
                }
            }
            if (!tempNeighbors.isEmpty()) {
                vertexMap.put(cur.level + 1, tempNeighbors);
            }

        }
        return vertexMap;

    }

    public static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            Vertex uVertex = vertices[u];
            Vertex vVertex = vertices[v];

            uVertex.addNeighbor(vVertex);
            vVertex.addNeighbor(uVertex);

        }
        return vertices;
    }

    static class Vertex {

        public int id;
        public boolean visited;
        public int level;
        public List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
            this.level = 0;
        }

        public void addNeighbor(Vertex v) {
            neighbors.add(v);
        }

        public int getDegree() {
            return neighbors.size();
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

}
