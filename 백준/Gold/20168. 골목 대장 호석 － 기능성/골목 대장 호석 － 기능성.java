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

        long arr[] = new long[N];
        Arrays.fill(arr, Long.MAX_VALUE);
        arr[0] = 0;

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
            long sum = io.cost;
            long max = io.max;

            if(sum > C) continue;

            for(info next : graph[from]){
                int to = next.u;
                long cost = next.cost;

                max = Math.max(max, cost);

                if(arr[to] > max && sum + cost <= C){
                    arr[to] = max;
                    que.add(new info(to, sum + cost, max));
                }
            }

        }

        return arr[end];
    }

}