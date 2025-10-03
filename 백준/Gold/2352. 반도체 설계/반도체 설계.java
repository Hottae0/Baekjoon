import java.io.*;
import java.util.*;

public class Main {
    static int arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());


        ArrayList<Integer> binary_search = new ArrayList<>();
        binary_search.add(Integer.MIN_VALUE);

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N; i++){
            
//            for(int a : binary_search){
//                bw.write(a + " ");
//            }bw.write("\n");

            int tmp = Integer.parseInt(st.nextToken());

            int start = 0;
            int end = binary_search.size() - 1;

            if(binary_search.get(end) < tmp){
                binary_search.add(tmp);
                continue;
            }

            while(start < end){
                int mid = (start + end) / 2;

                if(tmp > binary_search.get(mid)){
                    start = mid + 1;
                }else{
                    end = mid;
                }

            }
            binary_search.set(end, tmp);

        }


        bw.write(binary_search.size() - 1 + "");



        bw.flush();
        bw.close();
    }
}