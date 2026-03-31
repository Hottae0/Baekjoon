import java.io.*;
import java.util.*;

class info implements Comparable<info>{
    int x;
    int y;

    info(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(info io){
        if(this.x == io.x){
            return io.y - y;
        }
        return x - io.x;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<info> list = new ArrayList<>();


        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            list.add(new info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) );
        }

        Collections.sort(list);

        info fir = list.get(0);

        int left = fir.x;
        int right = fir.y;

        int sum = 0;

       for(int i = 1; i < list.size(); i++){
            info io = list.get(i);

            if(io.y <= right){
                continue;
            }else if(right <= io.x){
                sum += right - left;

                left = io.x;
                right = io.y;
            }else if(io.x < right && right < io.y){
                right = io.y;
            }
        }

        sum += right - left;

        bw.write(sum + "");

        bw.flush();
        bw.close();
    }
}
