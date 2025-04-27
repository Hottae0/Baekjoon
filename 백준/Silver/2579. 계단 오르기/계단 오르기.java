
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int stair[];
	static Integer memorial[];
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int num = Integer.parseInt(br.readLine());
        
        stair = new int[num];
        memorial = new Integer[num];
        
        for(int i = 0; i < num ; i++) {
        	stair[i] = Integer.parseInt(br.readLine());
        }
        
        memorial[0] = stair[0];
        
        if(num > 1) {
        	memorial[1] = stair[1] + stair[0];
        	if(num > 2) {
        		memorial[2] = stair[2] + Math.max(stair[0], stair[1]);
        	}
        }
        

        System.out.println( ac_num(num - 1));
        
	}
    
    static int ac_num(int n) {

    	if(memorial[n] == null) {
    		memorial[n] = Math.max(ac_num(n - 2), ac_num(n - 3) + stair[n - 1]) + stair[n];
    	}
    	
    	return	memorial[n];
    }
}