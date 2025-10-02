import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;

    static long black_sum[][];
    static long white_sum[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int [][] black = new int[N][M];
        int [][] white = new int[N][M];

        for(int i = 0 ; i < N; i++){
            String S = br.readLine();
            for(int j = 0; j < M; j++){
                char c = S.charAt(j);

                if(i % 2 == 0){ // 짝수번째 라인
                    if(j % 2 == 0){
                        if(c == 'B') black[i][j] += 1;
                        else white[i][j] += 1;
                    }else{
                        if(c == 'W') black[i][j] += 1;
                        else white[i][j] += 1;
                    }
                }
                else{ //홀수번째 라인일때.
                    if(j % 2 == 0){
                        if(c == 'W') black[i][j] += 1;
                        else white[i][j] += 1;
                    }else{
                        if(c == 'B') black[i][j] += 1;
                        else white[i][j] += 1;
                    }
                }
            }

        }

        black_sum = new long[N + 1][M + 1];
        white_sum = new long[N + 1][M + 1];

        for(int i = 1 ; i <= N; i++){
            for(int j = 1 ; j <= M; j++){
                black_sum[i][j] = black[i - 1][j - 1] + black_sum[i - 1][j] + black_sum[i][j - 1] - black_sum[i - 1][j - 1];
                white_sum[i][j] = white[i - 1][j - 1] + white_sum[i - 1][j] + white_sum[i][j - 1] - white_sum[i - 1][j - 1];
            }
        }

        long max = Integer.MIN_VALUE;

        for(int i = 0; i + K <= N; i++){
            for(int j = 0; j + K <= M; j++){
                int r1 = i + 1, c1 = j + 1;
                int r2 = i + K, c2 = j + K;

                long b_ans = black_sum[r2][c2] - black_sum[r1 - 1][c2] - black_sum[r2][c1 - 1] + black_sum[r1 - 1][c1 - 1];
                long w_ans = white_sum[r2][c2] - white_sum[r1 - 1][c2] - white_sum[r2][c1 - 1] + white_sum[r1 - 1][c1 - 1];

                max = Math.max(max, Math.max(b_ans, w_ans));
            }
        }

        long ans = (long)Math.pow(K, 2) - max;

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }
}