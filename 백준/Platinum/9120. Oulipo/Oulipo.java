import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0 ; t < T; t++){
            String pattern = br.readLine();
            String S = br.readLine();

            int N = pattern.length();
            int P[] = new int[N];

            int j = 0;
            for(int i = 1; i < N; i++){
                while (j > 0 &&  pattern.charAt(i) != pattern.charAt(j)){
                    j = P[j - 1];
                }

                if(pattern.charAt(i) == pattern.charAt(j)){
                    P[i] = ++j;
                }
            }

            int cnt = 0;

            int idx = 0;
            for(int i = 0; i < S.length(); i++){
                while (idx > 0 && S.charAt(i) != pattern.charAt(idx)){
                    idx = P[idx - 1];
                }

                if(S.charAt(i) == pattern.charAt(idx)){
                    if (idx == pattern.length() - 1){
                        cnt += 1;
                        idx = P[idx];
                    }else{
                        idx += 1;
                    }
                }
            }

            sb.append(cnt + "\n");
        }


        bw.write(sb.toString().trim());

//        for(int i : P){
//            bw.write( i + " ");
//        }

        bw.flush();
        bw.close();
    }
}