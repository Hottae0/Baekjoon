import java.io.*;
import java.util.*;


public class Main {

    static long memorial[][];
    static int cost[][];
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        cost = new int[N][3];

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 3; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long ans = Long.MAX_VALUE;

        for(int i = 0; i < 3; i++){
            memorial = new long[N][3];

            for(int j = 0 ; j < 3; j++){
                memorial[0][j] = cost[0][j];
            }

            ans = Math.min(dp(N-1, i, i), ans);
        }

        bw.write(ans + "");

        bw.flush();
        bw.close();

    }

    static long dp(int r, int c, int start){
        if(memorial[r][c] == 0){

            if(c == 0){
                memorial[r][c] = Math.min(dp(r-1, 1, start), dp(r-1, 2, start)) + cost[r][c];
            }else if(c == 1){
                memorial[r][c] = Math.min(dp(r-1, 0, start), dp(r-1, 2, start)) + cost[r][c];
            }else if(c == 2){
                memorial[r][c] = Math.min(dp(r-1, 0, start), dp(r-1,1, start)) + cost[r][c];
            }

        }

        if(r == 0 && c == start){
             return Integer.MAX_VALUE;
        }else{
            return memorial[r][c];
        }


    }


}

