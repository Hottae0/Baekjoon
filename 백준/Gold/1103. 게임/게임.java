import java.io.*;
import java.util.*;

public class Main {
    static char arr[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};

    static boolean visit[][];
    static int dp[][];

    static int N;
    static int M;
    static int max = 0;
    static boolean looped = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        dp = new int[N][M];
        visit = new boolean[N][M];

        for(int i = 0 ; i < N; i++){
            String Line = br.readLine();
            for(int j = 0; j < M; j++){
                arr[i][j] = (Line.charAt(j));
                dp[i][j] = -1;
            }
        }

        find(0, 0, 0);

        if(looped){
            bw.write("-1");
        }else{
            bw.write(max + "");
        }

        bw.flush();
        bw.close();
    }

    static void find(int i, int j, int cnt){
        if(i < 0 || i >= N || j < 0 || j >= M){ // 범위를 벗어남
            max = Math.max(cnt, max);
            return;
        }

        if(arr[i][j] == 'H'){ // 구멍에 들어감.
            max = Math.max(cnt, max);
            return;
        }

        if(visit[i][j]){ // 무한 루프 가능.
            looped = true;
            return;
        }

        if(dp[i][j] >= cnt) return;
        dp[i][j] = Math.max(dp[i][j], cnt);

        visit[i][j] = true;

        for(int a = 0 ; a < 4; a++){
            int num = Integer.parseInt(String.valueOf(arr[i][j]));

            int x = i + dx[a] * num;
            int y = j + dy[a] * num;

            find(x, y, cnt + 1);
        }

        visit[i][j] = false;
    }
}