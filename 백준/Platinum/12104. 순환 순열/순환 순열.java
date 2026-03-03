import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String A = br.readLine();
        String pattern = br.readLine();

        int P[] = new int[pattern.length()]; // suffix & prefix 의 일치하는 길이
        int j = 0;
        for(int i = 1 ; i < pattern.length(); i++){
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)){
                j = P[j - 1];
            }
            if(pattern.charAt(i) == pattern.charAt(j)){
                P[i] = ++j;
            }
        }

        int L = A.length() - P[A.length() - 1];
        int cnt = 0;


        for(int k = 0 ; k < L ; k++){
            //bw.write(A + "\n");

            if(A.equals(pattern)) cnt += (A.length() / L);

            A = A.substring(1, A.length()) + A.charAt(0);
        }

        bw.write(cnt + "");

//        for(int i : P){
//            bw.write(i + " ");
//        }

        bw.flush();
        bw.close();
    }
}