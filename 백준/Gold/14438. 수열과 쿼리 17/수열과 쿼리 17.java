import java.io.*;
import java.util.*;

public class Main {
    static long segment[];
    static long arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int h = (int)Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, h + 1);

        arr = new long[N + 1];
        segment = new long[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        init(1, 1, N);

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < M ;i++){
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if(c == 1){ // 바꾸기
                arr[a] = b;

                update(1, 1, N, a);

            }else{
                sb.append(find(1, 1, N, a, (int)b) + "\n");
            }
        }


        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }

    static long init(int node, int start, int end){
        if(start == end){
            return segment[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return segment[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
    }

    static long update(int node, int start, int end, int idx){
        if(idx < start || end < idx) return segment[node];

        int mid = (start + end) / 2;

        if(start!= end){ // 리프노드가 아니라면
            return segment[node] = Math.min(update(node * 2, start, mid, idx), update(node * 2 + 1, mid + 1, end, idx));
        }else{
            return segment[node] = arr[start];
        }
    }

    static long find(int node, int start, int end, int left, int right){
        if(left > end || right < start) return Integer.MAX_VALUE; // 범위 밖

        if(left <= start && end <= right){
            return segment[node];
        }

        int mid = (start + end) / 2;

        return Math.min(find(node * 2, start, mid, left, right), find(node * 2 + 1, mid + 1, end, left, right));

    }
}