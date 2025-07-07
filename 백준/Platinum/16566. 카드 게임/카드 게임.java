import java.util.*;
import java.io.*;

public class Main {
    static int parent[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 1 ~ N 까지의 카드가 존재
        int M = Integer.parseInt(st.nextToken()); // 카드 중 M개의 카드 선택
        int K = Integer.parseInt(st.nextToken()); // K번 카드를 가져옴.

        // 철수(마술사) : 민수 몰래 다시 들고 온다거나 민수한테 없는 카드를 내기도 한다
        // 민수(심리학자) : 철수가 낼 카드를 알아낼 수 있다. 철수가 낼 카드보다 큰 카드가 있다면 그 카드들 중 가장 작은 카드를 내기로 했다.

        int[] card = new int[M];
        parent = new int[M];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < M; i++){
            card[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }
        // M 개의 카드를 뽑아온다.

        Arrays.sort(card);

        /*
        for(int k : card){
            bw.write(k + " ");
        }bw.write("\n");
         */

        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < K; i++){
            int num = Integer.parseInt(st.nextToken());

            int idx = binary_search(card, num);

            while (card[idx] < num){
                idx += 1;
            }

            if(idx != parent[idx]){
                idx = find(idx);
            }

            if(idx != M - 1){
                union(idx, idx + 1);
            }

            /*
            for(int k = 0; k < M; k++){
                bw.write(parent[k] + " ");
            }bw.write("\n");
            */

            sb.append(card[idx] + "\n");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }


    static int binary_search(int[] card, int target){

        int low = 0;
        int high = card.length;

        int mid = (low + high) / 2;

        while(low <= high){
            mid = (low + high) / 2;

            if(target == card[mid]){
                return mid + 1; // 최소 값임.
            }
            else if(target < card[mid]){ // 찾으려는 게 현재 가르키는 거보다 작음
                high = mid - 1;
            }else{
                low = mid + 1;
            }

        }

        return mid;
    }


    static void union(int x, int y){
        int a = find(x);
        int b = find(y);

        if(a == b) return;

        if(a < b){ // 높은 숫자로 union하기
            parent[a] = b;
        }else{
            parent[b] = a;
        }

    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }



}