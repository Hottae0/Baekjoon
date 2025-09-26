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

    static int home[];
    static int store[];

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            graph[a].add(new info(b, c));
            graph[b].add(new info(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken()); //집 개수
        int q = Integer.parseInt(st.nextToken()); // 편의점 개수

        home = new int[p];
        store = new int[q];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < p ;i++){
            home[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < q ;i++){
            store[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        //Dijkstra();
        bw.write(Dijkstra() + "");

        bw.flush();
        bw.close();
    }

    static int Dijkstra(){
        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o1.cost, o2.cost);
            }
        });

        long arr[] = new long[N];
        Arrays.fill(arr, Long.MAX_VALUE);

        for(int i = 0 ; i < store.length; i++){
            que.add(new info(store[i], 0));
            arr[store[i]] = 0;
        }

        while (!que.isEmpty()){
            info io = que.poll();

            int from = io.j;
            long sum = io.cost;

            if(sum > arr[from]) continue;

            for(int i = 0; i < graph[from].size(); i++){
                info nxt = graph[from].get(i);

                int to = nxt.j;
                long cost = nxt.cost;

                if(arr[to] > arr[from] + cost){
                    arr[to] = arr[from] + cost;
                    que.add(new info(to, arr[to]));
                }
            }

        }

        long min = Long.MAX_VALUE;
        int m_idx = -1;
        
        Arrays.sort(home);

        for(int i = 0 ; i < home.length; i++){
            int idx = home[i];

            if(min > arr[idx]){
                min = arr[idx];
                m_idx = idx + 1;
            }
        }

        return m_idx;
    }


}