import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int arr[] = new int[D];
        Arrays.fill(arr, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());

        arr[0] = Integer.parseInt(st.nextToken());

        for(int i = 1; i < D; i++){
            arr[i] = Math.min(Integer.parseInt(st.nextToken()), arr[i - 1]);
        }

        int pizza[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            pizza[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int last = 0;

        for(int i = D - 1; i >= 0; i--){
            if(idx == N) break;

            if(pizza[idx] <= arr[i]){
                idx += 1;
                last = i + 1;
            }
        }

        if(idx == N){
            bw.write(last + "");
        }else{
            bw.write("0");
        }

        bw.flush();
        bw.close();
    }
}