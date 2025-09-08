import java.io.*;
import java.util.*;

class info{
    int v;
    long cost;
    boolean isBus;

    info(int v, long cost, boolean isBus){
        this.v = v;
        this.cost = cost;
        this.isBus = isBus;
    }
}

public class Main {
    static int N, K;

    static ArrayList<info>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 첫 차 시간
        int X = Integer.parseInt(st.nextToken()); // 인도
        int Y = Integer.parseInt(st.nextToken()); // 차도

        graph = new ArrayList[N];

        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < X; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            graph[s].add(new info(e, c, false));
            graph[e].add(new info(s, c, false));
        }

        for(int i = 0 ; i < Y; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            graph[s].add(new info(e, c, true));
            graph[e].add(new info(s, c, true));

        }

        bw.write(Dijkstra(0) + "");

        bw.flush();
        bw.close();
    }




    static long Dijkstra(int start){
        long[] arr = new long[N];
        Arrays.fill(arr, Long.MAX_VALUE);
        arr[start] = 0;

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                if(o1.cost > o2.cost){
                    return 1;
                }else {
                    return -1;
                }
            }
        });

        que.add(new info(start , 0, false));

        while(que.size() > 0){
            info io = que.poll();

            int from = io.v;
            long sum = io.cost;

            if(sum > arr[from] || arr[N - 1] < arr[from]) continue; // 미리 담아진거라면 스킵하기.

            for(int i = 0; i < graph[from].size(); i++){

                int to = graph[from].get(i).v;
                long cost = graph[from].get(i).cost;
                boolean isBus = graph[from].get(i).isBus;

                if(!isBus && arr[to] > arr[from] + cost){ // 걸어 가면 된다.
                    arr[to] = arr[from] + cost;
                    que.add(new info(to, arr[from], false));

                }else if(isBus){
                    // K 이전이라면 기다리기 아니라면 그냥 타
                    if(arr[from] < K){
                        if(arr[to] > K + cost){
                            arr[to] = K + cost;
                            que.add(new info(to, arr[from], false));
                        }
                    }else{
                        if(arr[to] > arr[from] + cost) {
                            arr[to] = arr[from] + cost;
                            que.add(new info(to, arr[from], false));
                        }
                    }

                }

            }

        }


        return arr[N - 1];
    }

}