import java.io.*;
import java.util.*;

public class Main {
    static int arr[];
    static int seg_tree[];
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        int h = (int) Math.ceil(Math.log(N)/ Math.log(2));
        int size = (int)Math.pow(2, h + 1);

        seg_tree = new int[size];

        st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i < N + 1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 1, N);

        for(int i = M ; i <= N - M + 1; i++){
            //bw.write( (i - M + 1) + " " + (i + M - 1) +"\n");
            ans.append(find(1, 1, N, i - M + 1, i + M - 1) + " ");
        }

        bw.write(ans.toString().trim());

        bw.flush();
        bw.close();
    }

    static int init(int node, int start, int end) { // 트리 노드 번호, 배열 시작~끝 인덱스
        if (start == end) { // 단일 원소값 가정
            return seg_tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return seg_tree[node] = Math.max(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
    }

    static int find(int node, int start, int end, int left, int right){

        if(right < start || end < left) return -1;

        if(left <= start && end <= right){
            return seg_tree[node];
        } // 내부에 포함.

        // 찾을려는 번호가 범위에 속해 있음.
        int mid = (start + end) / 2;

        return Math.max(find(node * 2, start, mid, left, right) , find(node * 2 + 1, mid + 1, end, left, right));
    }


}