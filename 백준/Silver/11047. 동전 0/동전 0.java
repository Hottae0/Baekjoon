import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 0;

        StringTokenizer info = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(info.nextToken());
        int K = Integer.parseInt(info.nextToken());

        int arr[] = new int[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i = n - 1; i >=0 ; i--){
            int head = K / arr[i];

            count += head;
            K -= head * arr[i];
        }

        bw.write(count + "");

        bw.flush();
        bw.close();
    }
}