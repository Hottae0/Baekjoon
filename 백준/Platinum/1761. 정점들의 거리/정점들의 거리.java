import java.io.*;
import java.util.*;

class info{
    int child;
    int cost;

    info(int child, int cost){
        this.child = child;
        this.cost = cost;
    }
}


public class Main {
    static ArrayList<ArrayList<info>> graph = new ArrayList<>();
    static int depth[];
    static int parent[];
    static int cost[];
    static boolean root[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        depth = new int[N + 1];
        cost = new int[N + 1];
        root = new boolean[N + 1];

        for(int i = 0 ; i < N + 1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());

            int parent_tmp = Integer.parseInt(st1.nextToken());
            int child_tmp = Integer.parseInt(st1.nextToken());
            int cost_tmp = Integer.parseInt(st1.nextToken());

            graph.get(parent_tmp).add(new info(child_tmp, cost_tmp));
            graph.get(child_tmp).add(new info(parent_tmp, cost_tmp));

        } // 트리 제작

        // 깊이 판단 + 부모 판단.
        depth_check(1, 1);

        // 받고 최대한 찾아보기.
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < M; i++){
            StringTokenizer st2= new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st2.nextToken());
            int v = Integer.parseInt(st2.nextToken());

            int dis = 0;

            while (depth[u] > depth[v]){
                // 거리 더하기
                dis += cost[u];

                u = parent[u];
            }

            while (depth[v] > depth[u]){
                // 거리 더하기
                dis += cost[v];

                v = parent[v];
            }

            while (u != v) {
            // 거리 더하기
                dis += cost[u];
                dis += cost[v];

                u = parent[u];
                v = parent[v];
            }

            sb.append(dis + "\n");

        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static void depth_check(int idx, int dep){
        depth[idx] = dep;
        root[idx] = true; // 방믄 판당

        for(info io : graph.get(idx)){
            if(!root[io.child]){
                parent[io.child] = idx;
                cost[io.child] = io.cost;
                depth_check(io.child, dep + 1);
            }

        }

    }


}

