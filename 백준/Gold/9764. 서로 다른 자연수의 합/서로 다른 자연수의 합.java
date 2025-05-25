import java.io.*;
import java.util.*;

public class Main {
    static int dp[];
    static int mod = 100_999;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        dp = new int[2001]; // 같은 숫자는 못 더함!
        dp[0] = 1;

        for(int i = 1; i <= 2000; i++){
            for(int j = 2000 ; j >= i; j--){
                dp[j] += dp[j - i];
                dp[j] = dp[j] % mod;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(br.readLine());

            sb.append(dp[num] + "\n");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();

    }

}