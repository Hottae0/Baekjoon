import java.io.*;
import java.util.*;


public class Main {
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long D = Long.parseLong(st.nextToken()); // 구매할 사과 가격
        long P = Long.parseLong(st.nextToken());
        long Q = Long.parseLong(st.nextToken());

        long low = Math.min(P, Q);
        long high = Math.max(P, Q);

        for(int i = 0 ; i <= Math.min(D/high, low); i++){ // high * low + low.... 이건 low로 묶을 수 있음 -> low까지만 해야 안곂침.
            long cost = (low - (D - high * i) % low + low) % low ; // 추가로 더 필요한 금액을 유도
            min = Math.min(min, cost);
        }

        // low 를 하나도 안 쓸 경우?
        long cost = (high - (D - high) % high) % high;
        min = Math.min(min, cost);

        bw.write((D + min) + "");

        bw.flush();
        bw.close();
    }

    static long gcd(long a, long b){
        if(a % b == 0) return b;

        return gcd(b , a % b);
    }
}