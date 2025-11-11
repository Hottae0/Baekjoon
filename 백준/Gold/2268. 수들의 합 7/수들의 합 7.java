import java.io.*;
import java.util.*;

public class Main {
    static long segment[];
    static long arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, h+1);

        segment = new long[size];
        arr = new long[N + 1];

        init(1, 1, N);

        StringBuilder ans = new StringBuilder();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int ins = Integer.parseInt(st.nextToken());

            if(ins == 1){
                int a = Integer.parseInt(st.nextToken());
                long k = Long.parseLong(st.nextToken());

                update(1, 1, N, a, k - arr[a]);
                arr[a] = k;

            }else{
                int tmp1 = Integer.parseInt(st.nextToken());
                int tmp2 = Integer.parseInt(st.nextToken());

                int l = Math.min(tmp1, tmp2);
                int r = Math.max(tmp1, tmp2);

                ans.append(sum(1, 1, N, l, r) + "\n");
            }

        }

        bw.write(ans.toString().trim());

        bw.flush();
        bw.close();
    }

    static long init(int node, int start, int end){
        if(start == end){
            return segment[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return segment[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    static void update(int node, int start, int end, int idx, long diff){
        if(idx < start || end < idx) return;

        segment[node] += diff;

        int mid = (start + end) / 2;

        if(start!= end){ // 리프노드가 아니라면
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }
    }

    static long sum(int node, int start, int end, int left, int right){
        if(left > end || right < start) return 0;

        if(left <= start && end <= right){
            return segment[node];
        }

        int mid = (start + end) / 2;

        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);

    }

}