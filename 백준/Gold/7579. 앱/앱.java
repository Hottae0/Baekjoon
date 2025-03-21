import java.io.*;
import java.util.*;

public class Main {

    static int memorial[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 앱 개수
        int M = Integer.parseInt(st.nextToken()); // 필요 바이트

        int A[] = new int[N + 1];
        int m[] = new int[M + 1];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int sec_sum = 0;

        for(int i = 1 ; i <= N; i++){
            int tmp1 = Integer.parseInt(st1.nextToken());
            int tmp2 = Integer.parseInt(st2.nextToken());

            A[i] = tmp1;
            m[i] = tmp2;

            sec_sum += tmp2;
        }

        memorial = new int[N + 1][sec_sum + 1];

        for(int i = 1 ; i <= N; i++){
            for(int j = 0; j < sec_sum + 1; j++){

                if(j >= m[i]){ // 현재 제한에서 사용 가능할 때.
                    memorial[i][j] = Math.max(memorial[i][j], memorial[i-1][j - m[i]] + A[i]);
                }

                memorial[i][j] = Math.max(memorial[i][j], memorial[i-1][j]);
            }
        }

        int ans = 0;

        for(int i = 0; i < sec_sum + 1; i++){
            if(memorial[N][i] >= M){
                ans = i;
                break;
            }
        }

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }

}

