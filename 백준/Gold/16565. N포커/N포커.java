import java.io.*;
import java.util.*;


public class Main {
    static long mod = 10_007;
    static long ans[] = new long[53];
    static long pascal[][] = new long[53][53];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 52; i++) {
            // 규칙 1. 각 행의 첫 번쨰와 마지막 숫자는 1
            pascal[i][0] = 1;
            pascal[i][i] = 1;
            // 규칙 2. 중간에 위치한 숫자는 이전 행의 왼쪽에 있는 숫자와 바로 위에 있는 숫자의 합
            for (int j = 1; j < i; j++) {
                pascal[i][j] = (pascal[i - 1][j - 1] + pascal[i - 1][j]) % mod;
            }
        }

        long sum = 0;

        for(int i = 1 ; i <= N/4; i++){ // 포카드 갯수 / N/4 가 최대 갯수이다.
            long four_cards = pascal[13][i];
            long else_cards = pascal[52 - i * 4][N - i * 4]; // 여기서 틀린듯!

            long cnt = (four_cards * else_cards) % mod;

            // 포함 - 배제의 원리
            if(i % 2 == 1){ // 홀수 -> 더하기
                sum = (sum + cnt) % mod;
            }else{
                sum = (sum - cnt + mod) % mod; // - 대비
            }

        }

        bw.write(sum + "");

        bw.flush();
        bw.close();


    }

}