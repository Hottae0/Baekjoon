import java.io.*;
import java.util.*;

class info{
    int dep;
    int sum;

    info(int dep, int sum){
        this.dep = dep;
        this.sum = sum;
    }
}

public class Main {
    static long dp[][];
    static int [] arr;

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        arr = new int[N - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N - 1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int sum = Integer.parseInt(st.nextToken());

        dp = new long[N - 1][21];
        dp[0][arr[0]] = 1;

        bw.write(Bottom_Up(N - 2, sum) + "\n");

//        for(int i = 0 ; i < N - 1; i++){
//            for(int j = 0 ; j < 21; j++){
//                bw.write(dp[i][j] + " ");
//            }bw.write("\n");
//        }

        bw.flush();
        bw.close();

    }

    static long Bottom_Up(int dep, int sum){
        // 아니 top_down을 했는데 바텀 업이라 썻네
        if(sum < 0 || sum > 20) return 0;
        if(dep == 0 || dp[dep][sum] > 0) return dp[dep][sum];

        int num = arr[dep];

        return dp[dep][sum] = Bottom_Up(dep - 1, sum + num) + Bottom_Up(dep - 1, sum - num);


    }
}