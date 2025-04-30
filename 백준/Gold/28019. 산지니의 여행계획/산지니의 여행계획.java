import java.io.*;
import java.util.*;


class Node{
    int i, j;
    long cost;

    Node(int i, int j, long cost){
        this.i = i;
        this.j = j;
        this.cost = cost;
    }

    Node(int j, long cost){
        this.j = j;
        this.cost = cost;
    }
}

public class Main {
    static int parent[];
    static ArrayList<Node> arr = new ArrayList<>();
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static long dis = 0;
    static long further[];
    static int start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        further = new long[N + 1];

        for(int i = 0 ; i < N + 1; i++){
            graph.add(new ArrayList<>());
            parent[i] = i;
        }

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr.add(new Node(a,b,c));
        }

        Collections.sort(arr, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.cost > o2.cost){
                    return -1;
                }else if(o1.cost == o2.cost) return 0;

                return 1;
            }
        });

        kruskal();

        start = Integer.parseInt(br.readLine());

        dfs(start, 0, 0);

        bw.write((dis - further[start]) + "");

        bw.flush();
        bw.close();
    }

    static void dfs(int x, long sum, int last){
        if(graph.get(x).size() == 1 && graph.get(x).get(0).j == last ){
            further[start] = Math.max(further[start], sum);
        }

        for(Node nd : graph.get(x)){
            if(nd.j == last) continue; // 밑으로만 내려가기.

            dis += nd.cost * 2;

            dfs(nd.j,sum + nd.cost, x);
        }
    }
    static int find(int x){
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y){
        int a = find(x);
        int b = find(y);

        if(x == y) return false;

        if(a < b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }

        return true;
    }

    static void kruskal(){
        for(int i = 0 ; i < arr.size(); i++){
            Node nd = arr.get(i);

            if(find(nd.i) != find(nd.j)){
                union(nd.i, nd.j);

                graph.get(nd.i).add(new Node(nd.j, nd.cost));
                graph.get(nd.j).add(new Node(nd.i, nd.cost));
            }
        }

    }

}