import java.io.*;
import java.util.*;

class info{
    int u;
    int v;
    int cost;

    info(int v, int cost){
        this.v = v;
        this.cost = cost;
    }

    info(int u, int v, int cost){
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static int n, m;

    static int parent[];

    static ArrayList<info>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        graph = new ArrayList[n];

        for(int i = 0 ; i < n; i++){
            parent[i] = i;
            graph[i] = new ArrayList<>();
        } // 부모 노드 초기화


        info[] tmp = new info[m];

        for(int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            tmp[i] = new info(a, b, c);
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int e = Integer.parseInt(st.nextToken()) - 1;

        for(int i = 0; i < m; i++){
            info io = tmp[i];

            int u = io.u;
            int v = io.v;
            int cost = io.cost;

            union(u , v);

            graph[u].add(new info(v, cost));
            graph[v].add(new info(u, cost));

            //if(find(s) == find(e)) break;
        }

        bw.write(Dijkstra(s, e) + "");

        bw.flush();
        bw.close();
    }



    static int find(int x){ // 부모 노드 찾기
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y){ // 부모 노드 같게 하기.
        int a = find(x);
        int b = find(y);

        if(a == b){ // 부모노드가 같다.
            return false;
        }

        if(a < b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }

        return true;

    }

    static int Dijkstra(int start, int end){
        int[] arr = new int[n];
        Arrays.fill(arr, 10_000_001);
        arr[start] = 0;

        Queue<info> que = new ArrayDeque<>();
        que.add(new info(start , 0));

        while(que.size() > 0){
            info io = que.poll();

            int from = io.v;
            int sum = io.cost;

            if(sum > arr[from]) continue; // 미리 담아진거라면 스킵하기.

            for(int i = 0; i < graph[from].size(); i++){

                int to = graph[from].get(i).v;
                int cost = graph[from].get(i).cost;

                if(arr[to] > arr[from] + cost){
                    arr[to] = arr[from] + cost;
                    que.add(new info(to, arr[from]));

                }
            }

        }
        return arr[end];
    }

}