import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] graph;

    static int cnt = 0;

    static boolean check[];
    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 0 ; t < T; t++){
            cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); // 췤 수
            int M = Integer.parseInt(st.nextToken()); // 학생 수

            graph = new ArrayList[M];
            for(int i = 0 ; i < M; i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < M; i++){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                for(int j = a; j < b + 1; j++) {
                    graph[i].add(j);
                }
            }

            // 책 관련 배열들
            check = new boolean[N];
            arr = new int[N];

            Arrays.fill(arr, -1);

            for(int i = 0 ; i < M; i++){
                Arrays.fill(check, false);

                dfs(i);

                int tmp = 0;
                for(int j = 0 ; j < N; j++){
                    if(arr[j] != -1){
                        tmp += 1;
                    }
                }

                cnt = Math.max(tmp, cnt);

            }

            sb.append(cnt + "\n");

        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }

    static boolean dfs(int now){
        for(int next : graph[now]){
            if(!check[next]){
                check[next] = true;

                if(arr[next] == -1 || dfs(arr[next])){
                    // 매칭 x
                    arr[next] = now;
                    return true;
                }
            }
        }

        return false;
    }

}