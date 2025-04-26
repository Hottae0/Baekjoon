import java.io.*;
import java.util.*;

public class Main {
    static long segTree[];
    static long arr[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(N)/ Math.log(2));
        int treeSize = (int)Math.pow(2, h + 1);

        segTree = new long[treeSize];

        arr = new long[N + 1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i < N + 1; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        init(1, 1, N);

        for(int i = 0 ; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int x, y, a, b;

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            long tmp = find(1, 1, N, Math.min(x, y), Math.max(x,y));
            sb.append(tmp + "\n");

            long diff = b - arr[a];
            update(1, 1, N, a, diff);
            arr[a] = b;
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static long init(int node, int start, int end){ // 트리 노드 번호, 배열 시작~끝 인덱스
        if(start == end){ // 단일 원소값 가정
            return segTree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return segTree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end); // 부모노드는 자식 노드들의 합.
    }

    static void update(int node, int start, int end, int idx, long diff){
        if(idx < start || idx > end) return; // 범위 밖임.

        segTree[node] += diff; // 변경값 적용

        int mid = (start+end) / 2;

        if(start!= end){ // 리프노드가 아니라면 -> 자식 노드에도 변화값 적용 필요
            update(node*2, start, mid, idx, diff);
            update(node*2 + 1, mid+1, end, idx, diff);
        }

    }

    static long find(int node, int start, int end, int left, int right){
        if(left > end || right < start) return 0; // 범위를 벗어남.

        if(left <= start && end <= right){ // 속한다.
            return segTree[node];
        }

        // 찾을려는 번호가 범위에 속해 있음.
        int mid = (start + end) / 2;
        return find(node * 2, start, mid, left, right) + find(node * 2 + 1, mid + 1, end, left, right);
    }

}