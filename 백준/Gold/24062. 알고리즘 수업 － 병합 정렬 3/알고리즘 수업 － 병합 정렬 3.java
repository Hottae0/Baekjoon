import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[] ans;
    static int[] tmp;
    static int N;
    static boolean found = false;
    // ans와 일치하는 원소의 개수를 추적하는 변수
    static int matchCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        ans = new int[N + 1];
        tmp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            ans[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 초기에 arr와 ans가 얼마나 일치하는지 확인하여 matchCount를 설정
        for (int i = 1; i <= N; i++) {
            if (arr[i] == ans[i]) {
                matchCount++;
            }
        }

        if (matchCount == N) {
            found = true;
        }

        // 2. found가 false일 때만 정렬 시작
        if (!found) {
            merge_sort(1, N);
        }

        bw.write(found ? "1" : "0");

        bw.flush();
        bw.close();
    }

    static void merge_sort(int p, int r) {
        if (found) {
            return;
        }
        if (p < r) {
            int q = (p + r) / 2;
            merge_sort(p, q);
            merge_sort(q + 1, r);
            merge(p, q, r);
        }
    }

    static void merge(int p, int q, int r) {
        if (found) {
            return;
        }

        int i = p;
        int j = q + 1;
        int t = p; // tmp 배열 인덱스를 arr와 동일하게 사용하면 복사할 때 편리

        // 두 부분 배열을 병합하여 tmp에 저장
        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }
        while (i <= q) {
            tmp[t++] = arr[i++];
        }
        while (j <= r) {
            tmp[t++] = arr[j++];
        }

        // tmp에 저장된 정렬된 결과를 다시 arr로 복사하면서 확인
        for (int k = p; k <= r; k++) {
            if (found) return;

            int oldValue = arr[k];
            int newValue = tmp[k];

            if (oldValue != newValue) {
                // 값이 변경될 때만 matchCount를 갱신
                if (oldValue == ans[k]) {
                    matchCount--;
                }
                if (newValue == ans[k]) {
                    matchCount++;
                }

                arr[k] = newValue;

                // 갱신 직후, 모든 원소가 일치하는지 확인
                if (matchCount == N) {
                    found = true;
                    return;
                }
            }
        }
    }
}