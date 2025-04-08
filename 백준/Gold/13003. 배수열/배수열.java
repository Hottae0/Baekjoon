import java.io.*;
import java.util.*;

public class Main {
    static int dp[][];
    static int mod = (int)(Math.pow(10,9) + 7);

    static List<Integer>[] divisors;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수 최대 값.
        int L = Integer.parseInt(st.nextToken()); // 길이

        dp = new int[L][N + 1];

        for(int i = 1 ; i < N + 1; i++){
            dp[0][i] = 1;
        }

        divisors = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            divisors[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j += i) {
                divisors[j].add(i);
            }
        }

        int sum = 0;

        for(int i = 1 ; i < N + 1; i++){
            sum += find(L - 1, i) % mod;
            sum = sum % mod;
        }

        bw.write(sum + "\n");

        bw.flush();
        bw.close();

    }

    static int find(int dep, int last){
        if(dp[dep][last] > 0){
            return dp[dep][last] % mod;
        }

        int sum = 0;

        for(int i : divisors[last]){
            sum += find(dep - 1, last / i) % mod;
            sum = sum % mod;
        }

        return dp[dep][last] = sum % mod;

    }
}

