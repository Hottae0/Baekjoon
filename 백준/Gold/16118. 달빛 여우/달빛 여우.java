import java.io.*;
import java.util.*;

class info{
    int v;
    long cost;
    int isFast;

    info(int v, long cost){
        this.v = v;
        this.cost = cost;
    }

    info(int v, long cost, int isFast){
        this.v = v;
        this.cost = cost;
        this.isFast = isFast;
    }

}

public class Main {
    static int N, M;

    static long fox[];
    static long wolf[][];
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
            int c = Integer.parseInt(st.nextToken()) * 2;

            graph[s].add(new info(e, c));
            graph[e].add(new info(s, c));
        }

        Dijkstra_fox(0);
        Dijkstra_wolf(0);

        int cnt = 0;

        for(int i = 0 ; i < N; i++){
            long wolf_min = Math.min(wolf[i][0], wolf[i][1]);
            if(wolf_min > fox[i]) cnt += 1;

//            bw.write(fox[i] + " " + wolf[i][0] + " " + wolf[i][1] +  "\n");
        }

        bw.write(cnt + "");

        bw.flush();
        bw.close();


    }

    static void Dijkstra_fox(int start){
        fox = new long[N];
        Arrays.fill(fox, Long.MAX_VALUE);
        fox[start] = 0;

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                if(o1.cost > o2.cost){
                    return 1;
                }
                return -1;
            }
        });

        que.add(new info(start , 0));

        while (!que.isEmpty()){

            info io = que.poll();

            int from = io.v;
            long sum = io.cost;

            if(sum > fox[from]) continue;

            for(int i = 0 ; i < graph[from].size(); i++){
                info tmp = graph[from].get(i);

                int to = tmp.v;
                long cost = tmp.cost;

                if(fox[to] > fox[from] + cost){
                    fox[to] = fox[from] + cost;
                    que.add(new info(to, fox[to]));
                }
            }

        }

    }

    static void Dijkstra_wolf(int start){
        wolf = new long[N][2];
        for(int i = 0 ; i < N; i++){
            Arrays.fill(wolf[i], Long.MAX_VALUE);
        }
        wolf[start][1] = 0;

        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                if(o1.cost > o2.cost){
                    return 1;
                }
                return -1;
            }
        });


        que.add(new info(start , 0, 1));

        while (!que.isEmpty()){
            info io = que.poll();

            int from = io.v;
            long sum = io.cost;
            int isFast = io.isFast;

            if(wolf[from][isFast] < sum) continue;

            for(int i = 0 ; i < graph[from].size(); i++){
                info tmp = graph[from].get(i);

                int to = tmp.v;
                long cost = tmp.cost;

                if(isFast == 1){
                    cost /= 2;
                }else{
                    cost *= 2;
                }

                if(wolf[to][(isFast+1)%2] > wolf[from][isFast] + cost){
                    wolf[to][(isFast+1)%2] = wolf[from][isFast] + cost;
                    que.add(new info(to, wolf[to][(isFast+1)%2], (isFast+1)%2) );

                }

            }

        }

    }

}