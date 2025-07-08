import java.util.*;
import java.io.*;

public class Main {
    static int dist[][];
    static int max[][];

    static int N;

    static int inf = 10_000_000;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점의 수
        int M = Integer.parseInt(st.nextToken()); // 간선의 수

        // a 와 b 를 연결하는 간선들은 불이 붙는다면 (min + max)/2 초 만에 다 타버림.

        max = new int[N][N];
        dist = new int[N][N];

        for(int i = 0; i < N; i++){
            Arrays.fill(dist[i], inf);
            dist[i][i] = 0;

            Arrays.fill(max[i], -1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken()); // 길이.

            dist[s][e] = dist[e][s] = Math.min(dist[s][e], l);
            max[s][e] = max[e][s] = Math.max(max[s][e], l);
        }

        for(int k = 0 ; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        // 플로이드 위셜.

//        for(int i = 0 ; i < N; i++){
//            for(int j = 0; j < N; j++){
//                bw.write(dist[i][j] + " ");
//            }bw.write("\n");
//        }

        float ans = 20_000_001;

        for(int i = 0; i < N; i++){
            ans = Math.min(ans, ignite(i));
        }

        bw.write(String.format("%.1f", ans) + "");

        bw.flush();
        bw.close();

    }

    static float ignite(int start){
        float max_time = 0;

        for(int i = 0 ; i < N; i++){

            for(int j = 0; j < N; j++){

                float remain;

                if(max[i][j] != -1){ // i 와 j를 바로 연결해주는 간선이 있다면.
                    remain = (float)max[i][j] - ((float)dist[start][j] - (float)dist[start][i]);

                    if(remain > 0){
                        max_time = Math.max(max_time, (remain / 2) + (float) dist[start][j]);
                    }
                }


            }
        }

        return max_time;
    }

}