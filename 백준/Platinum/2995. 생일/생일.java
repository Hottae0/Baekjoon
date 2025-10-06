import java.io.*;
import java.util.*;

class info{
    int x;
    int y;

    info(int x, int y){
        this.x = x;
        this.y = y;
    }

}

// 2995 생일
public class Main {
    static int N;

    static ArrayList<info> arr = new ArrayList<>();
    static int index[];

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        index = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr.add(new info(x, y));
        }

        Collections.sort(arr, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                if(o1.x == o2.x){
                    return Integer.compare(o2.y, o1.y);
                }

                return Integer.compare(o1.x, o2.x);
            }
        });

        ArrayList<info> list = new ArrayList<>();
        list.add(new info(-1, 1_000_001));

        for(int i = 0 ; i < N; i++){
            info io = arr.get(i);

            int start = 0;
            int end = list.size() - 1;

            if(check(list.get(end), io)){
                list.add(io);
                index[i] = list.size() - 1;
                continue;
            }

            while(start < end){
                int mid = (start + end) / 2;

                if( check( list.get(mid), io ) ){
                    start = mid + 1;
                }else{
                    end = mid;
                }
            }

            list.set(end, io);
            index[i] = end;
        }

        sb.append(list.size() - 1 + "\n");

        int cnt = list.size() - 1;

        Stack<info> stack = new Stack<>();

        for (int j = N - 1; j >= 0; j--) {
            if(cnt == 0) break;
            if (index[j] == cnt) {
                cnt -= 1;
                stack.add(arr.get(j));
            }
        }

        while (!stack.isEmpty()){
            info io = stack.pop();

            sb.append(io.x + " " + io.y + "\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }

    static boolean check(info io1, info io2){ // io2가 io1에 포함된다면 -> ture // 아니면 false
        if(io1.x <= io2.x && io2.y <= io1.y) return true;

        return false;
    }
}