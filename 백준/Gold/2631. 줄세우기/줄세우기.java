import java.io.*;
import java.util.*;

public class Main {
    static int arr[];
    static int dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        // 배열은 1 ~ N 까지 들어있음.

        int max = 0;
        for(int i = 0; i < N; i++){
            max = Math.max(max, LIS(i));
        }

        bw.write(N - max + "");
//        
//        for(int i : dp){
//            bw.write(i + " ");
//        }

        bw.flush();
        bw.close();
    }

    static int LIS(int idx){
        if(dp[idx] == 0){
            dp[idx] = 1;

            for(int i = 0 ; i < idx; i++){
                if(arr[i] < arr[idx]){
                    dp[idx] = Math.max(dp[idx], LIS(i) + 1);
                }

            }

        }

        return dp[idx];
    }
}