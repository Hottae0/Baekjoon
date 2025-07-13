import java.util.*;
import java.io.*;

public class Main {
    static int [][] parent;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 카드의 수
        long K = Long.parseLong(st.nextToken()); // 셔플할 예정

        int S[] = new int[N]; // K번의 셔플 후의 결과
        int D[] = new int[N]; // Di 번째 카드를 i로 가져옴

        st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        int size = (int) (Math.log(K) / Math.log(2)) + 1;
        parent = new int[N][size];

        for(int i = 0 ; i < N; i++){
            S[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st1.nextToken()) - 1;
        }

        parent = new int[N][size];
        for(int i = 0 ; i < N; i++){
            parent[D[i]][0] = i;
        }

        for(int j = 1; j < size; j++){
            for(int i = 0; i < N; i++){
                if(parent[i][j - 1] != -1) parent[i][j] = parent[parent[i][j - 1]][j - 1];
                else parent[i][j] = -1;
            }
        } // 점화식

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N; i++){
            int ans = S[find(i, size, K)];

            sb.append(ans + " ");
        }

//        for(int i = 0 ; i < N ; i++){
//            for (int j = 0; j < size; j++){
//                bw.write(parent[i][j] + " ");
//            }bw.write("\n");
//        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static int find(int idx, int size, long K){

        for(int j = size - 1; j >= 0; j--){
            if(parent[idx][j] != -1 && (K & (1L << j)) != 0){
                idx = parent[idx][j];
            }
        }
        return idx;
    }


}