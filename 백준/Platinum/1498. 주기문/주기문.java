import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();

        StringBuilder sb = new StringBuilder();

        int N = S.length();
        int P[] = new int[N];

        int j = 0;
        for(int i = 1; i < N; i++){
            while (j > 0 &&  S.charAt(i) != S.charAt(j)){
                j = P[j - 1];
            }

            if(S.charAt(i) == S.charAt(j)){
                P[i] = ++j;
            }

            if(P[i] > 0 && (i + 1) % (i + 1 - P[i]) == 0){
                int len = (i + 1) - P[i];
                int repeated = len / P[i];

                sb.append((i + 1) + " " + (i + 1) / len + "\n");
            }

        }

        bw.write(sb.toString());

//        for(int i : P){
//            bw.write( i + " ");
//        }

        bw.flush();
        bw.close();
    }
}