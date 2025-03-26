import java.io.*;
import java.util.*;

class info{
    int i;
    int j;
    int width;
    
    info(int i, int j, int width){
        this.i = i;
        this.j = j;
        this.width = width;
    }
}

public class Main {
    
    static ArrayList<info> graph = new ArrayList<>();
    static int parent[];
    
    static int N;
    static int M;
    static int K;
    
    static int cnt = 0;
    static int max = -1;
    
    static info[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        parent = new int[N + 1];
        
        for(int i = 1; i < N + 1; i++){
            parent[i] = i;
        }
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken()); // 음수일 수도 있다~
            
            graph.add(new info (A,B,C));
        }
        
        Collections.sort(graph, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2){
                return o2.width - o1.width;
            }
        });
        
        arr = new info[K];
        
        for(int i = 0 ; i < K; i++){
            st = new StringTokenizer(br.readLine());
            
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            arr[i] = new info(A,B,0);
        }
        
        kruskal();
        
        StringBuilder sb = new StringBuilder();
        
        for(info i : arr){
            sb.append(i.width + "\n");
        }
        
        bw.write(sb.toString());
        
        bw.flush();
        bw.close();

    } 
    
    static void kruskal(){ // 크루스칼 알고리즘 구현
        for(int i = 0; i < graph.size(); i++){
            info io = graph.get(i);
            
            if(max != io.width){
                
                for(int j = 0 ; j < K; j++){
                    info tmp = arr[j];
                    
                    if(tmp.width == 0 && find(tmp.i) == find(tmp.j)){
                        tmp.width = max;
                        cnt++;
                    }
                }
                
                if(cnt == K){
                    return;
                }
                
                max = io.width;
                
            }
            
            if(find(io.i) != find(io.j)){
                union(io.i, io.j);
            }
            
        }
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
    
    static int find(int x){ //부모 노드 찾기
        if(parent[x] == x){
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }
    
    
    
}