import java.io.*;
import java.util.*;

public class Main {
    static ArrayList <Integer> graph[];

    static int N;

    static int dp[];
    static int in_degree[];
    static int time[];
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N];

        in_degree = new int[N];
        graph = new ArrayList[N];

        for(int i = 0 ; i < N ; i++){
            graph[i] = new ArrayList<>();
        }

        time = new int[N];

        for(int i = 0 ; i < N ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());

            int cnt = Integer.parseInt(st.nextToken());
            in_degree[i] = cnt;
            for(int j = 0; j < cnt; j++){
                graph[Integer.parseInt(st.nextToken()) - 1].add(i);
            }
        }

        bw.write(topological() + "" );

        bw.flush();
        bw.close();
    }

    static int topological(){
        Queue<Integer> que = new ArrayDeque<>();

        int max = 0;

        for(int i = 0 ; i < N; i++){
            if(in_degree[i] == 0){
                que.add(i);
                dp[i] = time[i];

                max = Math.max(dp[i], max);
            }
        }


        while(que.size() > 0){

            int idx = que.poll();

            for(Integer i : graph[idx]){
                in_degree[i] -= 1;

                dp[i] = Math.max(dp[idx], dp[i]);

                if(in_degree[i] == 0){
                    que.add(i);
                    dp[i] += time[i];

                    max = Math.max(dp[i], max);
                }
            }

        }


        return max;
    }
}