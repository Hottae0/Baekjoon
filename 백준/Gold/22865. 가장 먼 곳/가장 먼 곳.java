import java.io.*;
import java.util.*;

class info{
    int j;
    int cost;

    info(int j, int cost){
        this.j = j;
        this.cost = cost;
    }

}

public class Main {
    static ArrayList<info> graph[];

    static int N;

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String S = br.readLine();

        graph = new ArrayList[N];
        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new info(b, c));
            graph[b].add(new info(a, c));
        }

        bw.write(Dijkstra(S) + "");

        bw.flush();
        bw.close();
    }

    static int Dijkstra(String tmp){
        PriorityQueue<info> que = new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });

        int arr[] = new int[N];
        Arrays.fill(arr, Integer.MAX_VALUE);

        StringTokenizer st = new StringTokenizer(tmp);

        int start[] = new int[st.countTokens()];

        for(int i = 0 ; i < start.length; i++){
            start[i] = Integer.parseInt(st.nextToken()) - 1;

            arr[start[i]] = 0;

            que.add(new info(start[i], 0));
        }


        for(int i = 0 ; i < start.length; i++){

        }

        while(!que.isEmpty()){
            info io = que.poll();

            int from = io.j;
            int sum = io.cost;

            if(sum > arr[from]) continue;

            for(int i = 0 ; i < graph[from].size(); i++){
                info nxt = graph[from].get(i);

                int to = nxt.j;
                int cost = nxt.cost;

                if(arr[to] > arr[from] + cost){
                    arr[to] = arr[from] + cost;
                    que.add(new info(to, arr[to]));
                }
            }
        }

        int max = 0;
        int idx = 0;
        for(int i = 0 ; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
                idx = i;
            }

            //System.out.print(i + " " + arr[i] + "\n");
        }




        return idx + 1;

    }
}