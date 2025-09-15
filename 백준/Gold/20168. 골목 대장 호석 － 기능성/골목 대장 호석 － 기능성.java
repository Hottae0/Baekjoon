import java.io.*;
import java.util.*;

class info{
    int u;
    long cost;
    long max;
    info(int u, long cost, long max){
        this.u = u;
        this.cost = cost;
        this.max = max;
    }
}

public class Main {
    static ArrayList<info> graph[];

    static boolean check[];
    static int arr[];
    static int N, M, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 교차로 개수
        M = Integer.parseInt(st.nextToken()); // 도로 개수
        int A = Integer.parseInt(st.nextToken()) - 1; // 시작
        int B = Integer.parseInt(st.nextToken()) - 1; // 도착
        C = Integer.parseInt(st.nextToken()); // 소유 돈

        graph = new ArrayList[N];
        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            graph[a].add(new info(b, c, 0));
            graph[b].add(new info(a, c , 0));
        }

        long ans = Dijkstra(A, B);

        bw.write(ans == Long.MAX_VALUE ? "-1" : ans + "");

        bw.flush();
        bw.close();
    }

    static long Dijkstra(int start, int end){

        long arr[][] = new long[N][2]; //[0] = 최소 최대 가격, [1] = 지금까지의 합
        for(int i = 0 ; i < N; i++){
            Arrays.fill(arr[i], Long.MAX_VALUE);
        }

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o1.max, o2.max);
            }
        });

        que.add(new info(start, 0, 0));

        while (!que.isEmpty()){
            info io = que.poll();

            int from = io.u;
            long now_sum = io.cost;
            long now_max = io.max;

            if(now_max > arr[from][0] || (now_sum > arr[from][1] && now_max == arr[from][0])) continue;

            for(info next : graph[from]){
                int to = next.u;
                long edge_cost = next.cost;

                long new_max = Math.max(io.max, edge_cost);

                long new_sum = io.cost + edge_cost;
                if(new_sum > C) continue;

                if(arr[to][0] > new_max ||(new_max == arr[to][0] && new_sum < arr[to][1])){
                    arr[to][0] = new_max;
                    arr[to][1] = new_sum;
                    que.add(new info(to, new_sum, new_max));
                }
            }

        }

        return arr[end][0];
    }

}