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

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int T = Integer.parseInt(br.readLine());

        ArrayList<info> cat_pos = new ArrayList<>();
        for(int i = 0 ; i < T; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            cat_pos.add(new info(a, b));
        }

        Collections.sort(cat_pos, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                if(o1.x == o2.x){
                    return Integer.compare(o1.y, o2.y);
                }

                return Integer.compare(o1.x, o2.x);
            }
        });

        ArrayList<info> list = new ArrayList<>();
        list.add(new info(-1, -1));

        for(int i = 0 ; i < T; i++){
            info io = cat_pos.get(i);

            int x = io.x;
            int y = io.y;

            if(x < 0 || x > N || y < 0 || y > M) continue;

            int start = 0;
            int end = list.size() - 1;


            if(check(list.get(end), io)){
                list.add(io);
                continue;
            }

            int mid = 0;

            while(start < end){
                mid = (start + end) / 2;

                if( check(list.get(mid), io) ){
                    start = mid + 1;
                }else{
                    end = mid;
                }
            }

            list.set(end, io);
        }

//        for(info io : list){
//            bw.write(io.x + " " + io.y + "\n");
//        }

        bw.write(list.size() - 1 + "");

        bw.flush();
        bw.close();
    }



    static boolean check(info io1, info io2){
        if(io1.x <= io2.x && io1.y <= io2.y) return true;
        return false;
    }
}