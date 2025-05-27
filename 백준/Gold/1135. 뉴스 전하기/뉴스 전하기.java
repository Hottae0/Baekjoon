import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N]; // 자식 전부가 걸리는 시간 계산 할 거임!

        for(int i = 0 ; i < N; i++){
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        String trash = st.nextToken();

        for(int i = 1; i < N; i++){
            int tmp = Integer.parseInt(st.nextToken());
            graph.get(tmp).add(i);
        }

        bw.write(DP(0) + "");

        bw.flush();
        bw.close();

    }

    static int DP(int num){
        for(int i = 0 ; i < graph.get(num).size(); i++){
            int tmp = graph.get(num).get(i);

            dp[tmp] = 1 + DP(tmp);
        } // 자식 순회 및 높이만큼 1 더하기

        Collections.sort(graph.get(num), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dp[o2] - dp[o1];
            }
        }); // 자식 갯수 내림차순 정리
        // -> 어차피 자식 갯수가 가장 많은 애들부터 시작하는게 경제적이다. ! 예외는 없을듯

        int cnt = 0;

        for(int i = 0 ; i < graph.get(num).size(); i++){
            int tmp = graph.get(num).get(i);

            cnt = Math.max(dp[tmp] + i, cnt);
        }
        return cnt;
    }

}