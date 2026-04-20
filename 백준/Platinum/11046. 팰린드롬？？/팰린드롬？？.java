import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int S[] = new int[N * 2 + 1];

        // 전처리 중간마다 문자열 하나 넣기
        for (int i = 1; i < S.length; i += 2) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        int len = S.length;

        int P[] = new int[len]; // = P[i] 를 중심으로 하는 펠린드롬의 최대 반지름 길이
        int r = 0; // 현재 펠린드롬 중 가장 오른쪽의 위치
        int c = 0; // 찾은 펠린드롬의 중심


        for(int i = 0 ; i < len; i++){
            if(i < r) {
                P[i] = Math.min(r - i, P[2 * c - i]);
            }

            while(i - P[i] - 1 >= 0 && i + P[i] + 1 < len && S[i - P[i] - 1] == S[i + P[i] + 1] ){
                P[i]++;
            }

            if(i + P[i] > r){
                r = i + P[i];
                c = i;
            }

        }

//        for(int i : P){
//            bw.write(i + " ");
//        }bw.write("\n");

        int M = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int cen = s + e - 1;
            int diff = e - s + 1;

            if(P[cen] >= diff){
                ans.append("1\n");
            }else{
                ans.append("0\n");
            }

        }

        bw.write(ans.toString().trim());

        bw.flush();
        bw.close();
    }
}