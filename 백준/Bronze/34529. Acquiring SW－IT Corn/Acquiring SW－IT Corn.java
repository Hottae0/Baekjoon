import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A_c = Integer.parseInt(st.nextToken());
        int B_c = Integer.parseInt(st.nextToken());
        int C_c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int A_cnt = Integer.parseInt(st.nextToken()) / 100;
        int B_cnt = Integer.parseInt(st.nextToken()) / 50;
        int C_cnt = Integer.parseInt(st.nextToken()) / 20;

        long sum = A_c * A_cnt + B_c * B_cnt + C_c * C_cnt;

        bw.write(sum + "");

        bw.flush();
        bw.close();
    }
}