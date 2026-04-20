import java.awt.geom.GeneralPath;
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
    static int dx[] = {0,0, 1,1,1, -1,-1,-1};
    static int dy[] = {1,-1, 0,1,-1, 0,1,-1};

    static int graph[][];
    static boolean visited[][];

    static int N, M;

    static Queue<info> que = new ArrayDeque<>();

    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < N ;i++){
            for(int j = 0; j < M; j++){
                que.clear();
                if(graph[i][j] != 0 && !visited[i][j])  {
                    que.add(new info(i, j));

                    if(dfs(graph[i][j])){
                        ans += 1;
                        //bw.write(i + " " + j + "\n");
                    }
                }

            }
        }

//        for(int i = 0 ; i < N ;i++){
//            for(int j = 0; j < M; j++){
//                if(visited[i][j]){
//                    bw.write("1");
//                }else{
//                    bw.write("0");
//                }
//
//            }bw.write("\n");
//        }

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }

    static boolean dfs(int max){
        boolean is_top = true;

        while (que.size() > 0){
            info io = que.poll();

            int a = io.x;
            int b = io.y;

            visited[a][b] = true;

            for(int i = 0 ; i < 8; i++){
                int x = a + dx[i];
                int y = b + dy[i];

                if(x < 0 || x >= N || y < 0 || y >= M) continue;
                if(graph[x][y] == 0) continue;

                if(graph[x][y] > max) {
                    is_top = false;
                }
                else if(graph[x][y] == max && !visited[x][y]){
                    que.add(new info(x, y));
                }
            }
        }

        return is_top;
    }
}


//        8 7
//        4 3 2 2 1 0 1
//        3 3 3 2 1 0 1
//        2 2 2 2 1 0 0
//        2 1 1 1 1 0 0
//        1 1 6 6 0 1 0
//        0 0 0 1 1 1 0
//        0 1 2 2 5 1 0
//        0 1 1 1 2 1 0