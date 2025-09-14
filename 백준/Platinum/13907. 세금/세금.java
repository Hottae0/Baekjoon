import java.awt.*;
import java.io.*;
import java.util.*;

class info{
    int to;
    long cost;
    int edge_cnt;

    info(int to, long cost, int edge_cnt){
        this.to = to;
        this.cost = cost;
        this.edge_cnt = edge_cnt;
    }
}

class Node{
    int to;
    long cost;

    Node(int to, long cost){
        this.to = to;
        this.cost = cost;
    }

}

public class Main {
    static long tax[];

    static ArrayList<Node>[] graph;

    static int N, M, K;

    static long arr[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시
        M = Integer.parseInt(st.nextToken()); // 도로
        K = Integer.parseInt(st.nextToken()); // 세금

        st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken()) - 1;
        int D = Integer.parseInt(st.nextToken()) - 1;

        graph = new ArrayList[N];
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        tax = new long[K + 1];
        for(int i = 1 ; i < K + 1; i++){
            tax[i] = Long.parseLong(br.readLine());
        }

        StringBuilder ans = new StringBuilder();

        Dijkstra(S, D);

        long tax_sum = 0;
        for(int i = 0 ; i < K + 1; i++){
            tax_sum += tax[i];

            long min = Long.MAX_VALUE;

            for(int j = 0; j < N; j++){
                long cost = arr[D][j];
                if(cost == Long.MAX_VALUE) continue;
                
                min = Math.min(min, arr[D][j] + j * tax_sum);
            }

            ans.append(min + "\n");
        }

        bw.write(ans.toString().trim());

        bw.flush();
        bw.close();
    }

    static void Dijkstra(int s, int e){
        arr = new long[N][N];
        for(int i = 0 ; i < N; i++){
            Arrays.fill(arr[i], Long.MAX_VALUE);
        }
        arr[s][0] = 0;

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o1.cost, o2.cost);
            }
        });
        que.add(new info(s, 0, 0));

        while (que.size() > 0){
            info now = que.poll();

            int from = now.to;
            long sum = now.cost;
            int cnt = now.edge_cnt;

            if(sum > arr[from][cnt]) continue;
            if(cnt + 1 >= N) continue;

            for(Node nxt : graph[from]){
                int to = nxt.to;
                long cost = nxt.cost;

                if(arr[to][cnt + 1] > sum + cost){
                    arr[to][cnt + 1] = sum + cost;
                    que.add(new info(to, arr[to][cnt + 1], cnt + 1));

                }
            }

        }

    }

}