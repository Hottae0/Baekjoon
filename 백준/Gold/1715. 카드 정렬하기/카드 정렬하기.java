import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        PriorityQueue<Long> que = new PriorityQueue<>();

        for(int i = 0; i < num; i++){
            que.add(Long.parseLong(br.readLine()));
        }

        long sum = 0;
        while(que.size() > 1){
            long a = que.remove();
            long b = que.remove();

            que.add(a+b);
            sum += a + b;
        }

        bw.write(sum + "");

        bw.flush();
        bw.close();
    }
}