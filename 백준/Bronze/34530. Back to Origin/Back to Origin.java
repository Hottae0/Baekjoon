import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int cnt = 0;

        for(int i = 2; i < 361; i++){
            if((N * i) % 360 == 0){
                cnt = i;
                break;
            }
        }

        bw.write(cnt + "");

        bw.flush();
        bw.close();
    }
}