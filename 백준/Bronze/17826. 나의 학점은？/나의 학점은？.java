import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        for(int i = 0 ; i < 50; i++){
            int tmp = Integer.parseInt(st.nextToken());

            if(tmp == N){
                ans = i + 1;
                break;
            }
        }

        if(ans <= 5){
            bw.write("A+");
        }else if(ans <= 15){
            bw.write("A0");
        }else if(ans <= 30){
            bw.write("B+");
        }else if(ans <= 35){
            bw.write("B0");
        }else if(ans <= 45){
            bw.write("C+");
        }else if(ans <= 48){
            bw.write("C0");
        }else{
            bw.write("F");
        }

        bw.flush();
        bw.close();
    }

}