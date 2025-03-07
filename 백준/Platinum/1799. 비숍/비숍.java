import java.io.*;
import java.util.*;

class info{
    int i;
    int j;
    
    info(int i, int j){
        this.i = i;
        this.j = j;
    }
}

public class Main {
    
    static boolean visit[][];
    
    static ArrayList<info> Node_white = new ArrayList<>();
    static ArrayList<info> Node_black = new ArrayList<>();
    
    static int max_white = 0;
    static int max_black = 0;
    
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        visit = new boolean[N][N];
        
        for(int i = 0; i < N; i++){
            StringTokenizer tmp = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                if(Integer.parseInt(tmp.nextToken()) == 1){
                    
                    if(i % 2 == 0){
                        if(j % 2 == 0){
                            Node_white.add(new info(i, j));
                        }else{
                            Node_black.add(new info(i, j));
                        }
                    }else{
                        if(j % 2 == 0){
                            Node_black.add(new info(i,j));
                        }else{
                            Node_white.add(new info(i,j));
                        }
                    }
                    
;
                }
            }
        }
        
        bishop_white(0,0);
        bishop_black(0,0);
        
        System.out.println(max_white + max_black);
    }
    
    static void bishop_white(int i, int cnt){
        
        if(i == Node_white.size()){
            max_white = Math.max(max_white, cnt);
            return;
        }
        
        info io = Node_white.get(i);
        
        int a = io.i;
        int b = io.j;
        
        if(check(a, b)){ // 둘 수 있다면
            visit[a][b] = true;
            bishop_white(i + 1, cnt + 1);
            visit[a][b] = false;
        }
            bishop_white(i+1, cnt);
        
    }
    
    static void bishop_black(int i, int cnt){
        
        if(i == Node_black.size()){
            max_black = Math.max(max_black, cnt);
            return;
        }
        
        info io = Node_black.get(i);
        
        int a = io.i;
        int b = io.j;
        
        if(check(a, b)){ // 둘 수 있다면
            visit[a][b] = true;
            bishop_black(i + 1, cnt + 1);
            visit[a][b] = false;
        }
            bishop_black(i+1, cnt);
        
    }
    
    static boolean check(int a, int b){
        for(int i = 0; i < N; i++){
            
            if(b - Math.abs(a-i) >= 0){
                if(visit[i][b - Math.abs(a-i)]){
                    return false;
                }
            }
            
            if(b + Math.abs(a-i) < N){
                if(visit[i][b + Math.abs(a-i)]){
                    return false;
                }
            }            
        }
        
        return true; // (a,b)에 비숍을 둘 수 있다면 true를 반환
    }
    
    /*
<반례>
5
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
ans : 8

    */
}