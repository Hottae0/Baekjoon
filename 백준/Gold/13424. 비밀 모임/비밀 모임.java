import java.io.*;
import java.util.*;

class info{
    int v;
    int cost;

    info(int v, int cost){
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static int N, M;

    static ArrayList<info>[] graph;

    static int sum[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 0 ; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N];

            for(int i = 0 ; i < N; i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < M; i++){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());

                graph[a].add(new info(b, c));
                graph[b].add(new info(a, c));
            }

            int K = Integer.parseInt(br.readLine());

            sum = new int[N];
            st = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < K; i++){
                Dijkstra(Integer.parseInt(st.nextToken()) - 1);
            }

            int min = Integer.MAX_VALUE;
            int min_idx = -1;

            for(int i = 0 ; i < N ;i++){
                if(min > sum[i]){
                    min = sum[i];
                    min_idx = i + 1;

                }
            }

            sb.append(min_idx + "\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();

    }




    static void Dijkstra(int start){
        int[] arr = new int[N];
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


        for(int i = 0 ; i < arr.length; i++){
            sum[i] += arr[i];
        }
        return;

    }

}