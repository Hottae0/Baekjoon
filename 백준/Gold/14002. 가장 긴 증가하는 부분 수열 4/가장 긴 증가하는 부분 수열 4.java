import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    
    static int arr[];
    static int inc_memorial[];
    static int route[];
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        inc_memorial = new int[N];
        route = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            route[i] = i;
            inc_memorial[i] = -1;
        }
        
        int length = 0;
        int min_idx = 0;
    
        for(int i = 0 ; i < N; i++){
            int tmp = increase(i);
            
            if(length < tmp){
                length = tmp;
                min_idx = i;
            }
        }
        bw.write(length + "\n");
        
        Stack<Integer> sta = new Stack<>();
        
        while (route[min_idx] != min_idx){
            sta.push(arr[min_idx]);
            
            min_idx = route[min_idx];
        } 
        sta.push(arr[min_idx]);
        
        while(!sta.empty()){
            bw.write(sta.pop() + " ");
        }
        
        bw.flush();
        bw.close();
    }
    
    static int increase(int idx){
        if(inc_memorial[idx] == -1){
            inc_memorial[idx] = 1;
            
            for(int i = idx - 1; i > -1; i--){
                if(arr[idx] > arr[i]){
                    int tmp = increase(i) + 1;
                    
                    if(inc_memorial[idx] < tmp){
                        inc_memorial[idx] = tmp;
                        route[idx] = i;
                    }
                    
                }
            }
        }
        
        return inc_memorial[idx];
    }
    
}