import java.io.*;
import java.util.*;

class info{
    int u;
    int v;
    long cost;

    info(int v, long cost){
        this.v = v;
        this.cost = cost;
    }

}

public class Main {
    static int n, m;

    static int parent[];
    static boolean visitied[];

    static ArrayList<info>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visitied = new boolean[n];
        graph = new ArrayList[n];


        for(int i = 0 ; i < n; i++){
            graph[i] = new ArrayList<>();
        } // 부모 노드 초기화

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n; i++){
            if(Integer.parseInt(st.nextToken()) == 1){
                visitied[i] = true;
            }
        }





        for(int i = 0; i < m; i++){

            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph[u].add(new info(v, cost));
            graph[v].add(new info(u, cost));

        }

        long ans = Dijkstra(0, n  - 1);

        if(ans == Long.MAX_VALUE){
            bw.write("-1");
        }else{
            bw.write(ans + "");
        }

        bw.flush();
        bw.close();
    }



    static long Dijkstra(int start, int end){
        long[] arr = new long[n];
        Arrays.fill(arr, Long.MAX_VALUE);
        arr[start] = 0;

       PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
           @Override
           public int compare(info o1, info o2) {
               return Long.compare(o1.cost, o2.cost);
           }
       });
        que.add(new info(start , 0));

        while(que.size() > 0){
            info io = que.poll();

            int from = io.v;
            long sum = io.cost;

            if(sum > arr[from]) continue; // 미리 담아진거라면 스킵하기.

            for(int i = 0; i < graph[from].size(); i++){

                int to = graph[from].get(i).v;
                long cost = graph[from].get(i).cost;

                if(to != n - 1 && visitied[to]) continue;

                if(arr[to] > arr[from] + cost){
                    arr[to] = arr[from] + cost;
                    que.add(new info(to, arr[from]));

                }
            }

        }
        return arr[end];
    }

}