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

// 2158 산악자전거
public class Main {
    static ArrayList<info>[] graph;
    static ArrayList<Integer> list;

    static int N, M;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 관계의 수
            M = Integer.parseInt(st.nextToken()); // 사람의 수

            graph = new ArrayList[M];
            for(int i = 0 ; i < M; i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < N; i++){
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                graph[x].add(new info(y, z));
                graph[y].add(new info(x, z));
            }

            ans.append("Case #" + (t + 1) + ": ");
            Dijkstra(0);

        }

        bw.write(ans.toString().trim());

        bw.flush();
        bw.close();
    }

    static void Dijkstra(int start){
        int[] arr = new int[M];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[start] = 0;

        boolean[] visited = new boolean[M];

        ArrayList<Integer>[] list = new ArrayList[M];

        for(int i = 0 ; i < M ; i++){
            int from = 0;
            int min = Integer.MAX_VALUE;

            for(int j = 0 ; j < M; j++){
                if(!visited[j] && min > arr[j]){
                    from = j;
                    min = arr[j];
                }
            }

            visited[from] = true;

            for(info io : graph[from]){
                int to = io.to;
                int cost = io.cost;

                if(arr[to] > arr[from] + cost){
                    arr[to] = arr[from] + cost;

                    list[to] = new ArrayList<>();
                    list[to].add(from);
                }
            }


        }

        if(arr[M - 1] == Integer.MAX_VALUE){
            ans.append("-1\n");
            return;
        }

        Stack<Integer> stack = new Stack<>();

        int to = M - 1;

        while(to != 0){
            stack.add(to);
            to = list[to].get(0);
        }

        ans.append("0 ");
        while (stack.size() > 0){
            ans.append(stack.pop() + " ");
        }
        ans.append("\n");

    }

}