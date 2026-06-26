
import java.util.*;

public class EIPRF {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static ArrayList<vertices> list = new ArrayList<>();
    static boolean flag = false;

    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        vertices[] vertex = readGraph(n, m);
        dfs(vertex[0]);
        for (vertices v : list) {
            sb.append(v.id).append(" ");
        }
        System.out.println(sb);
    }

    static void dfs(vertices vertex) {
        if (flag) {
            return;
        }
        vertex.visited = true;
        list.add(vertex);
        for (vertices nei : vertex.neighbors) {
            if (nei.id == 0) {
                flag = true;
                return;
            }
            if (!nei.visited) {
                dfs(nei);
            }
            if (flag) {
                return;
            }
        }
        list.remove(list.size() - 1);
    }

    static vertices[] readGraph(int n, int m) {
        vertices[] vertex = new vertices[n];
        for (int i = 0; i < n; i++) {
            vertex[i] = new vertices(i);
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (!vertex[u].neighbors.contains(vertex[v])) {
                vertex[u].neighbors.add(vertex[v]);
            }
        }
        return vertex;
    }

    static class vertices {

        int id;
        boolean visited = false;
        ArrayList<vertices> neighbors = new ArrayList<>();

        public vertices(int id) {
            this.id = id;
        }

        public void addNeighbor(vertices v) {
            neighbors.add(v);
        }
    }

}

/*
main{
    for(v:graph){
        //sort nieghbor cua cai ay lai theo thu tu tang dan id
        v.neighbor.sort()
    }
    visitingVertices = new ArrayList<>();
    dfs(graph[0], visitingVertices)
    for(v: visitingVertices){
        sb.append(v.id)
    }
}
readGraph(directed graph)
static boolean isFound = false;
void dfs(u,visitingVertices){
    u.visited = true
    visittingVertices.add(u)
    for(v:u.neighbr){
        if(v.id == 0){
            isFound = true
            break
        }
        if(!v.visited){
            if(isFound){
                break
            }
            dfs(v,visitingVertices)
        }
    }
    if(!isFound) visitingVertices.remove(visitingVertices.size-1)
}
 */
