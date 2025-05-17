import java.io.*;
import java.util.*;

public class Main {

    static int memorial[][];

    static int mod = 1_000_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        memorial = new int[N + 1][K + 1];

        for(int i = 0 ; i < K + 1; i++){
            memorial[0][i] = 1;
        }
        for(int i = 0 ; i < N + 1; i++){
            memorial[i][1] = 1;
        }

        int ans = dp(N, K);

        System.out.println(ans);

    }

    static int dp(int N, int K){
        if(memorial[N][K] == 0){
            return memorial[N][K] = (dp(N, K - 1)%mod + dp(N - 1, K)%mod ) % mod;
        }

        return memorial[N][K];
    }
}