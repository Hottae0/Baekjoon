import java.io.*;
import java.util.*;

class info{
    int r;
    int c;
    int cnt;

    info(int r, int c, int cnt){
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}

public class Main {

    static int graph[][] = new int[5][5];
    static boolean visit[][] = new boolean[5][5];

    static int ans = Integer.MAX_VALUE;
    static PriorityQueue<info> pq = new PriorityQueue<>(new Comparator<info>() {
        @Override
        public int compare(info o1, info o2) {
            return o1.cnt - o2.cnt;
        }
    });

    static int dx[] = {0, 0 , 1 ,-1};
    static int dy[] = {1, -1, 0 ,0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0 ; i < 5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 5; j++){
                int tmp = Integer.parseInt(st.nextToken());

                graph[i][j] = tmp;
            }
        }

        int r; int c;

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        pq.add(new info(r, c, 0));

        while (pq.size() > 0){
            info io = pq.poll();

            if(graph[io.r][io.c] == 1){
                ans = io.cnt;
                break;
            }

            find(io.r, io.c, io.cnt);
        }

        if(ans == Integer.MAX_VALUE){
            bw.write("-1");
        }else{
            bw.write(ans + "");
        }

        bw.flush();
        bw.close();
    }

    static void find(int r, int c, int cnt){

        if(!visit[r][c]){
            visit[r][c] = true;

            for(int i = 0 ; i < 4; i++){

                int a = r + dx[i];
                int b = c + dy[i];

                if(a < 5 && a >= 0 && b < 5 && b >= 0){
                    if(graph[a][b] != -1){
                        pq.add(new info(a, b, cnt + 1));
                    }
                }
            } // 일반적으로 이동 할 시

            // 고속 이동 시
            for(int i = r - 1; i >= 0; i--){
                if(graph[i][c] == 7){
                    pq.add(new info(i, c, cnt + 1));
                    break;
                }else if(graph[i][c] == -1){
                    pq.add(new info(i + 1, c, cnt + 1));
                    break;
                }

                if(i == 0){
                    pq.add(new info(0, c, cnt + 1));
                }

            }

            for(int i = r + 1; i < 5; i++){
                if(graph[i][c] == 7){
                    pq.add(new info(i, c, cnt + 1));
                    break;
                }else if(graph[i][c] == -1){
                    pq.add(new info(i - 1, c, cnt + 1));
                    break;
                }

                if(i == 4){
                    pq.add(new info(4, c, cnt + 1));
                }
            }

            for(int j = c - 1; j >=0; j--){
                if(graph[r][j] == 7){
                    pq.add(new info(r, j, cnt + 1));
                    break;
                }else if(graph[r][j] == -1){
                    pq.add(new info(r, j + 1, cnt + 1));
                    break;
                }

                if(j == 0){
                    pq.add(new info(r, 0, cnt + 1));
                }
            }

            for(int j = c + 1; j < 5; j++){
                if(graph[r][j] == 7){
                    pq.add(new info(r, j, cnt + 1));
                    break;
                }else if(graph[r][j] == -1){
                    pq.add(new info(r, j - 1, cnt + 1));
                    break;
                }

                if(j == 4){
                    pq.add(new info(r,4,cnt + 1));
                }
            }

        }




    }

}

