import java.io.*;
import java.util.*;

public class Main {
    static int segment_tree[];
    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, h+1);

        segment_tree = new int[size];
        arr = new int[N + 1];

        for(int i = 1 ; i < N + 1; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(1,1, N);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(find(1, 1, N, a, b) + "\n");

        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static int init(int node, int start, int end){ // 트리 노드 번호, 배열 시작~끝 인덱스
        if(start == end){ // 단일 원소값 가정
            return segment_tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return segment_tree[node] = Math.min(init(node*2, start, mid), init(node*2+1, mid+1, end)); // 부모노드는 자식 노드들 중 최솟값.
    }

    static int find(int node, int start, int end, int left, int right){
        if(right < start || end < left) return Integer.MAX_VALUE;

        if(left <= start && end <= right) return segment_tree[node];

        // 찾을려는 번호가 범위에 속해 있음.
        int mid = (start + end) / 2;

        return Math.min(find(node * 2, start, mid, left, right),
        find(node * 2 + 1, mid + 1, end, left, right));

    }

}