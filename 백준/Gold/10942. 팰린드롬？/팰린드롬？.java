import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int A;
    static int B;
    
    static String S[];
    static Integer dp[][];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        S = new String[N];
        dp = new Integer [N][N];
        
        for(int i = 0; i < N; i++){
            S[i] =  st.nextToken();
            dp[i][i] = 1;
        }
        
        int M = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < M; i++){
            StringTokenizer tmp = new StringTokenizer(br.readLine());
            
            A = Integer.parseInt(tmp.nextToken()) - 1;
            B = Integer.parseInt(tmp.nextToken()) - 1;

            if(palindrome(A,B) == 1){
                bw.write(1 + "\n");
            }else{
                bw.write(0 + "\n");
            }
            
        }
        
        bw.flush();
        bw.close();

    }
    
    static int palindrome(int a, int b){
        if(dp[a][b] != null){
            return dp[a][b];
        }
        
        if(a + 1 == b || a+1 == b-1){
            if(S[a].equals(S[b])){
                return dp[a][b] = 1;
            }
            return dp[a][b] = 0;
        }
        
        if(!S[a].equals(S[b])){
            return dp[a][b] = 0;
        }
        
        return dp[a][b] = palindrome(a+1, b-1);
    }
/*
반례
3
1 11 111
1
1 3
ans : 0 -> 해결


*/


}