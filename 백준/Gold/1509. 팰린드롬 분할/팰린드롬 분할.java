    import java.io.*;
    import java.util.*;

    public class Main {
        static boolean is_pal[][];
        static int DP[];

        static String S;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            S = br.readLine();
            int len = S.length();

            is_pal = new boolean[len][len];

            // 펠린드롬 테이블 제작
            for(int i = 0; i < len; i++){
                is_pal[i][i] = true;
            }
            for(int i = 0 ; i < len - 1; i ++){
                if(S.charAt(i) == S.charAt(i + 1)){
                    is_pal[i][i + 1] = true;
                }
            }

            for(int tmp_len = 3; tmp_len <= len; tmp_len++){
                for(int start = 0; start + tmp_len - 1 < len; start++){
                    int end = start + tmp_len - 1;

                    if(S.charAt(start) == S.charAt(end) && is_pal[start + 1][end - 1]) is_pal[start][end] = true;
                }
            }

            DP = new int[len]; // i까지 사용했을 때의 pal의 갯수
            for(int i = 0 ; i < len ; i++){
                DP[i] = i + 1; // 최대 개수는 길이만큼임. 다 아니면 되잖아
            }

            for(int i = 0; i < len ; i++){
                for(int j = 0; j <= i; j ++){

                    if(j == 0) {
                        if(is_pal[j][i]) DP[i] = 1;
                    }else{
                        if(is_pal[j][i]){
                            DP[i] = Math.min(DP[i], DP[j - 1] + 1);
                        }
                    }

                }
            }

//            for(int i = 0 ; i < is_pal.length; i++){
//                for(boolean j : is_pal[i]){
//                    bw.write(j ? "1 " : "0 ");
//                }bw.write("\n");
//            }
            
            bw.write(DP[len - 1] + "");

            bw.flush();
            bw.close();
        }


    }