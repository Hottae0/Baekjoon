import java.io.*;
import java.util.*;
 // 21924 도시 건설
class info{
    int i;
    int j;
    long c;
    info(int i, int j, long c){
        this.i = i;
        this.j = j;
        this.c = c;
    }
}

public class Main {
    static int parent[];
        static ArrayList<info> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        for(int i = 0;  i < N + 1; i++){
            parent[i] = i;
        }

        long sum = 0;

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            arr.add(new info(a, b, c));

            sum += c;
        }

        Collections.sort(arr, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Long.compare(o1.c, o2.c);
            }
        });

        long ans = kruskal();

        boolean tmp = false;

        int check = find(parent[1]);
        for(int i = 1; i <= N; i++){
            if(check != find(parent[i])){
                tmp = true;
                break;
            }
        }

        if(tmp) bw.write("-1");
        else bw.write(sum - ans + "");

        bw.flush();
        bw.close();
    }

    static long kruskal(){
        long ans = 0;

        for(int i = 0 ; i < arr.size(); i++){
            info io = arr.get(i);

            if(find(io.i) != find(io.j)){
                union(io.i, io.j);
                ans += io.c;
            }
        }

        return ans;
    }

    static boolean union(int a, int b){
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

    static int find(int x){ //부모 노드 찾기
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}