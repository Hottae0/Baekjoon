import java.io.*;
import java.util.*;

class info{
    int i;
    int j;

    info(int i, int j){
        this.i = i;
        this.j = j;
    }
}

public class Main {
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};

    static int arr[][];
    static boolean visit[][];

    static int L, R, N;

    static Queue<info> que = new ArrayDeque<>();
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        L = Integer.parseInt(st.nextToken()); // 인원 하한
        R = Integer.parseInt(st.nextToken()); // 인원 상한

        arr = new int[N][N];
        visit = new boolean[N][N];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        while(find()){// 국경 개방할 필요가 없을 때 까지
            visit = new boolean[N][N];
            cnt += 1;
        }

        bw.write(cnt + "");

        bw.flush();
        bw.close();

    }

    static boolean find(){
        boolean ret = false;

        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < N; j++){

                if(!visit[i][j]){
                    que.clear();
                    sum = 0;

                    dfs(i,j);

                    int after_move = sum / que.size();
                    if(que.size() <= 1) continue; // 본인밖에 없음 -> 그냥 스킵.

                    while(!que.isEmpty()){
                        ret = true;

                        info io = que.poll();
                        arr[io.i][io.j] = after_move;
                    }

                }

            }
        }

        return ret;
    }


    static void dfs(int i, int j){
        visit[i][j] = true;

        sum += arr[i][j];
        que.add(new info(i, j));

        for(int t = 0 ; t < 4; t++){
            int x = i + dx[t];
            int y = j + dy[t];

            if(x < 0 || x >= N || y < 0 || y >= N) continue;

            int diff = Math.abs(arr[i][j] - arr[x][y]);

            if((L <= diff && diff <= R) && !visit[x][y]){
                dfs(x, y);
            }

        }

    }


    static void print(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                sb.append(arr[i][j] + " ");
            }sb.append("\n");
        }

        System.out.println(sb.toString().trim());
    }
}