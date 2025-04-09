import java.io.*;
import java.util.*;

class info{
    long price;
    long value;

    info(long price, long value){
        this.price = price;
        this.value = value;
    }

}

public class Main {

    static info arr[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 친구  수
        int D = Integer.parseInt(st.nextToken()); // 만족하는 만족감 차이

        arr = new info[N];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());

            long tmp_p = Long.parseLong(st.nextToken());
            long tmp_v = Long.parseLong(st.nextToken());

            arr[i] = new info(tmp_p, tmp_v);
        }

        Arrays.sort(arr, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                if(o1.price > o2.price) return  1;
                else return -1;
            }
        });

        int i = 0; // 왼쪽
        int j = 0; // 오른쪽을 나타낼 예정.

        long value_sum = 0;
        long value_max = 0;

        while (j < N){ // D가 1 이상이기 때문에 절대로 추월할 수는 없다.
            if(arr[j].price - arr[i].price < D){
                value_sum += arr[j].value;
                j++;
            }else{
                value_sum -= arr[i].value;
                i++;
            }

            value_max = Math.max(value_sum, value_max);

        }

        bw.write(value_max + "");

        bw.flush();
        bw.close();
    }

}

