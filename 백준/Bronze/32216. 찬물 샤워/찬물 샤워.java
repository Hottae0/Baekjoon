import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); // 적정 온도.
        int T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int sum = 0;

        for(int i = 0 ; i < N; i++){
            int d = Integer.parseInt(st.nextToken());

            if(T < k){
                T = T + d + Math.abs(T - k);
            }else{
                T = T + d - Math.abs(T - k);
            }


            sum += Math.abs(T - k);
        }

        bw.write(sum + "");

        bw.flush();
        bw.close();

    }
}

