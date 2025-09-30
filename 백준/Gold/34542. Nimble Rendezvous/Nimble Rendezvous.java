import java.io.*;
import java.util.*;

public class Main {
    static long ans = 0;

    static long arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        long a = Math.min(x, y);
        long b = Math.max(x, y);

        int diff = (int) Math.abs(a - b);

        if(Math.abs(a - b) % 2 == 1) bw.write(-1 + "");
        else{
            arr = new long[31]; // 최대 21억까지 필요.

            arr[0] = 1;
            for(int i = 1; i <= 30; i++) {
                arr[i] = arr[i - 1] * 2;
            }
            // 2의 배수 배열 형성.

            int start = (int)(Math.log(diff) / Math.log(2));

            long cnt = arr[start] - (Math.abs(a - b) / 2);


            bw.write(start + " " + cnt);
        }


        bw.flush();
        bw.close();

    }
}