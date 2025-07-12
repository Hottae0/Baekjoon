import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int K = Integer.parseInt(st.nextToken()); // 영상 수
        int M = Integer.parseInt(st.nextToken()); // 남은 시간

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int size = (int)(Math.log(M) /Math.log(2)) + 1;
        int parent[][] = new int[K][size]; // i의 2^j 번째 부모를 나타냄

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K; i++){
            Arrays.fill(parent[i], -1);
            parent[i][0] = Integer.parseInt(st.nextToken()) - 1;
        }

        for(int j = 1; j < size; j++){
            for(int i = 0 ; i < K; i++){
                if(parent[i][j - 1] != -1){
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }else{
                    parent[i][j] = -1;
                }
            }
        } // 점화식

//        for(int i = 0 ; i < K; i++){
//            for(int j = 0 ; j < size; j++){
//                bw.write(parent[i][j] + " ");
//            }bw.write("\n");
//        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            int idx = arr[i];

            idx = find(idx, M - 1, size, parent);

            sb.append((idx + 1) + " ");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static int find(int idx, int M, int size, int[][] parent) {
        for (int j = size - 1; j >= 0; j--) {
            if ((M & (1 << j)) != 0) { // M에 2^j가 포함되어 있으면
                idx = parent[idx][j];
            }
        }
        return idx;
    }


}