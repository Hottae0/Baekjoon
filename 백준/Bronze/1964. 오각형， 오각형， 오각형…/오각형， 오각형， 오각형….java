import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int mod = 45678;

        int arr[] = new int[N + 1];

        arr[1] = 5;
        for(int i = 2; i < N + 1; i++){
            arr[i] = ((i+1) * 2 + i - 1 + arr[i - 1]) % mod;
        }

        bw.write(arr[N] + "");

        bw.flush();
        bw.close();

    }
}