import java.io.*;
import java.util.*;

class Node{
    int i;
    int j;
    int cost;

    Node(int i, int j, int cost){
        this.i = i;
        this.j = j;
        this.cost = cost;
    }
}

public class Main {
    static ArrayList<Node> arr = new ArrayList<>();
    static int parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        parent = new int[N];

        for(int i = 0 ; i < N; i++){
            parent[i] = i;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ;j++){
                int num = Integer.parseInt(st.nextToken());

                arr.add(new Node(i, j, num));
            }
        }

        Collections.sort(arr, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        long ans = kruskal();

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }

    static int find(int x){
        if(parent[x] == x){
            return parent[x] = x;
        }

        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x == y) return false;

        if(x > y){
            parent[x] = y;
        }else{
            parent[y] = x;
        }

        return true;
    }

    static long kruskal(){
        long sum = 0;

        for(int i = 0 ; i < arr.size() ; i++){
            Node nd = arr.get(i);

            int a = nd.i;
            int b = nd.j;

            if(union(a, b)){
                sum += nd.cost;
            }
        }
        return sum;
    }
}