import java.io.*;
import java.util.*;

class info{
    int idx;
    int num;
    long sec;

    info(int idx, int num, long sec){
        this.idx = idx;
        this.num = num;
        this.sec = sec;
    }
}

public class Main {

    static PriorityQueue<info> pq = new PriorityQueue<>(new Comparator<info>() {
        @Override
        public int compare(info o1, info o2) {
            if(o1.sec == o2.sec){
                return o1.idx - o2.idx;
            }
            else if(o1.sec > o2.sec){
                return 1;
            }else {
                return -1;
            }


        }
    });
    static long sum = 0;

    static int n;
    static int s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < m; i++){
            int tmp = Integer.parseInt(br.readLine());

            pq.add(new info(i + 1, tmp, 0));
        }

        int ans = find();

        bw.write(ans + "");

        bw.flush();
        bw.close();

    }
    static int find(){
        while (pq.size() > 0){
            info io = pq.poll();
            sum += 1;

            pq.add(new info(io.idx, io.num, io.num + io.sec));

            if(sum == n - s){
                return io.idx;
            }

        }

        return 0;
    }
}

