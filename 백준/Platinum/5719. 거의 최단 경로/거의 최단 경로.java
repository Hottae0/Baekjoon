import java.io.*;
import java.util.*;

class info{
    int v;
    int cost;

    info(int v, int cost){
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static int N;
    static int M;

    static ArrayList<info>[] graph;
    static ArrayList<Integer>[] remove_list;

    static boolean exRoute[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 장소의 수
            M = Integer.parseInt(st.nextToken()); // 도로의 수 (단방향)

            if(N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            exRoute = new boolean[N][N];
            remove_list = new ArrayList[N];
            graph = new ArrayList[N];

            for(int i = 0 ; i < N; i++){
                graph[i] = new ArrayList<>();
                remove_list[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < M ; i++){
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[u].add(new info(v, cost));
            }

            Dijkstra(start, end);

            Remove_Route(start, end);

            sb.append(Dijkstra(start, end) + "\n");
            
//            for(int i = 0 ; i < N; i++){
//                for(int j = 0; j < N; j++){
//                    bw.write(exRoute[i][j] + " ");
//                }bw.write("\n");
//            }
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }


    static int Dijkstra(int start, int end){
        int[] arr = new int[N];
        Arrays.fill(arr, 10_000_001);
        arr[start] = 0;

        Queue<info> que = new ArrayDeque<>();
        que.add(new info(start , 0));

        while(que.size() > 0){
            info io = que.poll();

            int from = io.v;
            int sum = io.cost;

            if(sum > arr[from]) continue; // 미리 담아진거라면 스킵하기.

            for(int i = 0; i < graph[from].size(); i++){

                int to = graph[from].get(i).v;
                int cost = graph[from].get(i).cost;

                if(exRoute[from][to]) continue;

                if(arr[to] > arr[from] + cost){
                    arr[to] = arr[from] + cost;

                    remove_list[to] = new ArrayList<>();
                    remove_list[to].add(from);

                    que.add(new info(to, arr[from]));

                }else if(arr[to] == arr[from] + cost){ // 같아도?
                    remove_list[to].add(from);
                }
            }
        }

        return (arr[end] == 10_000_001? -1 : arr[end]);
    }

    static void Remove_Route(int s, int d){
        if(s == d) return;

        for(int from : remove_list[d]){
            if(!exRoute[from][d]){
                exRoute[from][d] = true;
                Remove_Route(s, from);
            }
        }
    }

}