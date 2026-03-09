import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int arr[] = new int[N];
        for(int i = 0 ; i < N ; i++){
            arr[N - i - 1] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int cnt = 0;

        int P[] = new int[N];

        int j = 0;
        for (int i = 1; i < N; i++) {
            while (j > 0 && arr[i] != arr[j]) {
                j = P[j - 1];
            }

            if (arr[i] == arr[j]) {
                P[i] = ++j;

            }
        }
        
//        for (int a : P) {
//            bw.write(a + " ");
//        }
//        bw.write("\n");

        for(int i = 0 ; i < N ;i++){
            if(max == P[i]){
                cnt += 1;
            }else if(max < P[i]){
                cnt = 1;
                max = P[i];
            }
        }

        if(max == 0){
            bw.write("-1");
        }else{
            bw.write(max + " " + cnt);
        }

        bw.flush();
        bw.close();
    }
}