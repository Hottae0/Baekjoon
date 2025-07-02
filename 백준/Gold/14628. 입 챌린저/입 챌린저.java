import java.util.*;
import java.io.*;

class info{
    int X;
    int Y;
    int cnt;


    info(int X, int Y, int cnt){
        this.X = X;
        this.Y = Y;
        this.cnt = cnt;
    }
}


public class Main {
    static int dp[][];
    static info arr[];

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 스킬의 개수
        int M = Integer.parseInt(st.nextToken()); // 적의 체력
        int K = Integer.parseInt(st.nextToken()); // 추가 되는 마나 포인트

        dp = new int[N + 1][M + 1]; // i의 피를 깎을 때는 드는 마나 값
        for (int i = 0; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        arr = new info[N + 1];
        for(int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            arr[i] = new info(m, d, 1);
        }

        // 스킬 별로 돌아가며 해당 마나에서 얼마나 쓸 수 있는지 확인해야함
        for(int i = 1 ; i < N + 1; i++){
            int skill_mp = arr[i].X;
            int skill_dmg = arr[i].Y;


            for(int j = 1; j < M + 1; j++){

                for(int cnt = j / skill_dmg; cnt >= 0 ; cnt--){
                    int total_dmg = cnt * skill_dmg;
                    int total_mp = skill_mp * cnt + K * (cnt * (cnt - 1) / 2);

                    if(total_dmg <= j && dp[i - 1][j - total_dmg] != Integer.MAX_VALUE){
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - total_dmg] + total_mp);
                    }
                }

            }

        }

        bw.write(dp[N][M] + "");


        bw.flush();
        bw.close();
    }

}