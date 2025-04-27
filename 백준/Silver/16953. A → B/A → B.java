import java.io.*;
import java.util.*;

public class Main {
    static long A;
    static long B;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        dfs(A, 0);

        System.out.println(min == Integer.MAX_VALUE?- 1: min + 1);
    }

    static void dfs(Long sum, int count){
        if(sum.equals(B)){
            min = Math.min(min, count);
            return;
        }else if(sum > B){
            return;
        }

        dfs(sum * 2, count + 1);
        dfs((sum * 10) + 1, count + 1);

    }
}