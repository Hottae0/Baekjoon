import java.io.*;
import java.util.*;

class info{
    long num;
    long streak;

    info(long num, long streak){
        this.num = num;
        this.streak = streak;
    }
}

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long N = Long.parseLong(br.readLine());

        Stack<info> stack = new Stack<>();
        Stack<info> tmp_st = new Stack<>();

        stack.add(new info(Long.parseLong(br.readLine()), 1));

        long cnt = 0;

        // 양 옆으로 갇혀버리면 더이상 쓸 이유가 없음
        // 푸쉬 쓰는 습관 기르기.

        for(int i = 0 ; i < N - 1; i++){
            long next = Long.parseLong(br.readLine());
            long streak = 1;

            while (!stack.isEmpty() && stack.peek().num < next){
                info io = stack.pop();

                cnt += io.streak;
            }

            if(!stack.isEmpty() && stack.peek().num == next){
                info io = stack.pop();

                cnt += io.streak;
                if(!stack.empty()) cnt += 1;

                streak = io.streak + 1;

            } else if(!stack.isEmpty() && stack.peek().num > next){
                cnt += 1;
            } // 옆에 아이랑만 무조건 1쌍 유일.

            stack.push(new info(next, streak));

        }

        bw.write(cnt +  "");

        bw.flush();
        bw.close();
    }

}

