import java.io.*;
import java.util.*;

public class Main {

    static long cycle_sum;

    static int L;
    static long arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new long[N];
        long ac_sum[] = new long[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            arr[i] = Long.parseLong(st.nextToken());

            if(i > 0){
                ac_sum[i] = ac_sum[i - 1] + arr[i];
            }
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int P[] = new int[N];

        int j = 0;
        for(int i = 1; i < N; i++){
            while (j > 0 && arr[i] != arr[j]){
                j = P[j - 1];
            }
            if(arr[i] == arr[j]){
                P[i] = ++j;
            }
        }

        long sum = 0; // 출력할려는 값
        L = N - P[N - 1]; // 주기

        cycle_sum = 0;
        for(int i = 0 ; i < L; i++){
            cycle_sum += arr[i];
        }

        int offset = (Math.abs(a) / L + 1) * L;

        int newA = a + offset;
        int newB = b + offset;

//        bw.write(newA + " " + newB + "\n");

        long ans = find_ac_sum(newB) - find_ac_sum(newA);

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }

    static long find_ac_sum(int idx){
        int repeated = (idx) / L;
        int mod = idx % L;

        long ret = repeated * cycle_sum;

        for(int i = 0 ; i < mod; i++){
            ret += arr[i];
        }

        return ret;
    }
}