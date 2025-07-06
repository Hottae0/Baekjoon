import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        long left = 0;
        long right = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
        }

        Arrays.sort(arr);

        long ans = 0;

        for(int i = 0 ; i < n; i++){

            ans += Math.abs(right - (long) arr[i] * (n - i));
            ans += Math.abs(left - ((long) arr[i] * (i)));

            right -= arr[i];
            left += arr[i];
        }

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }
}