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

        int N = Integer.parseInt(br.readLine());

        ArrayList<info> pos = new ArrayList<>();

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int linked[] = new int[N + 1];
        for(int i = 1; i < N + 1; i++){
            linked[Integer.parseInt(st1.nextToken())] = i;
        }

        for(int i = 1 ; i < N + 1; i++){
            int n = Integer.parseInt(st2.nextToken());

            pos.add(new info(i, linked[n]));
        }

        ArrayList<info> list = new ArrayList<>();
        list.add(new info(-1, -1));

        for(int i = 0 ; i < N; i++){
            info io = pos.get(i);

            int x = io.x;
            int y = io.y;

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



        bw.write(list.size() - 1 + "");

        bw.flush();
        bw.close();
    }



    static boolean check(info io1, info io2){
        if(io1.x <= io2.x && io1.y <= io2.y) return true;
        return false;
    }
}