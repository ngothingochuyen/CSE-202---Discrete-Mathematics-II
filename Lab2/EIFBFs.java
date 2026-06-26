
import java.util.*;

public class EIFBFs {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int countMale = 0;
    static int countFemale = 0;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);

        for (int i = 1; i <= n; i++) {
            dfs(vertices[i]);

            sb.append(vertices[i].id + " ").append(countMale + " ").append(countFemale + "\n");
            countFemale = 0;
            countMale = 0;

            for (int j = 1; j <= n; j++) {
                if (vertices[j].visited == true) {
                    vertices[j].visited = false;
                }
            }
        }
        System.out.println(sb);
    }

    static void dfs(Vertex v) {
        v.visited = true;

        if (v.gender.equals("Nu")) {
            countFemale++;
        } else {
            countMale++;
        }

        for (Vertex w : v.neighbors) {
            if (w.visited == false) {
                dfs(w);
            }
        }
    }

    static Vertex[] readGraph(int nVertices, int nedges) {

        Vertex[] vertices = new Vertex[nVertices + 1];

        for (int i = 1; i <= nVertices; i++) {
            String gender = sc.next();
            vertices[i] = new Vertex(i, gender);

        }

        for (int i = 0; i < nedges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            vertices[u].add(vertices[v]);
            vertices[v].add(vertices[u]);
        }

        return vertices;

    }

    static class Vertex {

        int id;
        boolean visited;
        String gender;

        public List<Vertex> neighbors = new ArrayList<>();

        public Vertex(int id, String gender) {
            this.gender = gender;
            this.id = id;
        }

        public void add(Vertex v) {
            if (!neighbors.contains(v)) {
                neighbors.add(v);
            }
        }

    }

    //public static void main(String[] args) {
    //     int nVertices = sc.nextInt();
    //     int mEdges = sc.nextInt();
    //     Vertex[] vertices = readGraph(nVertices, mEdges);
    //     Map<Integer, Component> components = new HashMap<>();
    //     for (int i = 1; i <= nVertices; i++) {
    //         Vertex vertex = vertices[i];
    //         if (!vertex.visited) {
    //             Component c = new Component(vertex.id);
    //             dfs(vertex, c, components);
    //         }
    //     }
    //     List<Component> result = new ArrayList<>(components.values());
    //     result.sort((c1, c2) -> {
    //         return Integer.compare(c1.maxVertex, c2.maxVertex);
    //     });
    //     for (int i = 1; i <= nVertices; i++) {
    //         Vertex v = vertices[i];
    //         sb.append(v.id + " " + components.get(v.componentId).male + " " + components.get(v.componentId).female + "\n");
    //     }
    //     System.out.println(sb);
    // }
    // public static class Component {
    //     int maxVertex;
    //     int id;
    //     int female;
    //     int male;
    //     public Component(int id) {
    //         this.id = id;
    //         this.female = 0;
    //         this.male = 0;
    //     }
    // }
    // public static void dfs(Vertex v, Component c, Map<Integer, Component> coMap) {
    //     v.visited = true;
    //     if (v.gender.equals("Nu")) {
    //         c.female++;
    //     } else {
    //         c.male++;
    //     }
    //     v.componentId = c.id;
    //     c.id = Math.max(v.id, c.id); // luon chon id lon nhat de in ra
    //     coMap.put(c.id, c);
    //     for (Vertex vertex : v.neighbors) {
    //         if (!vertex.visited) {
    //             dfs(vertex, c, coMap);
    //         }
    //     }
    // }
    // public static Vertex[] readGraph(int n, int m) {
    //     Vertex[] vertices = new Vertex[n + 1];
    //     for (int i = 1; i <= n; i++) {
    //         String gender = sc.next();
    //         vertices[i] = new Vertex(i, gender);
    //     }
    //     for (int i = 0; i < m; i++) {
    //         int u = sc.nextInt();
    //         int v = sc.nextInt();
    //         Vertex uVertex = vertices[u];
    //         Vertex vVertex = vertices[v];
    //         uVertex.addNeighbor(vVertex);
    //         vVertex.addNeighbor(uVertex);
    //     }
    //     return vertices;
    // }
    // static class Vertex {
    //     public int componentId;
    //     public int id;
    //     public String gender;
    //     public boolean visited;
    //     public List<Vertex> neighbors = new ArrayList<>();
    //     public Vertex(int id, String gender) {
    //         this.id = id;
    //         this.gender = gender;
    //     }
    //     public void addNeighbor(Vertex v) {
    //         neighbors.add(v);
    //     }
    //     public int getDegree() {
    //         return neighbors.size();
    //     }
    // }
}
