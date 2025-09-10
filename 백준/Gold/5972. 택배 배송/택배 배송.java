import javax.print.attribute.standard.ReferenceUriSchemesSupported;
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
    static int N, M;

    static ArrayList<info>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            graph[s].add(new info(e, c));
            graph[e].add(new info(s, c));
        }

        bw.write(Dijkstra_(0) + "");

        bw.flush();
        bw.close();

    }

    static long Dijkstra_(int start){
        long arr[] = new long[N];
        Arrays.fill(arr, Long.MAX_VALUE);
        arr[start] = 0;

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o1.cost, o2.cost);
            }
        });

        que.add(new info(start , 0));

        while (!que.isEmpty()){

            info io = que.poll();

            int from = io.v;
            long sum = io.cost;

            if(sum > arr[from]) continue;

            for(int i = 0 ; i < graph[from].size(); i++){
                info tmp = graph[from].get(i);

                int to = tmp.v;
                long cost = tmp.cost;

                if(arr[to] > arr[from] + cost){
                    arr[to] = arr[from] + cost;
                    que.add(new info(to, arr[to]));
                }
            }

        }

        return arr[N - 1];

    }



}