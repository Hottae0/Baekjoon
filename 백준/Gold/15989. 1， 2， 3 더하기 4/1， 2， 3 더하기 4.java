import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int arr[] = new int[10_001];
        Arrays.fill(arr, 1);

        for(int i = 0; i < 9999; i++){
            arr[i + 2] += arr[i];
        }

        for(int i = 0; i < 9998; i++){
            arr[i + 3] += arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int t = 0 ; t < T; t++){
            int n = Integer.parseInt(br.readLine());

            sb.append(arr[n] + "\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }
}