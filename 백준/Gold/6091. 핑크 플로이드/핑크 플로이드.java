import java.io.*;
import java.util.*;

class info{
    int v;
    int u;
    int cost;

    info(int v, int u, int cost){
        this.v = v;
        this.u = u;
        this.cost = cost;
    }
}


public class Main {

    static int parent[];

    static int N;

    static ArrayList<info> tree = new ArrayList<>();

    static ArrayList<Integer> ans[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 트리 정점 수

        for(int i = 0 ; i < N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = i + 1; j < N; j++){
                tree.add(new info(i, j, Integer.parseInt(st.nextToken())));
            }
        }

        Collections.sort(tree, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });

        parent = new int[N];
        ans = new ArrayList[N];

        for(int i = 0 ; i < N; i++){
            parent[i] = i;
            ans[i] = new ArrayList<>();
        }

        kruskal();

        for(int i = 0 ; i < N; i++){
            Collections.sort(ans[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < ans.length; i++){
            sb.append(ans[i].size() + " ");
            for(int j : ans[i]){
                sb.append(j + 1 + " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
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

        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }

        return true;
    }

    static void kruskal(){
        for(info io : tree){
            int i = io.v;
            int j = io.u;

            int cost = io.cost;

            if(find(i) != find(j)){ // 연결이 안되어 있음 -> 무조건 최단 거리임. => sort 했잖아
                union(i, j);
                ans[i].add(j);
                ans[j].add(i);
            }
        }


    }

}