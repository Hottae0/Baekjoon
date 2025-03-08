import java.io.*;
import java.util.*;

public class Main {

    static int com[][];
    static int N;
    static int L;
    static long I;

    static String ans = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 자리 수
        L = Integer.parseInt(st.nextToken()); // 1의 최대 개수
        I = Long.parseLong(st.nextToken()); // I 번째 이진수

        com = new int[N + 2][N + 2];

        com[0][0] = 1;

        for(int i = 1; i < N + 2; i++) {
            com[i][0] = 1;
            com[i][i] = 1;
        }

        for(int i = 2; i < N + 2; i++){
            for(int j = 1; j < N + 2; j++){
                com[i][j] = com[i-1][j] + com[i-1][j-1];
            }
        }

        // 삼각형인데?
        //        1
        // 여기부터 자릿수에 따른 갯수가 충족 된다.
        //       1 1  -> 한자리수 1, 0
        //      1 2 1 -> 두 자리수 11 / 10, 01/ 00
        //     1 3 3 1 ->세 자리수 111 / 110, 101, 011 / 100, 010, 001 / 000
        //    1 4 6 4 1 -> 네 자리수
        //   1 5 10 10 5 1 -> 다섯 자리수

        find(N, L, I);

        /*
        for(int i = 0 ; i < N + 2; i ++){
            for(int j = 0 ; j < N + 2; j++){
                bw.write(com[i][j] + " ");
            }bw.write("\n");
        }
         */

        bw.write(ans + "");

        bw.flush();
        bw.close();

    }

    static void find(int N, int L, long I){ // N == 남은 자리수, L == 남은 1 개수
        if(N == 0){
            return;
        } // 자리수 다 채움.

        if(L == 0){
            for(int i = 0 ; i < N; i++){
                ans += "0";
            }
            return;
        } // 1을 다 썼으니 0으로만 채워도 괜찮다.

        long sum = 0;

        for(int i = 0 ; i <= L; i++) {
            sum += com[N - 1][i];
        } // 자리수보다 하나 적을 떄,  L개 이하의 경우의 수 합.

        if(I > sum){ // 찾을려는 게 1을 더 쓰는 걸로 있다.
            ans += "1";
            find(N - 1, L - 1 , I - sum);
        }else{
            ans += "0";
            find(N - 1, L , I);
        }

    }


}

