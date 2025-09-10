import java.util.*;
import java.io.*;

class info{
    int u;
    long cost;
    int cnt;

    info(int u, long cost, int cnt){
        this.u = u;
        this.cost = cost;
        this.cnt = cnt;
    }
}

class low_info{
    int u;
    long cost;

    low_info(int u, long cost){
        this.u = u;
        this.cost = cost;
    }
}


public class Main {
    static ArrayList<low_info>[] graph;

    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시
        int M = Integer.parseInt(st.nextToken()); // 도로
        K = Integer.parseInt(st.nextToken()); // 포장할 도로

        graph = new ArrayList[N];

        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[u].add(new low_info(v, cost));
            graph[v].add(new low_info(u, cost));
        }

        bw.write(Dijkstra(0) + "");

        bw.flush();
        bw.close();

    }

    static long Dijkstra(int start){

        long arr[][] = new long[N][K + 1];
        for(int i = 0 ; i < N; i++){
            Arrays.fill(arr[i], Long.MAX_VALUE);
        }
        arr[start][0] = 0;

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o1.cost, o2.cost);
            }
        });

        que.add(new info(start, 0, 0));

        while (!que.isEmpty()){
            info io = que.poll();

            int from = io.u;
            long sum = io.cost;
            int cnt = io.cnt;

            if(sum > arr[from][cnt]) continue;

            for(low_info next : graph[from]){
                int to = next.u;
                long cost = next.cost;

                if(arr[to][cnt] > arr[from][cnt] + cost){
                    arr[to][cnt] = arr[from][cnt] + cost;
                    que.add(new info(to, arr[to][cnt], cnt));
                }

                if(K > cnt){
                    if(arr[to][cnt + 1] > arr[from][cnt]){
                        arr[to][cnt + 1] = arr[from][cnt];
                        que.add(new info(to, arr[to][cnt + 1], cnt + 1));
                    }
                }

            }

        }

        long min = Long.MAX_VALUE;

        for(int i = 0 ; i < K + 1; i++){
            min = Math.min(min, arr[N - 1][i]);
        }

        return min;
    }
}