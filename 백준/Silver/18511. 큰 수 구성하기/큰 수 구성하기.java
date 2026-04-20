import java.io.*;
import java.util.*;

public class Main {
    static String N;
    static ArrayList<String> arr = new ArrayList<>();

    static String max = "0";
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = (st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K; i++){
            arr.add((st.nextToken()));
        }
        
        for(int i = 0 ; i < K; i++){
            make_num(1, arr.get(i));
        }

        bw.write(max + "");

        bw.flush();
        bw.close();
    }

    static void make_num(int dep, String ans){
        if(Integer.parseInt(ans) > Integer.parseInt(N)){
            return;
        }

        max = String.valueOf(Math.max(Integer.parseInt(max), Integer.parseInt(ans)));

        for(int i = 0 ; i < arr.size(); i++){
            make_num(dep + 1, ans + arr.get(i));
        }

    }
}
