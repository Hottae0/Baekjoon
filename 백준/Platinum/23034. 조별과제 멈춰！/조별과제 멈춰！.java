import java.io.*;
import java.util.*;

class info{
    int A;
    int B;
    int C;

    info(int A, int B, int C){
        this.A = A;
        this.B = B;
        this.C = C;
    }

}
class Node{
    int j;
    int cost;

    Node(int j, int cost){
        this.j = j;
        this.cost = cost;
    }
}

public class Main {
    static ArrayList<info> graph = new ArrayList<>();

    static ArrayList<ArrayList<Node>> Nd = new ArrayList<>();
    static int parent[];

    static int N;
    static int M;

    static int dis = 0;

    static int answer[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 회선 수

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int tmp1 = Integer.parseInt(st.nextToken()); // 학생 번호 1
            int tmp2 = Integer.parseInt(st.nextToken()); // 학생 번호 2
            int C = Integer.parseInt(st.nextToken()); // 비용

            graph.add(new info(Math.min(tmp1,tmp2), Math.max(tmp1, tmp2), C));

        } // 회선 입력

        Collections.sort(graph, new Comparator<info>(){
            @Override
            public int compare(info o1, info o2){
                return o1.C - o2.C;
            }
        }); // 정렬

        parent = new int[N + 1];

        for(int i = 0; i < N + 1; i++){
            Nd.add(new ArrayList<>());
            parent[i] = i;
        }

        int Q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        kruscal();

        answer = new int[N + 1][N + 1];

        for(int i = 1; i < N + 1; i++){
            bfs(i);
        }

        for(int i = 0 ; i < Q; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int T = dis;

            sb.append(T - answer[a][b] + "\n");
        }

        bw.write(sb.toString());

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

    static void kruscal() {

        for (int i = 0; i < M; i++){
            info io = graph.get(i);

            if (find(io.A) != find(io.B)) { // 연결되어 있지 않다면
                union(io.A, io.B);
                dis += io.C;

                Nd.get(io.A).add(new Node(io.B, io.C));
                Nd.get(io.B).add(new Node(io.A, io.C));
            }
        }
    }


    static void bfs(int start){
        boolean visit[] = new boolean[N + 1];
        Arrays.fill(visit, false);

        answer[start][start] = 0;
        visit[start] = true;

        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);

        while (!q.isEmpty()){
            int n = q.poll();

            for(int i = 0 ; i < Nd.get(n).size(); i++){
                int j = Nd.get(n).get(i).j;
                int cost = Nd.get(n).get(i).cost;

                if(visit[j]) continue;

                answer[start][j] = Math.max(Math.max(answer[start][j], answer[start][n]), cost);

                visit[j] = true;
                q.add(j);
            }
        }

    }

}