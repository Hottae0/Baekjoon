import java.io.*;
import java.util.*;

public class Main {
    static int arr[];
    static long dp[][][][][][][];

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        arr = new int[5];
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new long[11][11][11][11][11][6][6]; // 1번째 구술 ..... 5번째 구슬/이전 구슬 / 이이전 구술
        int MAX = 11;
        for (int a = 0; a < MAX; a++)
            for (int b = 0; b < MAX; b++)
                for (int c = 0; c < MAX; c++)
                    for (int d = 0; d < MAX; d++)
                        for (int e = 0; e < MAX; e++)
                            for (int f = 0; f < 6; f++)
                                for (int g = 0; g < 6; g++)
                                    dp[a][b][c][d][e][f][g] = -1;

        long ans = make_bizz(5, 5);

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }


    static long make_bizz(int prev, int pre_prev){
        if(Arrays.stream(arr).sum() == 0L) return 1; // 모든 구슬 사용!

        long status = dp[arr[0]][arr[1]][arr[2]][arr[3]][arr[4]][prev][pre_prev]; // 방문 여부도 판단.

        if(status != -1) return status; // 이미 방문에서 dp를 돌린 배열.

        long res = 0;
        for(int i = 0 ; i < N; i++){
            if(arr[i] > 0 && prev != i && pre_prev != i){
                arr[i]--;
                res += make_bizz(i, prev);
                arr[i]++;
            }
        }

        return dp[arr[0]][arr[1]][arr[2]][arr[3]][arr[4]][prev][pre_prev] = res;
    }
}