import java.util.*;
public class Q2 {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int level=0;
    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Vertex[] vertices = readGraph(n, m);
        vertices[0].level = 1;
        dfs(vertices[0]);
        
        boolean connect = connect(vertices);
        boolean tree = tree(vertices,n,m);
        if(!connect){
            System.out.println(-1);
        }else if(!tree){
            System.out.println(-2);
        }
        if(tree){
            for(Vertex v:vertices){
                count(v);
                sb.append((v.subtree+1)+" ");
            }
        }
        System.out.println(sb);
    }
    static void count(Vertex v){
        for(Vertex x : v.neighbors){
            if(x.level>v.level){
                v.subtree+=x.subtree;
            }
        }
    }
    static boolean tree(Vertex[]vertices,int n, int m){
        if(connect(vertices)&&m==n-1){
            return true;
        }
        if(connect(vertices)&&m!=n-1){
            return false;
        }
        return false;
    }
    static boolean connect(Vertex[] vertices){
        for(Vertex v:vertices){
            if(!v.visited){
                return false;
            }
        }
        return true;
    }
    static void dfs(Vertex v){
        v.visited = true;
        v.subtree=1;
        for(Vertex u:v.neighbors){
            if(!u.visited){
                v.subtree+=u.subtree;
                dfs(u);
            }
            
        }
    }
    static Vertex[] readGraph(int n, int m){
        Vertex[] vertices = new Vertex[n];
        for(int i = 0;i<n;i++){
            vertices[i]=new Vertex(i);
        }
        for(int i = 0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            vertices[u].add(vertices[v]);
            vertices[v].add(vertices[u]);
        }
        return vertices;
    }

    static class Vertex{
        int id, subtree,level;
        boolean visited;
        List<Vertex> neighbors = new ArrayList<>();
        public Vertex(int id){
            this.id=id;
        }
        public void add(Vertex v){
            neighbors.add(v);
        }
        public int getDeg(){
            return neighbors.size();
        }
    }
}
