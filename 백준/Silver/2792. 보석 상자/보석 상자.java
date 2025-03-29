import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 큐를 쓰니깐 메모리 초과 발생!
        // 다른 방법을 써야함.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 아이들 수
        int M = Integer.parseInt(st.nextToken()); // 보석 개수

        int min = 1;
        int max = -1;

        for(int i = 0; i < M; i++){
            int tmp = Integer.parseInt(br.readLine());

            max = Math.max(tmp, max);
            arr.add(tmp);
        }

        int ans = Integer.MAX_VALUE;

        while (min <= max){
            int mid = (min + max) / 2;

            int tmp = find(mid);

            if(tmp <= N){ // tmp가 사람 수 이하 -> 이때의 경우의 수는 조건을 만족하긴 함.
                ans = Math.min(mid, ans);
                max = mid - 1;
            }else{ // tmp가 사람 수 보다 많음
                min = mid + 1;
            }

        }

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }

    static int find(int mid){ //mid 값씩 나눌 때 가정하면, cnt의 인원이 필요하다.
        int cnt = 0;

        for(int i = 0 ; i < arr.size(); i++){
            cnt += arr.get(i) / mid;
            if(arr.get(i) % mid > 0) cnt += 1;
        }

        return cnt;
    }

}

