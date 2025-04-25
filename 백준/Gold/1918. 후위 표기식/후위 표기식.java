import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
   
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char S[] = br.readLine().toCharArray();
        
        Stack<Character> op = new Stack<>();
        
        for(int i = 0; i < S.length; i++){ 
            char tmp = S[i];
            
            if((int)tmp > 64 && (int)tmp < 91){ // 피연산자 == 알파벳
                answer.append(tmp);
            }else{
                
                if(tmp == '(') {
                    op.push(tmp);
                }
                else if(tmp == ')'){
                    while (!op.empty() && op.peek() != '('){
                        char c = op.pop();
                        
                        answer.append(c);
                    } // 스택 맨 위에 ( 존재
                    
                    op.pop(); // ( 없애기
                    
                }
                else{
                    while(!op.empty() && priority(tmp) <= priority(op.peek())){ // 토큰의 연산자가 우선순위
                        char c = op.pop();
                        
                        answer.append(c);
                    }
                    
                    op.push(tmp);
                    
                }
                
            }
            
        }
        
        while(!op.empty()){
            char c = op.pop();
            if(c == '(') continue;
            
            answer.append(c);
        }
        
        bw.write(answer.toString());
        
        bw.flush();
        bw.close();
    }
    
    static int priority(char c){
        if(c == '(') return 0;
        else if(c == '+' || c == '-') return 1;
        else return 2;
    }
}