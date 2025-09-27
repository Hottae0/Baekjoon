import java.io.*;

import java.util.*;

class Node{
    int i;
    int cost;

    Node(int i, int cost){
        this.i = i;
        this.cost = cost;
    }
}

public class Main {

    static int n;
    static int m;
    
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    
    static boolean visit[];
    static int arr[];
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        m = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());            

            graph.get(a).add(new Node(b,c));

        }
        
        StringTokenizer tmp = new StringTokenizer(br.readLine());
        
        int start = Integer.parseInt(tmp.nextToken()) - 1;
        int end = Integer.parseInt(tmp.nextToken()) - 1;        
        
        visit = new boolean[n];
        arr = new int[n];
        
        Arrays.fill(arr, 100_000_001);
        
        bw.write(Djikstra(start, end) + "");
        
        bw.flush();
        bw.close();
    }

   

    static int Djikstra(int start, int end){ 
        arr[start] = 0;
        
        for(int i = 0; i < n ; i++){
            int min = 100_000_001;
            int min_idx = 0;
            
            for(int j = 0; j < n; j++){
                if(!visit[j] && arr[j] < min){
                    min = arr[j];
                    min_idx = j;
                }
            }
            visit[min_idx] = true;
            
            for(int j = 0; j < graph.get(min_idx).size(); j++){
                Node nd = graph.get(min_idx).get(j);
                
                if(arr[nd.i] > arr[min_idx] + nd.cost){
                    arr[nd.i] = arr[min_idx] + nd.cost;
                }
            }

        }
        
        return arr[end];

    }

}