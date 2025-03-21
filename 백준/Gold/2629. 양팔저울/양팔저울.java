import java.io.*;
import java.util.*;

public class Main {

    static int weight[];
    static int bead[];

    static boolean dp[][];

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        weight = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        bead = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            bead[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N + 1][40_001]; // i개의 구슬을 써서 두 저울의 차이가 j이다.

        dp(0, 0);

        String ans = "";

        for(int i = 0 ; i < M; i++){
            boolean check = false;

            for(int j = 0; j < N + 1; j++){
                if(dp[j][bead[i]]){
                    check = true;
                    break;
                }
            }

            if(check){
                ans += "Y ";
            }else{
                ans += "N ";
            }

        }

        bw.write(ans.trim());

        bw.flush();
        bw.close();
    }


    static void dp(int n, int sum){
        if(dp[n][sum]) return;

        dp[n][sum] = true;

        if(n == N) return;

        dp(n + 1, sum);
        dp(n + 1, sum + weight[n]);
        dp(n + 1, Math.abs(sum - weight[n]));

    }

}

