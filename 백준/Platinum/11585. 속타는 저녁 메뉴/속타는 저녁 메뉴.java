import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        String A = "";
        String pattern = "";

        StringBuilder st_a = new StringBuilder();
        StringBuilder st_p = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < N ; i++){
            st_a.append(st.nextToken());
            st_p.append(st2.nextToken());
        }

        A = st_a.toString();
        pattern = st_p.toString();

        int P[] = new int[pattern.length()]; // suffix & prefix 의 일치하는 길이
        int j = 0;
        for(int i = 1 ; i < pattern.length(); i++){
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)){
                j = P[j - 1];
            }
            if(pattern.charAt(i) == pattern.charAt(j)){
                P[i] = ++j;
            }
        }

        int cnt = 0;

        int idx = 0;

        StringBuilder sb = new StringBuilder(A);
        sb.append(sb.toString());

        A = sb.toString().trim();

        for(int i = 0; i < A.length() - 1; i++) {
            while (idx > 0 && A.charAt(i) != pattern.charAt(idx)) {
                idx = P[idx - 1];
            }

            if (A.charAt(i) == pattern.charAt(idx)) {
                if (idx == pattern.length() - 1) {
                    cnt += 1;
                    idx = P[idx];
                } else {
                    idx++;
                }
            }
        }

        int div = 0;

        while((div = gcd(cnt ,N)) != 1){
            N /= div;
            cnt /= div;
        }

        bw.write(cnt + "/" + N);

        bw.flush();
        bw.close();
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}