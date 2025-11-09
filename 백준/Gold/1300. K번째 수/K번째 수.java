import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long left = 1;
        long right = K;

        while (left < right){

            long mid = (left + right) / 2;
            long cnt = 0;

            for(int i = 1 ; i <= N; i++){
                cnt += Math.min(mid / i, N);
            }

            if(cnt < K){
                left = mid + 1;
            }else{
                right = mid;
            }

        }

        bw.write(left + "");

        bw.flush();
        bw.close();
    }
}