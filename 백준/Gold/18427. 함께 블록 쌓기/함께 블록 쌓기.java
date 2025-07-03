import java.util.*;
import java.io.*;


public class Main {
    static int dp[][];
    static int arr[][];

    static int mod = 10_007;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 학생 당 최대 블록 개수
        int H = Integer.parseInt(st.nextToken()); // 원하는 높이

        arr = new int[N + 1][M];

        for(int i = 1 ; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            int tmp = st.countTokens();

            for(int j =  0; j < tmp; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        dp = new int[N + 1][H + 1]; // i의 높이를 만들 수 있는 가짓수.
        for(int i = 0 ; i < N + 1; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i < N + 1; i++){

            for(int j = 0; j < M; j++){
                if(arr[i][j] == 0) break; // 주어진 블록이 끝남.

                int block = arr[i][j];

                for(int k = H ; k >= block; k--){
                    dp[i][k] +=  dp[i - 1][k - block];
                }

            }

            for(int j = 0 ; j < H + 1; j++){
                dp[i][j] += dp[i - 1][j];
                dp[i][j] %= mod;
            }
            dp[i][0] = 1;

        }


        bw.write(dp[N][H] + "");

        bw.flush();
        bw.close();

    }
}