import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int S[] = new int[N * 2];
        int P[] = new int[N * 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            S[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = N ; i < S.length; i++){
            S[i] = S[i - N];
        }

        int pattern [] =new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N; i++){
            pattern[i] = Integer.parseInt(st.nextToken());
        }

        int j = 0;
        for(int i = 1; i < pattern.length; i++){
            while (j > 0 &&  pattern[i] != pattern[j]){
                j = P[j - 1];
            }

            if(pattern[i] == pattern[j]){
                P[i] = ++j;
            }
        }

        int idx = 0;

        String ans = "NO";
        for(int i = 0; i < S.length; i++){
            while (idx > 0 && S[i] != pattern[idx]){
                idx = P[idx - 1];
            }

            if(S[i] == pattern[idx]){
                if(N - 1 == idx){
                    ans = "YES";
                    break;
                }
                else{
                    idx += 1;
                }
            }
        }

        bw.write(ans);

//        for(int i : S){
//            bw.write(i +  " ");
//        }

        bw.flush();
        bw.close();
    }
}