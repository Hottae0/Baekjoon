import java.io.*;
import java.util.*;

public class Main {

    static int N, S ,T;

    static int arr[];
    static long dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder ans = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;
            S = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            dp = new long[N + 1][T];
            for(int i = 0 ; i < N + 1; i++){
                Arrays.fill(dp[i], Integer.MIN_VALUE);
            }

            int input_cnt = 0;

            // 배열 입력 받기.
            while (input_cnt < N){
                st = new StringTokenizer(br.readLine());
                int num = st.countTokens();

                for(int i = 0; i < num; i++){
                    arr[input_cnt++] = Integer.parseInt(st.nextToken());
                }
            }

            ans.append(roll_dice(N, 0) + "\n");
            
        }

        bw.write(ans.toString().trim());

        bw.flush();
        bw.close();
    }

    static long roll_dice(int s, int cnt){

        if(s < 0 && cnt <= T) return 0;
        if(cnt >= T) return Integer.MIN_VALUE;

        if(dp[s][cnt] == Integer.MIN_VALUE){

            for(int i = 1 ; i < S + 1; i++){
                dp[s][cnt] = Math.max(dp[s][cnt], roll_dice(s - i, cnt + 1) + arr[s]);
            }

        }

        return dp[s][cnt];
    }
}