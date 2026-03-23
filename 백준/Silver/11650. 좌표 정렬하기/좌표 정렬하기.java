import java.io.*;
import java.util.*;

class info implements Comparable<info>{
    int x;
    int y;

    info(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int compareTo(info io){
        if(x == io.x){
            return y - io.y;
        }

        return x - io.x;
    }
}

public class Main {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<info> que = new PriorityQueue<>();
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            que.add(new info(x, y));
        }

        while (que.size() > 0){
            info io = que.poll();

            bw.write(io.x + " " + io.y +  "\n");
        }

        bw.flush();
        bw.close();
    }
}