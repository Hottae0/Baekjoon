import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long M, num;

    static long segment_tree[];
    static long arr[];

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        arr = new long[N + 1];

        ArrayList<Long> tmp_list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            long tmp = Long.parseLong(st.nextToken());

            arr[i] = tmp;
            tmp_list.add(tmp);
        }

        Collections.sort(tmp_list);

        HashMap<Long, Long> map = new HashMap<>();

        long idx = 0;
        for(int i = 0 ; i< N; i++){
            if(map.containsKey(tmp_list.get(i))){
                continue;
            }

            map.put(tmp_list.get(i), idx++);
        }

        int h = (int) Math.ceil(Math.log(map.size()) / Math.log(2));
        int size = (int) Math.pow(2, h+1);

        segment_tree = new long[size];

        long max_Length = 0;
        for (int i = 0; i < N; i++) {
            int now = (int) arr[i]; // 현재 처리할 압축 좌표

            long maxPrevLis = 0;
            if (now > 0) {
                // 여기서 find 함수를 사용합니다!
                maxPrevLis = find(1, 0, map.size() - 1, 0, now - 1);
            }

            long currentLis = maxPrevLis + 1;

            // 3. Update: 계산된 길이를 세그먼트 트리에 기록 (update 함수 필요)
            update(1, 0, map.size() - 1, now, currentLis);


            max_Length = Math.max(max_Length, currentLis);
        }

        bw.write(N - max_Length + "");


        bw.flush();
        bw.close();
    }

    static long init(int node, int start, int end){ // 트리 노드 번호, 배열 시작~끝 인덱스
        if(start == end){ // 단일 원소값 가정
            return segment_tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return segment_tree[node] = Math.max(init(node*2, start, mid), init(node*2+1, mid+1, end)); // 부모노드는 자식 노드들 중 최댓값.
    }

    static void update(int node, int start, int end, int index, long value) {
        if (index < start || index > end) return;
        if (start == end) {
            segment_tree[node] = Math.max(segment_tree[node], value); // 기존 값보다 더 긴 LIS가 가능하면 갱신
            return;
        }

        int mid = (start + end) / 2;

        update(node * 2, start, mid, index, value);
        update(node * 2 + 1, mid + 1, end, index, value);
        segment_tree[node] = Math.max(segment_tree[node * 2], segment_tree[node * 2 + 1]);
    }

    static long find(int node, int start, int end, int left, int right){
        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return segment_tree[node];

        // 찾을려는 번호가 범위에 속해 있음.
        int mid = (start + end) / 2;

        return Math.max(find(node * 2, start, mid, left, right),
                find(node * 2 + 1, mid + 1, end, left, right));
    }
}