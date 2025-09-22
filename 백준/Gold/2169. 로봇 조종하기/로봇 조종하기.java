import java.io.*;
import java.util.*;

public class Main {
    static int arr[][];
    static int dp[][];

    static int N, M;

    static boolean visited[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dp = new int[N][M];

        visited = new boolean[N][M];

        for(int i = 0 ; i < N; i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        dp[0][0] = arr[0][0];
        for(int i = 1 ; i < M ; i++){
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }

        for(int i = 1; i < N; i++){
            travel(i);
        }

        bw.write(dp[N - 1][M - 1] + "");

//        bw.write("------------- arr -------------\n");
//        for(int i = 0 ; i < N; i++){
//            for(int j : arr[i]){
//                bw.write(j + " ");
//            }bw.write("\n");
//        }
//
//        bw.write("\n---------- dp ------------\n");
//        for(int i = 0 ; i < N; i++){
//            for(int j : dp[i]){
//                bw.write(j + " ");
//            }bw.write("\n");
//        }

        bw.flush();
        bw.close();
    }


    static void travel(int dep){
        int left_to_right[] = new int[M];
        int right_to_left[] = new int[M];

        left_to_right[0] = dp[dep - 1][0] + arr[dep][0];

        for(int i = 1; i < M; i++){
            left_to_right[i] = Math.max(left_to_right[i - 1], dp[dep - 1][i]) + arr[dep][i];
        }

        if(dep != N - 1){
            right_to_left[M - 1] = dp[dep - 1][M - 1] + arr[dep][M - 1];
            for(int i = M - 2; i >= 0; i--){
                right_to_left[i] = Math.max(right_to_left[i + 1], dp[dep - 1][i]) + arr[dep][i];
            }

            for(int i = 0 ; i < M; i++){
                dp[dep][i] = Math.max(left_to_right[i], right_to_left[i]);
            }
        }else{
            for(int i = 0 ; i < M; i++){
                dp[dep][i] = left_to_right[i];
            }
        }


    }



}