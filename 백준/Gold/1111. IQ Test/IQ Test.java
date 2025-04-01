import java.io.*;
import java.util.*;

class info{
    int a;
    int b;

    info(int a, int b){
        this.a = a;
        this.b = b;
    }
}

public class Main {

    static Queue<info> que = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int arr[] = new int[N];

        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(N > 2){
            find_fir(arr[0], arr[1]);

            for(int i = 2; i < N; i++){
                find_later(arr[i - 1], arr[i]);
            }

            if(que.size() == 0){
                bw.write("B");
            }else{
                HashSet<Integer> set = new HashSet<>();
                int last = arr[N - 1];
                int tmp = Integer.MAX_VALUE;

                while (que.size() > 0){
                    info io = que.poll();

                    int ans = tmp = last * io.a + io.b;
                    set.add(ans);
                }

                if(set.size() > 1) bw.write("A");
                else if(set.size() == 0) bw.write("B");
                else bw.write(tmp + "");



            }
        }else if(N == 1){
            bw.write("A");
        }else if(N == 2){
            if(arr[0] == arr[1]) bw.write(arr[0] + "");
            else bw.write("A");
        }

        bw.flush();
        bw.close();

    }

    static void find_fir(int x, int y){
        for(int a = -200; a < 201; a++){
            int b = y - a * x;

            que.add(new info(a, b));
        }

    }

    static void find_later(int x, int y){
        int size = que.size();

        for(int i = 0 ; i < size; i++){
            info io = que.poll();

            if(x * io.a + io.b == y){
                que.add(new info(io.a, io.b));
            }
        }



    }


}

