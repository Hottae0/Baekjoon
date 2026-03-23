import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        long sum = 0;
        long max = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            long tmp = Long.parseLong(st.nextToken());
            sum += tmp;
            max = Math.max(max, tmp);
        }

        long rest = sum - max;

        if(rest < max){ // 가장 큰 숫자가 다른 숫자를 모두 커버 가능함
            bw.write(rest * 2 + 1 + "");
        }else{
            bw.write(sum + "");
        }

        bw.flush();
        bw.close();
    }
}