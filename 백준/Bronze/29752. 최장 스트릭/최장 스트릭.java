import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = 0;
        int cur = 0;

        for(int i = 0 ; i < N ;i++){
            int tmp = Integer.parseInt(st.nextToken());

            if(tmp > 0){
                cur += 1;
                max = Math.max(cur, max);

            }else{
                cur = 0;
            }
        }

        bw.write(max + "");

        bw.flush();
        bw.close();
    }

}