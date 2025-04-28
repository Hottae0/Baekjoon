import java.io.*;
import java.util.*;

public class Main {

    static int stair[];
    static int memorial[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        stair = new int[num];
        memorial = new int[num];

        for(int i = 0; i < num ; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        memorial[0] = stair[0];
        if(num > 1) memorial[1] = stair[0] + stair[1];
        if(num > 2) memorial[2] = Math.max(stair[0], stair[1]) + stair[2];

        bw.write(memorial(num - 1) + "");

        bw.flush();
        bw.close();
    }

    static int memorial(int idx){
        if(memorial[idx] == 0){
            return memorial[idx] = Math.max(memorial(idx - 2), memorial(idx - 3) + stair[idx - 1]) + stair[idx];
        }

        return memorial[idx];
    }

}