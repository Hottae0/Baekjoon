import java.io.*;
import java.util.*;

class info{
    long sum;
    boolean is_first;

    info(long sum, boolean is_first){
        this.sum = sum;
        this.is_first = is_first;
    }

}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        info arr[] = new info[10];
        for(int i = 0 ; i < 10; i++){
            arr[i] = new info(0, false);
        }

        for(int i = 0 ; i < N; i++){
            String S = br.readLine();

            int len = S.length();

            for(int j = 0; j < len; j++){
                int num = S.charAt(len - 1- j) - 'A';

                arr[num].sum += (long)Math.pow(10, j);
                if(j == len - 1) arr[num].is_first = true;
            }
        }

        Arrays.sort(arr, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o2.sum, o1.sum);
            }
        });

        ArrayList<info> list = new ArrayList<>();

        for(int i = 0 ; i < 10; i++){
            if(arr[i].sum == 0) break;

            list.add(arr[i]);
        }

        long cnt = 9;
        long ans = 0;


        if(list.size() != 10){
            for(int i = 0 ; i < list.size(); i++){
                ans += cnt-- * list.get(i).sum;
            }
        }else{ // 0을 지정하고 해야함.
            for(int i = 9; i >= 0; i--){
                if(!list.get(i).is_first){
                    list.remove(i);
                    break;
                }
            }

            for(int i = 0 ; i < 9; i++){
                ans += cnt-- * list.get(i).sum;
            }

        }

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }
}