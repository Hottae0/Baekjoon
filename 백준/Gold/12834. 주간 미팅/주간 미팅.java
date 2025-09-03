import java.io.*;
import java.util.*;

class info{
    int to;
    int cost;

    info(int to, int cost){
        this.to = to;
        this.cost = cost;
    }
}

public class Main {

    static int N;
    static int V;


    static ArrayList<info>[] graph;
    static int [] pos;

    static long ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 팀원의 수
        V = Integer.parseInt(st.nextToken()); // 장소의 수
        int E = Integer.parseInt(st.nextToken()); // 도로의 수

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()) - 1; // KIST 위치
        int B = Integer.parseInt(st.nextToken()) - 1; // 씨알푸드 위치

        pos = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N; i++){
            pos[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        graph = new ArrayList[V];

        for(int i = 0 ; i < V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new info(to, cost));
            graph[to].add(new info(from, cost));
        }

        Dijkstra(A);
        Dijkstra(B);

        bw.write(ans + "");

        bw.flush();
        bw.close();

    }

    static void Dijkstra(int start){
        int arr[] = new int[V];
        Arrays.fill(arr, Integer.MAX_VALUE);

        arr[start] = 0;


        Queue<info> que = new ArrayDeque<>();
        que.add(new info(start, 0));

        while (que.size() > 0){
            info io = que.poll();

            int from = io.to;
            int sum = io.cost;

            if(sum > arr[from]) continue;

            for(info tmp : graph[from]){
                int to = tmp.to;
                int cost = tmp.cost;

                if(arr[to] > arr[from] + cost){
                    arr[to] = arr[from] + cost;
                    que.add(new info(to, arr[to]));
                }
            }

        }

        for(int i : pos){
            int dis = arr[i];

            if(dis == Integer.MAX_VALUE){
                ans += -1;
            }else{
                ans += dis;
            }
        }

        return;
    }


}