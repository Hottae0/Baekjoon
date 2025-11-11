import java.io.*;
import java.util.*;

class info{
    int i;
    int j;

    info(int i, int j){
        this.i = i;
        this.j = j;
    }
}

public class Main {
    static long segment[];
    static info arr[];
    static long cnt[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = read();
        int M = read();

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, h+1);

        segment = new long[size];
        arr = new info[M];
        cnt = new long[N + 1];

        for(int i = 0 ; i < M; i++){
            int a = read();
            int b = read();

            arr[i] = new info(a, b);

            cnt[b]++;
        }

        Arrays.sort(arr, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                if(o1.i == o2.i){
                    return Integer.compare(o1.j, o2.j);
                }
                return Integer.compare(o1.i, o2.i);
            }
        });

        init(1, 1, N);

        long sum = 0;

        for(int i = 0 ; i < M; i++){
            info io = arr[i];
            int b = io.j;

            update(1, 1, N, b, -1);

            if(b > 1) sum += sum(1, 1, N, 1, b - 1);
        }

//        for(info io : arr){
//            bw.write(io.i + " " + io.j + "\n");
//        }

        bw.write(sum + "");

        bw.flush();
        bw.close();
    }

    static long init(int node, int start, int end){
        if(start == end){
            return segment[node] = cnt[start];
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

    static int read() throws IOException {
        int n = 0;
        int i;
        while ((i = System.in.read()) >= '0') {
            n = (n<<3) + (n<<1) + (i&15);
        }
        return n;
    }

}