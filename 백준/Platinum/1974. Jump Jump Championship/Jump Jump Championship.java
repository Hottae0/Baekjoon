import java.io.*;
import java.util.*;

// 1974 점프 점프 챔피언십
public class Main {
    static int N;

    static int arr[];
    static int index[];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            index = new int[N];
            arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            ArrayList<Integer> list = new ArrayList<>();
            list.add(Integer.MIN_VALUE);

            for (int i = 0; i < N; i++) {
                int tmp = arr[i];

                int start = 0;
                int end = list.size() - 1;

                if (tmp > list.get(end)) { // 가장 큰 숫자가 들어옴.
                    list.add(tmp);
                    index[i] = list.size() - 1;
                    continue;
                }
                // 아니라면 이분 탐색을 통해 넣을 자리를 찾아야함
                while (start < end) {
                    int mid = (start + end) / 2;

                    if (list.get(mid) < tmp) {
                        start = mid + 1;
                    } else {
                        end = mid;
                    }
                }

                list.set(end, tmp);
                index[i] = end;
            }

            sb.append(list.size() - 1 + "\n");

            int cnt = list.size() - 1;
            Stack<Integer> stack = new Stack<>();

            for (int j = N - 1; j >= 0; j--) {
                if(cnt == 0) break;
                if (index[j] == cnt) {
                    stack.add(j + 1);
                    cnt -= 1;
                }
            }

            while (!stack.isEmpty()) {
                sb.append(stack.pop() + " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }

}