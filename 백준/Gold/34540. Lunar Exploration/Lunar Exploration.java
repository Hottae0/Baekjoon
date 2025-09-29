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
    static int cnt[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ArrayList<info> drone_pos = new ArrayList<>();

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            drone_pos.add( new info(x, y) );
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x_goal = Integer.parseInt(st.nextToken());
        int y_goal = Integer.parseInt(st.nextToken());
        char forward_to = st.nextToken().charAt(0);


        long ans = 0;



        if(forward_to == 'E'){

            Collections.sort(drone_pos, new Comparator<info>() {
                @Override
                public int compare(info o1, info o2) {
                    return o1.x - o2.x;
                }
            });

            int idx = x_goal;

            for(int i = 0 ; i < N; i++){
                info io = drone_pos.get(i);

                int x = io.x;
                int y = io.y;

                ans += Math.abs(y_goal - y) + Math.abs(idx++ - x); // 여기는 무조건 이거 확정
            }


        }else if(forward_to == 'W'){

            Collections.sort(drone_pos, new Comparator<info>() {
                @Override
                public int compare(info o1, info o2) {
                    return o2.x - o1.x;
                }
            });

            int idx = x_goal;

            for(int i = 0 ; i < N; i++){
                info io = drone_pos.get(i);

                int x = io.x;
                int y = io.y;

                ans += Math.abs(y_goal - y) + Math.abs(idx-- - x); // 여기는 무조건 이거 확정

            }
        }else if(forward_to == 'N'){
            Collections.sort(drone_pos, new Comparator<info>() {
                @Override
                public int compare(info o1, info o2) {
                    return o1.y - o2.y;
                }
            });

            int idx = y_goal;
            for(int i = 0 ; i < N; i++){
                info io = drone_pos.get(i);

                int x = io.x;
                int y = io.y;

                ans += Math.abs(x_goal - x) + Math.abs(idx++ - y); // 여기는 무조건 이거 확정

            }
        }else {
            Collections.sort(drone_pos, new Comparator<info>() {
                @Override
                public int compare(info o1, info o2) {
                    return o2.y- o1.y;
                }
            });

            int idx = y_goal;

            for(int i = 0 ; i < N; i++){
                info io = drone_pos.get(i);

                int x = io.x;
                int y = io.y;

                ans += Math.abs(x - x_goal) + Math.abs(idx-- - y);
            }
        }

        bw.write(ans + "");

        bw.flush();
        bw.close();
    }

}