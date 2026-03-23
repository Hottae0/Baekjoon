import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());


        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int dis = end - start;
            int max_dis = (int) Math.sqrt(dis);

            if(max_dis == Math.sqrt(dis)){
                sb.append(max_dis * 2 - 1 + "\n");
            }else if (dis <= max_dis * (max_dis + 1)){
                sb.append(max_dis * 2 + "\n");
            }else{
                sb.append(max_dis * 2 + 1 + "\n");
            }

        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }


}