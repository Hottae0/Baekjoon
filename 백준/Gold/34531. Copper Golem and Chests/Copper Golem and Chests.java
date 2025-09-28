import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class Main {
    static int parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parent = new int[M];

            for(int i = 0 ; i < M; i++){
                parent[i] = i;
            }

            st = new StringTokenizer(br.readLine());

            int item[] = new int[N];
            for(int i = 0 ; i < N; i++){
                item[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            st = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < M; i++){ // i번째의 아이템은 다음으로 num으로 감
                int num = Integer.parseInt(st.nextToken()) - 1;

                if(find(i) != find(num)) union(i, num);
            }

            ArrayList<ArrayList<Integer>> arr = new ArrayList<>();

            boolean new_set[] = new boolean[M];

            int idx = 0;
            HashMap<Integer, Integer> map = new HashMap<>();

            for(int i = 0 ; i < M; i++){
                int root = find(i);

                if(!new_set[root]){
                    new_set[root] = true;

                    map.put(root, idx++);
                    arr.add(new ArrayList<>());

                }

                arr.get(map.get(root)).add(i);
            }

            for(int i = 0 ; i < idx; i++){
                Collections.sort(arr.get(i));
            }

            boolean sortable = true;


            int prev = arr.get(map.get(find(item[0]))).get(0); //0번 아이템에서 가장 작은 번호.

            for(int i = 0 ; i < N; i++){
                int now = map.get(find(item[i]));

                ArrayList<Integer> cur_list = arr.get(now);

                int foundIndex = Collections.binarySearch(cur_list, prev);
                //1. prev 값을 정확히 찾은 경우 -> foundIndex는 0 이상
                //2. prev 값을 찾지 못한 경우 -> foundIndex는 음수.

                if (foundIndex < 0) {
                    foundIndex = -(foundIndex + 1);
                }

                if (foundIndex >= cur_list.size()) {
                    sortable = false;
                    break;
                }

                prev = cur_list.get(foundIndex);

            }


            if(sortable) sb.append("YES\n");
            else sb.append("NO\n");

        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }

    static boolean union(int a, int b){ // 집합의 대푯값을 작은걸로~
        int x = find(a);
        int y = find(b);

        if(x == y){
            return false;
        }

        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }

        return true;
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}