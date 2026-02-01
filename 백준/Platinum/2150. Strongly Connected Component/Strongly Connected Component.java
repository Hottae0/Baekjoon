import javax.swing.border.SoftBevelBorder;
import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean visited[];
    static boolean in_stack[];

    static int dfn[];
    static int low[];

    static Stack<Integer> stack = new Stack<>();

    static int V, E;
    static int cnt = 1;

    static List<List<Integer>> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        visited = new boolean[V + 1];
        in_stack = new boolean[V + 1];
        low = new int[V + 1];
        dfn = new int[V + 1];

        for(int i = 0; i < V + 1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
        }

        for(int i = 1; i < V + 1; i++){
            Collections.sort(graph.get(i));
        }

        for(int i = 1; i < V + 1; i++){
            if(!visited[i]){
                dfs(i);
            }
        }

        Collections.sort(ans, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });

        StringBuilder sb = new StringBuilder();

        sb.append(ans.size() + "\n");

        for(int i = 0 ; i < ans.size() ; i++){
            for(int j : ans.get(i)){
                sb.append(j + " ");
            }sb.append("\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }

    static void dfs(int n){
        visited[n] = true;

        stack.add(n);
        dfn[n] = cnt++;
        low[n] = dfn[n];
        in_stack[n] = true;

        for(int i = 0; i < graph.get(n).size(); i++){
            int v = graph.get(n).get(i);

            if(!visited[v]){
                dfs(v);
                low[n] = Math.min(low[v], low[n]);
            }else if(in_stack[v]){
                // 벡 엣지임
                low[n] = Math.min(dfn[v], low[n]);
            }
        }

        if(low[n] == dfn[n]){
            List<Integer> tmp_scc = new ArrayList<>();

            while (true){
                int node = stack.pop();
                in_stack[node] = false;

                tmp_scc.add(node);
                if(node == n) break;
            }

            Collections.sort(tmp_scc);
            tmp_scc.add(-1);



            ans.add(tmp_scc);
        }

    }
}