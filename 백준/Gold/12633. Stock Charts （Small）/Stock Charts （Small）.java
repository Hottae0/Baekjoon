import java.io.*;
import java.util.*;

//12633
public class Main {
    static ArrayList<Integer> graph[];

    static int arr[];
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int price[][] = new int[n][k];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < k; j++){
                    price[i][j] = Integer.parseInt(st.nextToken());
                }

            }

            graph = new ArrayList[n];
            for(int i = 0 ; i < n ; i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < n; i++){
                for(int j = 0 ; j < n ; j++){
                    boolean dominate = true;

                    for(int day = 0; day < k; day++){
                        if(price[i][day] <= price[j][day]){
                            dominate = false;
                            break;
                        }
                    }

                    if(dominate){ // i는 j보다 항상 큼
                        graph[i].add(j);
                    }

                }
            }

            int cnt = 0;
            arr = new int[n];
            visited = new boolean[n];

            Arrays.fill(arr, -1);

            for(int i = 0; i < n; i++){
                Arrays.fill(visited, false);

                if(dfs(i)) cnt += 1;
            }

            sb.append("Case #" + t + ": ");
            sb.append(n - cnt + "\n");


        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();

    }

    static boolean dfs(int now){
        for(int nxt : graph[now]){
            if(visited[nxt]) continue;

            visited[nxt] = true;

            if(arr[nxt] == -1 || dfs(arr[nxt])){
                arr[nxt] = now;
                return true;
            }

        }

        return false;
    }

}