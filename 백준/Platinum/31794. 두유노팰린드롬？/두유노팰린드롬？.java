import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String fir_s = br.readLine();


        StringBuilder sb = new StringBuilder("#");
        // 전처리 중간마다 문자열 하나 넣기
        for (int i = 0; i < fir_s.length(); i++) {
            sb.append(fir_s.charAt(i)).append("#");
        }

        String S = sb.toString();
        int len = S.length();

        int P[] = new int[len]; // = P[i] 를 중심으로 하는 펠린드롬의 최대 반지름 길이
        int r = 0; // 현재 펠린드롬 중 가장 오른쪽의 위치
        int c = 0; // 찾은 펠린드롬의 중심

        long [] ac_sum = new long[len * 2];

        for(int i = 0 ; i < len; i++){
            if(i < r) {
                P[i] = Math.min(r - i, P[2 * c - i]);
            }

            while((i - P[i] - 1 >= 0 && i + P[i] + 1 < len) && S.charAt(i - P[i] - 1) == S.charAt(i + P[i] + 1) ){
                P[i]++;
            }

            if(i + P[i] > r){
                r = i + P[i];
                c = i;
            }

            // 2계 차분 배열
            if (P[i] > 0) {
                int L = i - P[i] + 1;
                int R = i + P[i] - 1;

                if(L % 2 == 0) L ++;
                if(R % 2 == 0) R --;

                if (L <= R) {
                    if (i % 2 != 0) { // 1. 홀수 길이 팰린드롬 (삼각형 모양 업데이트)
                        ac_sum[L]++;
                        ac_sum[i + 2] -= 2; // 중심에서 기울기 꺾임
                        ac_sum[R + 4]++;    // 끝에서 기울기 복구
                    } else { // 2. 짝수 길이 팰린드롬 (사다리꼴 모양 업데이트)
                        ac_sum[L]++;
                        ac_sum[i + 1]--;    // 짝수 중심은 고점이 평평하므로 두 번에 나눠 꺾음
                        ac_sum[i + 3]--;
                        ac_sum[R + 4]++;
                    }
                }

            }

        }

        StringBuilder ans = new StringBuilder();

        //기울기를 합쳐서 변화량 표시 -> 변화량을 합쳐서 갯수 구하기
        for(int i = 0 ; i < 2; i ++){
            for(int j = 3; j < ac_sum.length; j += 2){
                ac_sum[j] += ac_sum[j - 2];
            }
        }


        int M = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < M; i++){
            int q = Integer.parseInt(br.readLine()) * 2 - 1;

            ans.append(ac_sum[q] + ac_sum[q - 1] + "\n");
        }


//        for(long i : ac_sum){
//            bw.write(i + " ");
//        }bw.write("\n");

        bw.write(ans.toString().trim());


        bw.flush();
        bw.close();
    }
}