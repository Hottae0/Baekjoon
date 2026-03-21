import java.io.*;
import java.net.Inet4Address;
import java.util.*;

class info implements Comparable<info>{
    int start;
    int end;

    info(int start, int end){
        this.start = start;
        this.end = end;
    }


    @Override
    public int compareTo(info o) {
        if(this.start == o.start){
            return o.end - this.end;
        }
        return this.start - o.start;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> que = new PriorityQueue<>();
        que.add(0);

        ArrayList<info> arr = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr.add(new info(start, end));
        }

        Collections.sort(arr);

        for(int i = 0 ; i < arr.size(); i++){
            info io = arr.get(i);

            int start = io.start;
            int end = io.end;

            int cur_end = que.peek();

            if(cur_end <= start){
                que.poll();
            }
            que.add(end);
        }
        
        bw.write(que.size() + "");

        bw.flush();
        bw.close();
    }
}