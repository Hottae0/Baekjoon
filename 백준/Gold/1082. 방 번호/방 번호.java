import java.io.*;
import java.util.*;

public class Main {
    static int arr[];
    static String dp[];

    static int n, m;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());

        dp = new String[m + 1];
        Arrays.fill(dp, "0");

        for(int i = n - 1; i > 0 ;i--){
            make_room_number(arr[i], String.valueOf(i));
        }

//        int idx = 0;
//        for(String S : dp){
//            bw.write(idx++ + " " + S + "\n");
//        }
//
//        bw.write(sb.toString());

        String max = "0";
        for(int i = 0 ; i < m + 1; i ++){
            if(compare_string(dp[i], max)){
                max = dp[i];
            }
        }

        bw.write(max);

        bw.flush();
        bw.close();

    }

    static String make_room_number(int sum, String room){
        if(sum > m) return room;

        if(compare_string(room, dp[sum])){
            dp[sum] = room;

            for(int i = n - 1; i >= 0; i--){
                 make_room_number(sum + arr[i], room + i);
            }
        }

        return dp[sum];
    }


    static boolean compare_string(String s1, String s2){ // s1의 숫자가 더 큼 -> true 리턴.
        if(s1.length() > s2.length()){
            return true;
        }else if(s1.length() == s2.length()){
            return s1.compareTo(s2) > 0;
        }
        return false;
    }
}