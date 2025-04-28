import java.io.*;
import java.util.*;

public class Main {
    static long segment_tree[];
    static long arr[];
    static long lazy[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, h+1);

        segment_tree = new long[size];
        lazy = new long[size];
        arr = new long[N + 1];

        for(int i = 1 ; i < N + 1; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1,1, N);

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < M + K; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1){
                long d = Long.parseLong(st.nextToken());

                update(1, 1, N, b, c, d);
            }else{
                sb.append(find(1, 1, N, b, c) + "\n");
            }

        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static long init(int node, int start, int end) { // 트리 노드 번호, 배열 시작~끝 인덱스
        if (start == end) { // 단일 원소값 가정
            return segment_tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return segment_tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    static void update(int node, int start, int end, int left, int right, long diff){
        propagate(node, start, end);

        if(right < start || end < left) return; // 범위 밖임.

        if(left <= start && end <= right){ // 완전히 포함 된다.
            lazy[node] += diff;
            propagate(node, start, end);
            return;
        }

        int mid = (start+end) / 2;

        if(start!= end){ // 리프노드가 아니라면 -> 자식 노드에도 변화값 적용 필요
            update(node*2, start, mid, left, right, diff);
            update(node*2 + 1, mid+1, end, left, right, diff);
        }

        segment_tree[node] = segment_tree[node * 2] + segment_tree[node * 2 + 1];
    }

    static long find(int node, int start, int end, int left, int right){
        propagate(node, start, end);

        if(right < start || end < left) return 0;
        if(left <= start && end <= right){
            return segment_tree[node];
        }

        // 찾을려는 번호가 범위에 속해 있음.
        int mid = (start + end) / 2;
        return find(node * 2, start, mid, left, right) + find(node * 2 + 1, mid + 1, end, left, right);

    }

    static void propagate(int node, int start, int end) { //lazy 엡뎃
        if(lazy[node] != 0){
            if(start != end) { // 리프노드가 아니라면
                lazy[node*2] += lazy[node];
                lazy[node*2 +1] += lazy[node];
            }
            segment_tree[node] += lazy[node] * (end - start + 1); // 해당 노드에 그동안 밀린 업데이트 값을 더해줌.
            lazy[node] = 0;
        }

    }

}