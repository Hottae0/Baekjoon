import java.io.*;
import java.util.*;

public class Main {
    static boolean[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); // S의 길이
            int M = Integer.parseInt(st.nextToken()); // N의 길이

            String S = br.readLine(); // 여기서
            String U = br.readLine(); // 이걸로

            dp = new boolean[N + 1][M + 1][2]; // i까지 이용해서 j까지 만들 수 있나?
            dp[0][0][0] = true;

            for(int i = 1; i <= N; i++){
                for(int j = 0; j <= M; j++){

                    // S[i - 1] -> 을 살려서 진행
                    if(j > 0 && S.charAt(i - 1) == U.charAt(j - 1)){
                        if(dp[i - 1][j - 1][0] || dp[i - 1][j - 1][1]){
                            dp[i][j][0] = true;
                        }
                    }

                    if(dp[i - 1][j][0]){
                        dp[i][j][1] = true;
                    }

                }
            }

            if(dp[N][M][0] || dp[N][M][1]) sb.append("YES\n");
            else sb.append("NO\n");


        }

        bw.write(sb.toString().trim());


        bw.flush();
        bw.close();
    }
}