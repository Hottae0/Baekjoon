import com.sun.jdi.connect.spi.TransportService;

import java.io.*;
import java.util.*;

class info{
    int num;
    int linked;

    info(int num, int linked){
        this.num = num;
        this.linked = linked;
    }

}

// 1974 점프 점프 챔피언십
public class Main {
    static int N;

    static ArrayList<info> arr = new ArrayList<>();
    static int index[];

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        index = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int linked = Integer.parseInt(st.nextToken());

            arr.add(new info(num, linked));
        }

        Collections.sort(arr, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Integer.compare(o1.num, o2.num);
            }
        });

        ArrayList<info> list = new ArrayList<>();
        list.add(new info(-1, -1));

        for (int i = 0; i < N; i++) {
            int start = 0;
            int end = list.size() - 1;

            info io = arr.get(i);
            int tmp = io.linked;

            if (tmp > list.get(end).linked) { // 가장 큰 숫자가 들어옴.
                list.add(arr.get(i));
                index[i] = list.size() - 1;
                continue;
            }
            // 아니라면 이분 탐색을 통해 넣을 자리를 찾아야함
            while (start < end) {
                int mid = (start + end) / 2;

                if (list.get(mid).linked < tmp) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            list.set(end, io);
            index[i] = end;
        }

        sb.append((N - (list.size() - 1)) + "\n");

        int cnt = list.size() - 1;

        Stack<Integer> stack = new Stack<>();

        for (int j = N - 1; j >= 0; j--) {
            if (index[j] == cnt) {
                cnt -= 1;
            }else{
                stack.add(arr.get(j).num);
            }
        }

        while (!stack.isEmpty()){
            sb.append(stack.pop() + "\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }

}