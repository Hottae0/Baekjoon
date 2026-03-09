import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int max = 0;
        int cnt = 0;

        int P[] = new int[N];

        int j = 0;
        for (int i = 1; i < N; i++) {
            while (j > 0 && S.charAt(i) != S.charAt(j)) {
                j = P[j - 1];
            }

            if (S.charAt(i) == S.charAt(j)) {
                P[i] = ++j;
            }
        }

        int len = N - P[N - 1];

        bw.write(len + "");

        bw.flush();
        bw.close();
    }
}