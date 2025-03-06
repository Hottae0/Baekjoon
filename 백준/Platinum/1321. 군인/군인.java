import java.io.*;
import java.util.*;

public class Main {
    
    static long arr[];
    static long segment_tree[];
    
    static int N; 
    
    static int size;
    
    static StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        size = (int) Math.pow(2, h+1);
        
        segment_tree = new long[size];
        arr = new long[N + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 1 ; i < N + 1; i++){
            arr[i] = Long.valueOf(st.nextToken());
        }

        init(1, 1, N);
        
        
        int M = Integer.parseInt(br.readLine());
        

        for(int i = 0 ; i < M; i++){
            StringTokenizer tmp = new StringTokenizer(br.readLine());
            
            int act = Integer.parseInt(tmp.nextToken());
            
            if(act == 1){
                int a = Integer.parseInt(tmp.nextToken());
                long b = Long.valueOf(tmp.nextToken());
                
                update(1, 1, N, a, b);
                arr[a] += b;
                
            }else{
                int a = Integer.parseInt(tmp.nextToken());
                
                find(1, 1, N, 0, a);
            }
                
        }
        
        bw.write(answer.toString());
        
        bw.flush();
        bw.close();
        
    }
    
    static long init(int node, int start, int end){ // 트리 노드 번호, 배열 시작~끝 인덱스
        if(start == end){ // 단일 원소값 가정
            return segment_tree[node] = arr[start];
        }
        
        int mid = (start + end) / 2;
        
        return segment_tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end); // 부모노드는 자식 노드들의 합.
    }
    
    static void update(int node, int start, int end, int idx, long diff){
        if(idx < start || idx > end) return; // 범위 밖임.
        
        segment_tree[node] += diff; // 변경값 적용
        
        int mid = (start+end) / 2;
        
        if(start!= end){ // 리프노드가 아니라면 -> 자식 노드에도 변화값 적용 필요
            update(node*2, start, mid, idx, diff);
            update(node*2 + 1, mid+1, end, idx, diff);
        }
        
    }
    
    static void find(int node, int start, int end, long ac, int num){
        if(ac >= num || num > ac + segment_tree[node]) return;
        
        if(start == end){
            answer.append(start + "\n");
            return;
        }

        // 찾을려는 번호가 범위에 속해 있음.
        int mid = (start + end) / 2;
  
        find(node * 2, start, mid, ac, num);
        find(node * 2 + 1, mid + 1, end, ac + segment_tree[node*2], num);
        
    }
    
}