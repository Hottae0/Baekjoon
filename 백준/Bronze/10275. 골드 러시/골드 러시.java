import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            long n = (long)Math.pow(2,Integer.parseInt(st.nextToken()));
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            int ans = Math.min(find(n, a), find(n, b));
            //bw.write(find(n, a) + " " + find(n, b) + "\n");

            sb.append(ans + "\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();

    }
    static int find(long n, long num){
        int days = 0;

        while(num > 0){

            while (n > num){
                days += 1;
                n /= 2;
            }

            num -= n;
        }

        return days;
    }

}