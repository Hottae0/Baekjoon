import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        int N;
        int num = 1;

        while ( (N = Integer.parseInt(br.readLine())) != 0){
            sb.append("Test case #" + num++ + "\n");

            String pattern = br.readLine();

            int P[] = new int[pattern.length()];

            int j = 0;

            for(int i = 1; i < P.length ; i++){
                while(j > 0 && pattern.charAt(i) != pattern.charAt(j)){
                    j = P[j - 1];
                }

                if(pattern.charAt(i) == pattern.charAt(j)){
                    P[i] = ++j;
                }
            }


            for(int i = 0; i < P.length; i++){
                // (i + 1) == 접두사 길이
                int prefix = i + 1;

                if(P[i] == 0) continue;

                int K = prefix / (i - P[i] + 1);
                if(K > 1 && (prefix) % (i - P[i] + 1) == 0) sb.append(prefix + " " + K +"\n");



            }

            sb.append("\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }
}