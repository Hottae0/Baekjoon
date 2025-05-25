import java.io.*;
import java.util.*;

class info implements Comparable<info>{
    int start; // 거리
    int end; // 연료량
    
    info(int start, int end){
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int compareTo(info io){
        return this.start - io.start; // 시작 시간 기준 정렬.
    }
}

class room implements Comparable<room>{
    int num;
    int end;
    
    room(int num, int end){
        this.num = num;
        this.end = end;
    }
    
    @Override
    public int compareTo(room rm){
        return this.end - rm.end;
    }
}

public class Main {
    
    static int N;
    
    static PriorityQueue<info> arr = new PriorityQueue<>();
    static PriorityQueue<room> end = new PriorityQueue<>(); // 끝나는 시간. 
    static PriorityQueue<Integer> num = new PriorityQueue<>();
    
    static int ans[] = new int[100_001];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            arr.add(new info(a, b));
        }
        
        int cnt = 1;
        
        for(int i = 0 ; i < N; i++){
            
            info io = arr.poll();
            
            int now = io.start;
            int fut = io.end;
            
            while(!end.isEmpty() && end.peek().end <= now){
                room rm = end.poll();
                
                num.add(rm.num);
            }
            
            if(num.isEmpty()){
                ans[cnt] += 1;
                
                end.add(new room(cnt, fut));
                cnt += 1;
            }else{
                int tmp = num.poll();
                
                ans[tmp] += 1;
                end.add(new room(tmp, fut));
            }
            
            
            
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1 ; i < 100_001; i++){
            if(ans[i] == 0){
                break;
            }
            sb.append(ans[i] + " ");
        }
        
        bw.write(cnt - 1 + "\n");
        bw.write(sb.toString());
        
        bw.flush();
        bw.close();
        
    }
}