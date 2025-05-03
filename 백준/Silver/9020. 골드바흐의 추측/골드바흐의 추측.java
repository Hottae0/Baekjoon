import java.io.*;
import java.util.*;

public class Main {
    static boolean prime[] = new boolean[100000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < T; i++) {
            get_prime();

            int N = Integer.parseInt(br.readLine());
            int tmp1 = 0, tmp2 = 0;

            for(int j = 2; j <= N/2 ; j++) {

                if (!prime[j] && !prime[N - j]) { // 둘 다 소수임
                    tmp1 = j;
                    tmp2 = N - j;
                }
            }


            sb.append(tmp1 + " " + tmp2 + "\n");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static void get_prime() { // 에라토스테네스의 채

        prime[0] = prime[1] = true;

        for (int i = 2; i < Math.sqrt(prime.length); i++) {
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }

    }
}