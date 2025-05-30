import java.io.*;
import java.util.*;

class info implements Comparable<info>{
    int v;
    long cost;

    info(int v, long cost){
        this.v = v;
        this.cost = cost;
    }


    @Override
    public int compareTo(info o) {
        return Long.compare(this.cost, o.cost); // 오름차순
    }
}

public class Main {
    static ArrayList<ArrayList<info>> dis = new ArrayList<>();
    static int N;

    static long arr[];
    static boolean visited[];

    static long ans[];

    static PriorityQueue<info> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N; i++){
            dis.add(new ArrayList<>());
        }
        arr = new long[N];
        ans = new long[N];
        Arrays.fill(ans, Integer.MAX_VALUE);

        visited = new boolean[N];

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int U = Integer.parseInt(st.nextToken()) - 1;
            int V = Integer.parseInt(st.nextToken()) - 1;
            long C = Integer.parseInt(st.nextToken());

            dis.get(V).add(new info(U,C));
        }

        for(int i = 0; i < N; i++){
            arr[i] = Long.MAX_VALUE;
        } // 거리정보 배열 초기화 + 출발 지점은 비용 0;

        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < K; i++){
            int start = Integer.parseInt(st.nextToken()) - 1;

            arr[start] = 0;
            pq.add(new info(start, 0));
        }

        Dijkstra();


    }

    static void Dijkstra(){
        while (pq.size() > 0){
            info now = pq.poll();

            if(!visited[now.v]){
                visited[now.v] = true;

                for(info next : dis.get(now.v)){
                    if(arr[next.v] > arr[now.v] + next.cost){
                        arr[next.v] = arr[now.v] + next.cost;
                        pq.add(new info(next.v, arr[next.v]));
                    }
                }
            }

        }

        int max_idx = -1;
        long max = 0;

        for(int i = 0; i < N; i++){
            if(max < arr[i]){
                max = arr[i];
                max_idx = i + 1;
            }
        }

        System.out.println(max_idx);
        System.out.print(max);

    }

}