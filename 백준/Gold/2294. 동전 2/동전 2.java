import java.io.*;
import java.util.*;

public class Main {
    static HashSet<Integer> set = new HashSet<>();
    static ArrayList<Integer> arr = new ArrayList<>();
    static Integer dp[];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        dp = new Integer[k + 1];

        for(int i = 0 ; i < n; i++){
            int tmp = Integer.parseInt(br.readLine());

            if(!set.contains(tmp) && tmp <= k){ // 안나왔던 거 or 동전의 가치가 k 보다 작아야함.
                set.add(tmp);

                arr.add(tmp);
                dp[tmp] = 1;
            }
        }

        dp[0] = 0;

        dp(k);

        if(dp[k] == 100_001) bw.write("-1");
        else bw.write(dp[k] + "");

        bw.flush();
        bw.close();
    }

    static int dp(int sum){
        if(sum < 0) return 999_999;

        if(dp[sum] == null){
            dp[sum] = 100_001;

            for(int i = 0 ; i < arr.size(); i++){
                dp[sum] = Math.min(dp[sum], dp(sum - arr.get(i)) + 1);
            }

        }

        return dp[sum];
    }

}