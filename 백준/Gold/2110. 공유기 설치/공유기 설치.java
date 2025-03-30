import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> arr = new ArrayList<>();

    static int N;
    static int C;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            int tmp = Integer.parseInt(br.readLine());

            arr.add(tmp);
        }

        Collections.sort(arr);

        int high = arr.get(arr.size() - 1); // 최대 길이
        int low = 1; // 최소 길이.

        int max = 0;

        while (low <= high){ // 인접 최소 거리를 최대할려면 첫번째 집에 넣는게 유리함.

            int mid = (high + low) / 2;

            int last = 0;
            int cnt = 1;

            for(int i = 1 ; i < N; i++){
                int diff = arr.get(i) - arr.get(last);

                if(diff >= mid){
                    cnt += 1;
                    last = i;
                }
            }

            //bw.write(high + " " + low + " //// " + mid + " // " + cnt + "\n");

            if(cnt < C){ // 거리가 너무 멈
                high = mid - 1;
            }else{
                low = mid + 1;
                max = Math.max(max, mid);
            }

        }

        bw.write(max + "");

        bw.flush();
        bw.close();

    }

}

