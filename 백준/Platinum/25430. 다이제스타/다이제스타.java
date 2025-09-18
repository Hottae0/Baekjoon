import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // Key(가중치)를 기준으로 자동 정렬되는 TreeMap 사용
        TreeMap<Integer, ArrayList<int[]>> edgesByWeight = new TreeMap<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 가중치 c를 Key로 하여 간선 (u, v)를 저장
            edgesByWeight.computeIfAbsent(c, k -> new ArrayList<>()).add(new int[]{u, v});
        }

        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        // DP 테이블
        long[] D = new long[N + 1]; // 메인 DP 테이블
        long[] T = new long[N + 1]; // 임시 DP 테이블
        Arrays.fill(D, INF);
        Arrays.fill(T, INF);

        // 시작점 초기화
        D[startNode] = 0;

        // 가중치가 작은 순서대로 순회
        for (int weight : edgesByWeight.keySet()) {
            ArrayList<int[]> edges = edgesByWeight.get(weight);

            // 1. 경로 후보 계산 (Relaxation) -> 임시 테이블 T에 저장
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                
                // D[v]가 INF가 아니라는 것은 v까지 경로가 존재한다는 의미
                if (D[v] != INF) {
                    T[u] = Math.min(T[u], D[v] + weight);
                }
                if (D[u] != INF) {
                    T[v] = Math.min(T[v], D[u] + weight);
                }
            }
            
            // 2. DP 테이블 최종 갱신 -> T의 결과를 D에 반영
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                
                D[u] = Math.min(D[u], T[u]);
                D[v] = Math.min(D[v], T[v]);
                // T는 다음 가중치 계산을 위해 초기화 (선택적: 어차피 min으로 갱신되므로 생략 가능)
                // T[u] = INF; 
                // T[v] = INF;
            }
        }

        if (D[endNode] == INF) {
            System.out.println("DIGESTA");
        } else {
            System.out.println(D[endNode]);
        }
    }
}

