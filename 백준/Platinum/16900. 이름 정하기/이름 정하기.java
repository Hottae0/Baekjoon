import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken();
        long N = Long.parseLong(st.nextToken());

        long P[] = new long[S.length()];

        int j = 0;
        for(int i = 1 ; i < S.length(); i++){
            while (j > 0 && S.charAt(i) != S.charAt(j)){
                j = (int)P[j - 1];
            }

            if(S.charAt(i) == S.charAt(j)){
                P[i] = ++j;
            }
        }

//        for(int i : P){
//            bw.write(i + " ");
//        }bw.write("\n");

        long sum = S.length();
        
        sum += (S.length() - P[P.length - 1]) * (N - 1);

        bw.write(sum + "");

        bw.flush();
        bw.close();
    }
}