import java.io.*;
import java.util.*;

class info{
    int j;
    int cost;

    info(int j, int cost){
        this.j = j;
        this.cost = cost;
    }

}


public class Main {
    static ArrayList<ArrayList<info>> graph = new ArrayList<>();

    static int N;

    static boolean visited[];
    static int depth[];
    static int max_cost[][];
    static int min_cost[][];

    static int parent[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        depth = new int[N];

        int size = (int)(Math.log(N) / Math.log(2)) + 1;
        parent = new int[N][size]; // i의 2^j 부모의 값

        max_cost = new int[N][size];
        min_cost = new int[N][size];

        for(int i = 0 ; i < N; i++){
            graph.add(new ArrayList<>());
            Arrays.fill(parent[i], -1);
        }


        for(int i = 0 ; i < N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new info(b, cost));
            graph.get(b).add(new info(a, cost));
        }

        depth_check(0, 0);

        for(int j = 1 ; j < size; j++){
            for(int i = 0 ; i < N; i++){
                if(parent[i][j - 1] != -1){ // 그에 해당하는 부모가 있음.
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];

                    max_cost[i][j] = Math.max(max_cost[i][j - 1] , max_cost[parent[i][j - 1]][j - 1]);
                    min_cost[i][j] = Math.min(min_cost[i][j - 1], min_cost[parent[i][j - 1]][j - 1]);
                }else{
                    parent[i][j] = -1;

                }

            }
        } //점화식

        int M = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();

        for(int i = 0 ; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            if(depth[v] > depth[u]){
                int tmp = u;
                u = v;
                v = tmp;
            }

            // u가 더 밑에 존재!

            // 같은 최소 공통 조상을 찾음 -> 거기까지 거리를 각자 구하고 min, max별로 출력.

            // 1. Dep 을 맞춰보자~
            int max = -1;
            int min = Integer.MAX_VALUE;

            for(int j = size - 1; j >= 0; j--){
                if(depth[u] - depth[v] >= Math.pow(2, j)){
                    max = Math.max(max, max_cost[u][j]);
                    min = Math.min(min, min_cost[u][j]);

                    u = parent[u][j];
                }
            }

            if(u == v){
                ans.append(min + " " + max + "\n");
                continue;
            }

            // 2. 이제 최소 공통 조상 찾기~
            for(int j = size - 1; j >= 0; j--){
                if(parent[u][j] != parent[v][j]){
                    min = Math.min(min, Math.min(min_cost[u][j], min_cost[v][j]));
                    max = Math.max(max, Math.max(max_cost[u][j], max_cost[v][j]));

                    u = parent[u][j];
                    v = parent[v][j];
                }

            }

            min = Math.min(min, Math.min(min_cost[u][0], min_cost[v][0]));
            max = Math.max(max, Math.max(max_cost[u][0], max_cost[v][0]));

            ans.append(min + " " + max + "\n");
        }

        bw.write(ans.toString());

        bw.flush();
        bw.close();
    }

    static void depth_check(int idx, int dep){
        visited[idx] = true;
        depth[idx] = dep;

        for(info io : graph.get(idx)){
            int child = io.j;
            int ct = io.cost;

            if(!visited[child]){
                parent[child][0] = idx;

                max_cost[child][0] = ct;
                min_cost[child][0] = ct;

                depth_check(child, dep + 1);
            }

        }

    }

}
