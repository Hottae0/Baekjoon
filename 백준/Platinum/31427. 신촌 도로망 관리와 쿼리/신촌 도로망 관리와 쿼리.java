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
    static int N, M, Q;
    static int pidx = 0;

    static HashMap<Character, Integer> map = new HashMap<>();
    static int parent[];

    static ArrayList<info> graph[];


    static long precomputedCounts[][];
    static HashMap<String, Integer> permutationToIndex;
    // 가능한 경우의 수 계산

    static int size[];

    static long pre_cnt[][] = new long[120][5];

    static Map<String, Integer> changer = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점 수
        M = Integer.parseInt(st.nextToken()); // 간선 수
        Q = Integer.parseInt(st.nextToken()); // 비용 최신화 횟수

        map.put('A', 0); map.put('B', 1); map.put('C', 2); map.put('D', 3); map.put('E', 4);

        graph = new ArrayList[5];
        for(int i = 0 ; i < 5; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            char c = st.nextToken().charAt(0);

            int n = map.get(c);

            graph[n].add(new info(u, v));
        }

        precompute();

        StringBuilder ans = new StringBuilder();

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());

            info cost[] = new info[5];
            int original_cost[] = new int[5];
            for(int j = 0 ; j < 5; j ++){
                original_cost[j] = Integer.parseInt(st.nextToken());
                cost[j] = new info(j, original_cost[j]);
            }

            Arrays.sort(cost, new Comparator<info>() {
                @Override
                public int compare(info o1, info o2) {
                    return Integer.compare(o1.j, o2.j);
                }
            });

            StringBuilder keyBuilder = new StringBuilder();
            for (info io : cost) {
                keyBuilder.append(io.i);
            }

            int get_pidx = changer.get(keyBuilder.toString());

            long total_dis = 0;

            for(int j = 0; j < 5; j++){
                total_dis += precomputedCounts[get_pidx][j] * original_cost[j];
            }

            ans.append(total_dis + "\n");
        }

        bw.write(ans.toString().trim());

        bw.flush();
        bw.close();
    }

    static void precompute() {
        precomputedCounts = new long[120][5];
        permutationToIndex = new HashMap<>();
        int[] types = {0, 1, 2, 3, 4};

        // 재귀적으로 모든 순열을 생성하고, 각 순열마다 MST를 계산
        generatePermutations(types, 0);
    }

    static void generatePermutations(int arr[], int k){
        if(k == arr.length){
            kruskal(arr);
            return;
        }

        for(int i = k; i < arr.length; i++){
            swap(arr, i, k);
            generatePermutations(arr, k + 1);
            swap(arr, i, k);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int find(int a){
        if(parent[a] == a){
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x == y){
            return false;
        }

        if(size[x] < size[y]){
            parent[x] = y;
            size[y] += size[x];
        }else{
            parent[y] = x;
            size[x] += size[y];
        }

        return true;
    }

    static void kruskal(int arr[]) {
        parent = new int[N];
        size = new int[N];

        for(int i = 0 ; i < N; i++){
            parent[i] = i;
            size[i] = 1;
        }

        long [] cnt = new long[5];
        int usedEdge = 0;

        for(int type : arr){
            if(usedEdge == N - 1) break; // mst 완성

            for(info io : graph[type]){
                if(find(io.i) != find(io.j)){
                    union(io.i, io.j);
                    cnt[type] += 1;
                    usedEdge += 1;
                }
            }
        }

        StringBuilder keyBuilder = new StringBuilder();
        for (int type : arr) {
            keyBuilder.append(type);
        }

        String key = keyBuilder.toString();
        changer.put(key, pidx);
        precomputedCounts[pidx++] = cnt;
    }

}