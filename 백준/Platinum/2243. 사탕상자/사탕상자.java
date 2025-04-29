import java.io.*;
import java.util.*;

public class Main {
    static long segment_tree[];
    static int len = 1_000_001;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(len) / Math.log(2));
        int size = (int) Math.pow(2, h+1);

        segment_tree = new long[size];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(A == 1){
                int taste = (int)binary_search(1,1, len - 1, B);
                sb.append(taste + "\n");

                update(1, 1, len - 1, taste, -1);
            }else if(A == 2){
                long C = Long.parseLong(st.nextToken());

                update(1, 1, len - 1, B, C);
            }

        }
        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static void update(int node, int start, int end, int idx, long diff){
        if(idx < start || end < idx) return; // 범위 밖임.

        segment_tree[node] += diff;

        int mid = (start+end) / 2;

        if(start!= end){//리프노드가 아니라면 -> 자식 노드에도 변화값 적용 필요
            update(node*2, start, mid, idx, diff);
            update(node*2 + 1, mid+1, end, idx, diff);
        }
    }

    static long binary_search(int node, long left, long right, long idx){
        if(left == right) return left;

        // 여기서 최신화 한다.
        long mid = (left + right) / 2;

        if(idx <= segment_tree[node * 2]){ // 왼쪽에 포함되고 있다.
            return binary_search(node * 2, left, mid, idx);
        }else{
            return binary_search(node * 2 + 1, mid + 1, right, idx - segment_tree[node * 2]);
        }

    }


}