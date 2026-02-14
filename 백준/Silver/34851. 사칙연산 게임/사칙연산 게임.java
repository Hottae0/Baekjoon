import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        long mod = (long)Math.pow(10, 9) + 7;

        StringTokenizer st = new StringTokenizer(br.readLine());

        long cur = Long.parseLong(st.nextToken());

        for(int i = 0 ; i < N; i++){
            long next = Long.parseLong(st.nextToken());

            if(next == 1 || (cur == 1 && i == 0)){
                cur += next;
            }else{
                cur *= next;
            }

            cur %= mod;
        }

        bw.write((cur % mod) + "");

        bw.flush();
        bw.close();
    }

}