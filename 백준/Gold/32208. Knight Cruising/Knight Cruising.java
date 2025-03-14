import java.io.*;
import java.util.*;

class info{
    int x;
    int y;
    int z;
}

public class Main {


    public static void main(String[] args) throws IOException {
        // x, y, z의 순서는 상관이 없을 듯하다. 그렇다면 +- 1,2,3을 통해 만들 수 있는 집합을 만든다면?
        // 무조건 1,2,3의 숫자를 한번에 다 써야하기 때문. 과연 어떻게 할까? 으에으에에ㅔ으ㅔ으ㅔ에ㅡㄴ에ㅔㄴ에ㅔㄴ에
        // 다 합친 값이 항상 짝수다? 그렇다는 건? 모든 합이 짝수여야하는거 아닌감?


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            long sum = 0;

            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            long tmp = Math.abs(a + b + c) % 2;

            if(tmp == 1){
                bw.write("NO\n");
            }else{
                bw.write("YES\n");
            }
        }

        bw.flush();
        bw.close();

    }
}

