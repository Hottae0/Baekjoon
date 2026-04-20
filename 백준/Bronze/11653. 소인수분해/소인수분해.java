import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        get_num(N);

        bw.write(ans.toString().trim());

        bw.flush();
        bw.close();
    }

    static void get_num(int N) {

        for(long i = 2; i <= N; i++) {
            while(N % i == 0) {
                ans.append(i + "\n");
                N /= i;
            }
        }
    }

}