import java.io.*;
import java.util.*;

public class Main {
    static long ans = 0;

    static int N;
    static int K;

    static boolean visitied[];
    static String arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        arr = new String[N];
        visitied = new boolean[N];

        for(int i = 0 ; i < N; i++){
            arr[i] = br.readLine();
        }

        K = Integer.parseInt(br.readLine());

        dfs("", 0);

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }

    static void dfs(String S, int dep){
        if(dep == N){
            int P[] = new int[S.length()];

            int a = 0;
            for(int b = 1; b < S.length(); b++){
                while (a > 0 && S.charAt(a) != S.charAt(b)){
                    a = P[a - 1];
                }

                if(S.charAt(a) == S.charAt(b)){
                    P[b] = ++a;
                }
            }

            int len = S.length() - P[S.length() - 1];

            int cnt = (S.length() % len == 0) ? S.length() / len : 1;

            if(cnt == K) ans += 1;

            return;
        }


        for(int j = 0; j < N; j++){
            if(!visitied[j]){
                visitied[j] = true;
                dfs(S + arr[j], dep + 1);
                visitied[j] = false;
            }
        }

    }


}