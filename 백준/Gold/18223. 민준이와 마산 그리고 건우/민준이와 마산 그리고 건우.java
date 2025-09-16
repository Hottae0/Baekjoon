import java.io.*;
import java.util.*;

class info{
    int v;
    long cost;

    info(int v, long cost){
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static int N, M, P;

    static ArrayList<info>[] graph;
    static long short_path = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        M = Integer.parseInt(st.nextToken()); // 간선의 개수
        P = Integer.parseInt(st.nextToken()) - 1; // 건우의 위치.

        graph = new ArrayList[N];

        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Integer.parseInt(st.nextToken());

            graph[a].add(new info(b, c));
            graph[b].add(new info(a, c));
        }

        long helpable = Dijkstra(P, N - 1) + Dijkstra(0, P);

        if(helpable == short_path){
            bw.write("SAVE HIM");
        }else{
            bw.write("GOOD BYE");
        }

        bw.flush();
        bw.close();
    }

    static long Dijkstra(int start, int end){
        long[] arr = new long[N];
        Arrays.fill(arr, Long.MAX_VALUE);
        arr[start] = 0;

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o1.cost, o2.cost);
            }
        });

        que.add(new info(start, 0));

        while(que.size() > 0){
            info io = que.poll();

            int from = io.v;
            long sum = io.cost;

            if(sum > arr[from]) continue; // 미리 담아진거라면 스킵하기.

            for(int i = 0; i < graph[from].size(); i++){

                int to = graph[from].get(i).v;
                long cost = graph[from].get(i).cost;

                if(arr[to] >= arr[from] + cost){
                    arr[to] = arr[from] + cost;

                    que.add(new info(to, arr[from]));
                }
            }

        }

        short_path = arr[N - 1];
        return arr[end];

    }

}