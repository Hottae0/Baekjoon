import java.io.*;
import java.rmi.NoSuchObjectException;
import java.util.*;

public class Main {
    static long arr[];
    static long seg_tree[];
    static int idx = -1;
    static boolean visit = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new long[N+1];

        int h = (int)Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, h + 1);
        seg_tree = new long[size];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1 ; i < N + 1; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        init(1, 1, N);

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();


        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int fir = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());
            int thr = Integer.parseInt(st.nextToken());

            if(fir == 1){
                arr[sec] = thr;
                update(1, 1, N, sec, thr);

            }else{
                long min = MIN(1,1, N, sec, thr);
                visit = false;

                find(1, 1, N, sec, thr, min);

                sb.append(idx + "\n");
            }
        }


        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    static long init(int Node, int start, int end){
        if(start == end){
            return seg_tree[Node] = arr[start];
        }

        int mid = (start + end) / 2;

        return seg_tree[Node] = Math.min(init(Node * 2, start, mid), init(Node * 2 + 1, mid + 1, end));
    }

    static long update(int node, int start, int end, int idx, long chn) {
        if (idx < start || end < idx) {
            // 범위를 벗어나면, 이 구간에선 아무 변화도 일어나지 않음을 표시
            return seg_tree[node];
        }

        if (start == end) {
            // 리프에 도달했을 때만 실제 값을 바꾼다
            seg_tree[node] = chn;
            return chn;
        }

        int mid = (start + end) / 2;
        long leftMin  = update(node * 2,     start,   mid,   idx, chn);
        long rightMin = update(node * 2 + 1, mid + 1, end, idx, chn);
        // 두 자식에서 받아온 최소값으로 갱신
        return seg_tree[node] = Math.min(leftMin, rightMin);
    }

    static long MIN(int Node, int start, int end, int left, int right){
        if(right < start || end < left) return Integer.MAX_VALUE;

        if(left <= start && end <= right){
            return seg_tree[Node];
        }

        int mid = (start + end) / 2;

        return Math.min(MIN(Node * 2, start, mid, left, right),
                MIN(Node * 2 + 1, mid + 1, end, left, right));
    }

    static void find(int Node, int start, int end, int left, int right, long find_num){
        if(visit) return;

        if(right < start || end < left) return;
        int mid = (start + end) / 2;

        if(start != end){
            find(Node * 2, start ,mid, left, right, find_num);
            find(Node * 2 + 1, mid + 1, end, left, right, find_num);
        }
        if(start == end){
            if(seg_tree[Node] == find_num){
                visit = true;
                idx = start;
            }
        }

    }

}