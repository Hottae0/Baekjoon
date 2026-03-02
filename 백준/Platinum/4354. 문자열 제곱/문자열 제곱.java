import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        String pattern;


        while (!(pattern = br.readLine()).equals(".")){

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

//            for(int i : P){
//                bw.write(i + " ");
//            }bw.write("\n");

            int n = pattern.length();
            int len = n - P[n - 1];

            if(n % len == 0){
                sb.append((n / len) + "\n");
            }else{
                sb.append("1\n");
            }

        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }
}