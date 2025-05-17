import java.io.*;
import java.util.*;

public class Main {
    static int memorial[][];
    static int arr[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        memorial = new int[N][N];

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                max = Math.max(max, dp(i, j));
            }
        }
/*
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                bw.write(memorial[i][j] + " ");
            }bw.write("\n");
        }
*/

        bw.write(max + "");

        bw.flush();
        bw.close();
    }

    static int dp(int i, int j){
        if(memorial[i][j] == 0){
            memorial[i][j] = 1;

            for(int t = 0 ; t < 4; t++){
                int x = i + dx[t];
                int y = j + dy[t];

                if(x < 0 || x >= N || y < 0 || y >= N) continue; // 범위 밖이면 넘어가기.

                if(arr[x][y] > arr[i][j]){ // 더 높음. -> 갈 수 있는 곳.
                    memorial[i][j] = Math.max(memorial[i][j], dp(x, y) + 1);
                }

            }
        }

        return memorial[i][j];
    }


}