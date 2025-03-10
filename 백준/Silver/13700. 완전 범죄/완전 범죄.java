import java.io.*;
import java.util.*;

// 백준 13700
class info{
    int pos;
    int cnt;

    info(int pos, int cnt){
        this.pos = pos;
        this.cnt = cnt;
    }
}

public class Main {

    static PriorityQueue<info> pq = new PriorityQueue<>(new Comparator<info>() {
        @Override
        public int compare(info o1, info o2) {
            return o1.cnt - o2.cnt;
        }
    });

    static int arr[];
    static boolean visit[];

    static int N;
    static int S;
    static int D;
    static int F;
    static int B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 건물 개수
        S = Integer.parseInt(st.nextToken()); // 금은방 위치
        D = Integer.parseInt(st.nextToken()); // 목적지
        F = Integer.parseInt(st.nextToken()); // 앞
        B = Integer.parseInt(st.nextToken()); // 뒤
        int K = Integer.parseInt(st.nextToken()); // 경찰서

        arr = new int[N + 1];
        visit = new boolean[N + 1];
        
        if(K != 0){
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < K; i++){
                visit[Integer.parseInt(st.nextToken())] = true;
            }            
        }
        
        pq.add(new info(S, 0));

        int ans = find();

        if(ans == -1){
            bw.write("BUG FOUND");
        }else{
            bw.write(ans + "");
        }

        bw.flush();
        bw.close();

    }


    static int find(){ //bfs하면 될 듯

        while (!pq.isEmpty()){
            info io = pq.poll();

            if(io.pos == D){
                return io.cnt;
            }

            if(!visit[io.pos]){
                visit[io.pos] = true;

                if(io.pos + F <= N){
                    pq.add(new info(io.pos + F, io.cnt + 1));
                }
                if(io.pos - B > 0){
                    pq.add(new info(io.pos - B, io.cnt + 1));
                }


            }

        }

        return -1;

    }

}

