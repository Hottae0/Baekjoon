import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();
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

        int cnt = 0;
        StringBuilder ans = new StringBuilder();

        int idx = 0;
        for(int i = 0 ; i < S.length(); i++){
            // 다를 때!! patter을 가르키는 idx를 길이만큼 뒤로
            while (idx > 0 && S.charAt(i) != pattern.charAt(idx)){
                idx = P[idx - 1];
            }

            // 같음
            if(S.charAt(i) == pattern.charAt(idx)){
                if(idx == pattern.length() - 1){
                    cnt += 1;
                    ans.append( (i - pattern.length() + 2) + " ");
                    idx = P[idx];
                }else{
                    idx++;
                }
            }

        }

        bw.write(cnt + "\n");
        bw.write(ans.toString().trim());

//        for(int i : P){
//            bw.write(i + " ");
//        }

        bw.flush();
        bw.close();
    }
}