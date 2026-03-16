import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> crane = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            crane.add( Integer.parseInt(st.nextToken()) );
        }

        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> box = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M; i++){
            box.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(crane);
        Collections.sort(box);

        int ans [] = new int[N];

        int crane_idx = 0;
        int box_idx = 0;
        while (crane_idx < N && box_idx < M){
            if(crane.get(crane_idx) >= box.get(box_idx)){
                ans[crane_idx] += 1;

                box_idx += 1;
            }else{
                crane_idx += 1;
            }

        }

        if(box_idx < M){
            bw.write("-1");

            bw.flush();
            bw.close();
            return;
        }


//        for(int i : ans){
//            bw.write(i + " ");
//        }bw.write("\n");

        int min = 0;

        int sum = 0;
        for(int i = ans.length - 1; i >= 0; i--){
            sum += ans[i];

            int days = 0;
            if(sum % (N - i) == 0){
                days = sum / (N - i);
            }else{
                days = sum / (N - i) + 1;
            }

            min = Math.max(min, days);
        }

        bw.write(min + "");

        bw.flush();
        bw.close();
    }
}