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
    static ArrayList<ArrayList<info>> graph = new ArrayList<>();
    static int in_degree[];

    static int dp[];
    static int back_idx[];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine()); // 도로의 개수

        for(int i = 0 ; i < N + 1; i++){
            graph.add(new ArrayList<>());
        }
        dp = new int[N + 1];
        back_idx = new int[N + 1];
        in_degree = new int[N + 1];

        for(int i = 0 ; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new info(b, cost));

            in_degree[b]++;
        }

        topological_sort();

        StringBuilder sb = new StringBuilder();

        int from = back_idx[1];
        Stack<Integer> stack = new Stack<>();

        stack.add(1);
        while (from != 1){
            stack.add(from);
            from = back_idx[from];
        }
        stack.add(1);

        while (!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }

        bw.write(dp[1] + "\n" + sb.toString().trim());

        bw.flush();
        bw.close();
    }

    static void topological_sort(){
        Queue<Integer> que = new LinkedList<>();
        que.add(1);

        while(que.size() > 0){
            int from = que.poll();

            if(from == 1 && in_degree[from] == 0) break;

            for(info i : graph.get(from)){
                int to = i.to;
                int cost = i.cost;

                in_degree[to] -= 1;

                if(dp[to] < dp[from] + cost){
                    dp[to] = dp[from] + cost;
                    back_idx[to] = from;
                }

                if(in_degree[to] == 0){
                    que.add(to);
                }
            }
        }


    }
}