import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String fir_S = br.readLine();

        // 전처리 중간마다 문자열 하나 넣기
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < fir_S.length(); i++) {
            sb.append(fir_S.charAt(i)).append("#");
        }

        String S = sb.toString();
        int len = S.length();

        int P[] = new int[len]; // = P[i] 를 중심으로 하는 펠린드롬의 최대 반지름 길이
        int r = 0; // 현재 펠린드롬 중 가장 오른쪽의 위치
        int c = 0; // 찾은 펠린드롬의 중심

        int max_len = 0;

        for(int i = 0 ; i < len; i++){
            if(i < r) {
                P[i] = Math.min(r - i, P[2 * c - i]);
            }

            while(i - P[i] - 1 >= 0 && i + P[i] + 1 < len && S.charAt(i - P[i] - 1) == S.charAt(i + P[i] + 1)){
                P[i]++;
            }

            if(i + P[i] > r){
                r = i + P[i];
                c = i;
            }

            max_len = Math.max(max_len, P[i]);
        }

        bw.write(max_len + "");

        bw.flush();
        bw.close();
    }
}