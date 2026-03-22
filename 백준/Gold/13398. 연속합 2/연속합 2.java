import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int arr[] = new int[N];
        int dp[][] = new int[N][2]; // i 번째에서 시작한 배열 -> 지운게 없다 0, 지운게 있다 1

        StringTokenizer st = new StringTokenizer(br.readLine());


        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = arr[0];
        //dp[0][1] = 0;

        long max = arr[0];
        for(int i = 1 ; i < N; i++){

            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]); // 이전까지 안지운 값을 이어가거나 다시 시작
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]); // 이전까지 지운 값이 있다면 그냥 더하거나, i번째를 지우거나

            int tmp = Math.max(dp[i][0], dp[i][1]);
            max = Math.max(max, tmp);
        }

        bw.write(max + "");

        bw.flush();
        bw.close();
    }
}