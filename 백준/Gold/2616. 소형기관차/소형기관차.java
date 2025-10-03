import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    static long ans[][];
    static long ac_sum[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long tmp_sum[] = new long[N + 1];

        for(int i = 1; i <= N; i++){
            tmp_sum[i] = tmp_sum[i - 1] + Long.parseLong(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());

        ans = new long[N + 1 - K][3];
        ac_sum = new long[N + 1 - K];


        for(int i = 0 ; i < ans.length ; i++){

            Arrays.fill(ans[i], -1);
            ans[i][0] = ac_sum[i] = tmp_sum[K + i] - tmp_sum[i];

            if(i != 0){
                ans[i][0] = Math.max(ans[i][0], ans[i-1][0]);
            }

        }

        long max = -1;

        for(int i = 2 * K; i < ans.length; i++){
            max = Math.max(max, find(i, 2));
        }


//        for(int i = 0; i < 3; i++){
//            for(int j = 0; j < N + 1 - K; j++){
//                bw.write(ans[j][i] + " ");
//            }bw.write("\n");
//        }

        bw.write(max + "");

        bw.flush();
        bw.close();
    }

    static long find(int now, int cnt){
        if(ans[now][cnt] == -1){
            //1. ans[now - 1][cnt] -> 이전 최댓값
            //2. ans[now][0] + find(now - K, cnt + 1)
            //3. ans[now][cnt]

            ans[now][cnt] = Math.max(ans[now - 1][cnt], ac_sum[now] + find(now - K, cnt - 1));

        }

        return ans[now][cnt];
    }


}