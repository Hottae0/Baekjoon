import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String pattern = br.readLine();

        int N = pattern.length();

        int max = 0;

        for(int t = 0; t < N; t++){
            int P[] = new int[pattern.length()];

            int j = 0;
            for(int i = 1; i < pattern.length(); i++){
                while (j > 0 &&  pattern.charAt(i) != pattern.charAt(j)){
                    j = P[j - 1];
                }

                if(pattern.charAt(i) == pattern.charAt(j)){
                    P[i] = ++j;
                    max = Math.max(max, P[i]);
                }
            }

            StringBuilder sb = new StringBuilder(pattern);
            pattern = sb.substring(1);
            
        }


        bw.write(max + "");


        bw.flush();
        bw.close();
    }
}