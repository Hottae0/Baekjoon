import java.util.*;
import java.io.*;

class info{
    int W;
    int V;

    info(int w, int v){
        this.W = w;
        this.V = v;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int M = Integer.parseInt(st.nextToken()); // 가방의 수

        info arr[] = new info[N + 1];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            arr[i + 1] = new info(W, V);
        }

        int back_lim[] = new int[M];

        int back_max = 0;
        for(int i = 0; i < M; i++){
            int tmp = Integer.parseInt(br.readLine());
            back_max = Math.max(back_max, tmp);

            back_lim[i] = tmp;
        }
        
        int dp[][] = new int[N + 1][back_max + 1]; // i의 무게가 상한일 때 담을 수 있는 최대의 가치.

        for(int i = 1; i < N + 1; i++){
            int W = arr[i].W;
            int V = arr[i].V;

            for(int j = dp[i].length - 1; j >= 0; j--){
                dp[i][j] = dp[i - 1][j];

                if(j - W >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - W] + V);
                }
            }
        }

        float max_eff = -1;
        int max_idx = 1;

        for(int i = 0; i < M; i++){
            int idx = back_lim[i];

            float eff = (float)dp[N][idx] / idx;

            if(eff > max_eff){
                max_eff = eff;
                max_idx = i + 1;
            }
        }

        bw.write(max_idx + "");

        bw.flush();
        bw.close();

    }
}