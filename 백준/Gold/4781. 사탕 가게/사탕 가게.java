import java.util.*;
import java.io.*;

class info{
    int cal;
    int price;

    info(int cal, int price){
        this.cal = cal;
        this.price = price;
    }
}

public class Main {
    static int dp[];
    static info arr[];

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        float tmp_m = Float.parseFloat(st.nextToken());
        int m = (int) Math.round(tmp_m * 100);

        while (n != 0 && m != 0){

            arr = new info[n];

            for(int i = 0 ; i < n; i++){
                st = new StringTokenizer(br.readLine());

                int c = Integer.parseInt(st.nextToken());

                float tmp_p = Float.parseFloat(st.nextToken());
                int p = (int) Math.round(tmp_p * 100);

                arr[i] = new info(c, p);
            }

            dp = new int[m + 1];

            knapsack(n);

            sb.append(dp[dp.length - 1] + "\n");

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            tmp_m = Float.parseFloat(st.nextToken());
            m = (int) Math.round(tmp_m * 100);
        }


        bw.write(sb.toString());

        bw.flush();
        bw.flush();


    }

    static void knapsack(int N){
        for(int i = 0; i < N; i++){
            int c = arr[i].cal;
            int p = arr[i].price;

            for(int j = p; j < dp.length; j++){
                dp[j] = Math.max(dp[j], dp[j - p] + c);
            }

        }

    }

}