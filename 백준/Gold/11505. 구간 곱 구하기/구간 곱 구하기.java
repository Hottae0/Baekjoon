import java.io.*;
import java.util.*;

public class Main {
    
    static long arr[];
    static long segment_tree[];
    
    static long div = 1_000_000_007;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, h+1);
        
        segment_tree = new long[size];
        arr = new long[N + 1];
        
        for(int i = 1 ; i < N + 1; i++){
            arr[i] = Long.valueOf(br.readLine()) % div;
        }
        
        init(1, 1, N);
        
        for(int i = 0 ; i < M+K; i++){
            StringTokenizer tmp = new StringTokenizer(br.readLine());
            
            int act = Integer.parseInt(tmp.nextToken());
            
            int a = Integer.parseInt(tmp.nextToken());
            long b = Long.valueOf(tmp.nextToken());
            
            if(act == 1){
                arr[a] = b % div;
                update(1, 1, N, a);
            }else{
                bw.write(mul(1, 1, N, a, b) + "\n");
            }
        }
        
        bw.flush();
        bw.close();
        
    }
    
    static long init(int node, int start, int end){ // 트리 노드 번호, 배열 시작~끝 인덱스
        if(start == end){ // 단일 원소값 가정
            return segment_tree[node] = arr[start];
        }
        
        int mid = (start + end) / 2;
        
        return segment_tree[node] = (init(node*2, start, mid) * init(node*2+1, mid+1, end)) % div ; // 부모노드는 자식 노드들의 합.
    }
    
    static long update(int node, int start, int end, int idx){
        if(idx < start || idx > end) return segment_tree[node]; // 범위 밖임.
        
        if(start == end){ // 리프노드
            return segment_tree[node] = arr[start];
        }
        
        int mid = (start + end) / 2;
        
        return segment_tree[node] = ((update(node*2, start, mid, idx)%div) *
        (update(node*2 + 1, mid+1, end, idx)%div)) % div;
        
    }
    
    static long mul(int node, int start, int end, int left, long right){
        if(left > end || start > right) return 1 ; // 범위 밖임
        
        if(left <= start && end <= right){
            return segment_tree[node]; // 범위 내에 들면 바로 리턴
        }
        
        int mid = (start+end) / 2;
        
        return (mul(node*2, start, mid, left, right) * mul(node*2 + 1, mid+1, end, left, right)) % div;
    }
    
}