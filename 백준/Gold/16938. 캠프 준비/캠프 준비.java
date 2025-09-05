import java.io.*;
import java.util.*;

// 백준 16938


public class Main {
    static int[] arr;

    static int N, L, R, X;
    static int rest = 0;

    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 문제 개수
        L = Integer.parseInt(st.nextToken()); // 난이도 합 하한
        R = Integer.parseInt(st.nextToken()); // 난이도 합 상한
        X = Integer.parseInt(st.nextToken()); // 난이도 차 하한.

        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            rest += arr[i];
        }

        dfs(0, 0, Integer.MAX_VALUE, -1);

        bw.write(cnt + "");

        bw.flush();
        bw.close();
    }

    // sum 은 무조건적으로 증가함.
    static void dfs(int dep, int sum, int min, int max){
        if(dep == N) {
            if(max - min >= X && L <= sum && sum <= R){
                cnt += 1;
            }

            return;
        }

        if(sum + rest < L) return;
        if(sum > R) return;

        int num = arr[dep];

        dfs(dep + 1, sum, min, max);
        dfs(dep + 1, sum + num, Math.min(min, num), Math.max(max, num));

    }

}