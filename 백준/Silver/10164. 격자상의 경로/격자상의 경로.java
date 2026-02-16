import java.io.*;
import java.util.*;

class info{
    int x;
    int y;

    info(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int graph[][];

    static int dx[] = {-1, 0};
    static int dy[] = {0, -1};

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        graph[0][0] = 1;

        if(K == 0){
            bw.write(find(N - 1, M - 1) + "");
        }else{
            int i = (K - 1) / M;
            int j = (K - 1) % M;

            int fir = find(i, j);

            graph = new int[N][M];
            graph[i][j] = 1;

            int sec = find(N - 1, M - 1);

            bw.write(fir * sec);
        }

        bw.flush();
        bw.close();

    }

    static int find(int i, int j){
        if(graph[i][j] == 0){
            for(int t = 0; t < 2; t++){
                int x = i + dx[t];
                int y = j + dy[t];

                if(x < 0 || y < 0 || x >= N || y >= M) continue;

                graph[i][j] += find(x, y);
            }
        }

        return graph[i][j];
    }

}