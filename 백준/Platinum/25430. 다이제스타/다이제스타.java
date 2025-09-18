import java.io.*;
import java.util.*;

//25430 다이제스타
class Node implements Comparable<Node>{
    int i;
    long cost;

    Node(int i, long cost){
        this.i = i;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node nd) {
        return Long.compare(this.cost, nd.cost);
    }
}

class info{
    int i;
    long previous;
    long cost;

    info(int i, long cost, long previous){
        this.i = i;
        this.cost = cost;
        this.previous = previous;
    }

}

public class Main {
    static ArrayList<Node> arr[];

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N];
        for(int i = 0 ; i < N; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            arr[a].add(new Node(b, c));
            arr[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        long ans = Dijkstra(start, end); // 거꾸로 하는 게 나을듯?

        if(ans == Long.MAX_VALUE){
            bw.write("DIGESTA");
        }else{
            bw.write(ans + "");
        }

        bw.flush();
        bw.close();
    }

    static long Dijkstra(int start, int end){
        if(start == end) return 0;
        
        Map<Long, Long>[] dis = new HashMap[N]; // key : 직전 값 , value : 해당 노드까지 총 cost
        for(int i = 0 ; i < N; i++){
            dis[i] = new HashMap<>();
        }

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o1.cost, o2.cost);
            }
        });

        que.add(new info(start, 0, 0));

        while(!que.isEmpty()){
            info io = que.poll();

            int from = io.i;
            long sum = io.cost;
            long previous = io.previous;

            for(Node nd : arr[from]){
                int to = nd.i;
                long cost = nd.cost;

                if(previous < cost){ // 직전 값보다 큼.

                    if(sum + cost < dis[to].getOrDefault(cost, Long.MAX_VALUE)){
                        dis[to].put(cost, sum + cost);
                        que.add(new info(to, sum + cost, cost));
                    }
                }

            }

        }

        long ans = Long.MAX_VALUE;
        if(!dis[end].isEmpty()){
            for(long val : dis[end].values()){
                ans = Math.min(ans, val);
            }
        }

        return ans;
    }
}