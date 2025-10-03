import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static ArrayList<Integer> memorial = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine()); // 1_000_000 이므로 dp로 하면 n^2 -> 시간 초과

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        memorial.add(0);
        
        for(int i = 0 ; i < N; i++){
            int tmp = Integer.parseInt(st.nextToken());
            
            int start = 0;
            int end = memorial.size() - 1;

            if(memorial.get(memorial.size() - 1) < tmp){
                memorial.add(tmp);
            }else{
                
                while(start < end){
                
                    int mid = (start+end)/2;
                    
                    if(memorial.get(mid) < tmp){
                        start = mid + 1;
                    }else{
                        end = mid;
                    }
                }
                
                memorial.set(end, tmp);
            }
        }
        
        System.out.println(memorial.size() - 1);

    }


}