import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N ;i++){
            long tmp = Long.parseLong(br.readLine());

            if(get_num(tmp)){
                bw.write("YES\n");
            }else{
                bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static boolean get_num(long N) {

        for(long i = 2; i <= 1_000_000; i++) {
            while(N % i == 0) {
                return false;
            }
        }

        return true;
    }

}