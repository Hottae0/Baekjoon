import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder(br.readLine());

        String S = sb.toString();
        String pattern = sb.toString();

        int P[] = new int[pattern.length()];
        int j = 0;

        for(int i = 1; i < pattern.length(); i++){
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)){
                j = P[j - 1];
            }

            if(pattern.charAt(i) == pattern.charAt(j) ){
                P[i] = ++j;
            }
        }
        int len = P[pattern.length() - 1]; // 최초 suffix의 길이
        int L = pattern.length() - P[P.length - 1];

        int max_len = 0;
        for(int i = 1; i < S.length() - 1; i++){
            max_len = Math.max(P[i], max_len);
        }


        int res_len = P[P.length - 1];
        while (res_len > 0){
            if(max_len >= res_len){
                bw.write(sb.substring(S.length() - res_len).toString() + "");

                bw.flush();
                bw.close();
                return;
            }
            res_len = P[res_len - 1];
        }

        bw.write("-1");

        bw.flush();
        bw.close();
    }
}