import java.io.*;
import java.util.*;

class info{
    int j;
    long cost;

    info(int j, long cost){
        this.j = j;
        this.cost = cost;
    }

}

public class Main {
    static ArrayList<info> graph[];

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        graph = new ArrayList[N];
        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Integer.parseInt(st.nextToken());

            graph[a].add(new info(b, c));
            graph[b].add(new info(a, c));
        }

        bw.write(Dijkstra(start, end) + "");

        bw.flush();
        bw.close();
    }

    static long Dijkstra(int start, int end){
        long arr[] = new long[N];
        Arrays.fill(arr, Long.MAX_VALUE);
        arr[start] = 0;

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o1.cost, o2.cost);
            }
        });
        
        que.add(new info(start, 0));

        while(!que.isEmpty()){
            info io = que.poll();

            int from = io.j;
            long sum = io.cost;

            if(sum > arr[from]) continue;

            for(int i = 0 ; i < graph[from].size(); i++){
                info nxt = graph[from].get(i);

                int to = nxt.j;
                long cost = nxt.cost;

                if(arr[to] > arr[from] + cost){
                    arr[to] = arr[from] + cost;
                    que.add(new info(to, arr[to]));
                }
            }
        }

        return arr[end];
    }
}