// 어렵게 생각하지 말 것. 제목이 트리라고 꼭 트리를 사용해야하는 건 아님.
import java.io.*;
import java.util.*;

public class Main {
    static int cnt = 0;
    static int del;
    static int root = 0;
    
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N; i++){
            graph.add(new ArrayList<>());
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0 ; i < N; i++){
            int num = Integer.parseInt(st.nextToken());

            if(num == -1){
                root = i;
                continue;
            }
            
            graph.get(num).add(i); // num은 자식노드로 i 를 가짐.
        }
        
        del = Integer.parseInt(br.readLine()); // 지워 버릴 노드 번호.

        dfs(root);
        
        bw.write(cnt + "");
        
        bw.flush();
        bw.close();
        
    }
    
    static void dfs(int parent){
        if(parent == del){
            return;
        } // 루트노드가 삭제노드일 떄를 위함.

        int child_count = 0;
 
        for(int i = 0 ; i < graph.get(parent).size(); i++){
            int child = graph.get(parent).get(i);
            
            if(child != del){
                dfs(child);
                child_count++;
            }
            
        }

        if(child_count == 0){ // 리프노드임.
            cnt++;
        }
        
    }
        
        
    
    
}