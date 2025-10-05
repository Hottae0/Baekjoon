import java.io.*;
import java.util.*;

public class Main {
    static float score[] = {13f, 7f, 5f, 3f, 3f, 2f};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        float a_sum = 0;
        float b_sum = 1.5f;

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < 6; i++){
            a_sum += Float.parseFloat(st.nextToken()) * score[i];
            b_sum += Float.parseFloat(st1.nextToken()) * score[i];
        }

        if(a_sum > b_sum){
            bw.write("cocjr0208");
        }else{
            bw.write("ekwoo");
        }

        bw.flush();
        bw.close();
    }
}
