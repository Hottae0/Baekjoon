import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();

        int sum = 0;
        int idx = 0;


        for(int i = 0 ; i < S.length(); i++){
            if(S.charAt(i) == '*'){
                idx = i;
                continue;
            }

            int tmp = S.charAt(i) - '0';

            if(i % 2 == 0){
                sum += tmp;
            }else{
                sum += tmp * 3;
            }

            sum %= 10;
        }

        if(idx % 2 == 0){
            bw.write((10 - sum) + "");
        }else{
            for(int i = 0; i < 10; i++){
                if ( (sum + i*3) % 10 == 0){
                    bw.write(i + "");
                    break;
                }
            }

        }

        bw.flush();
        bw.close();
    }
}
