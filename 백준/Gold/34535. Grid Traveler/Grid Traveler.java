import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int arr[] = new int[N * N];
        arr[0] = 1;
        int idx = 0;

        for(int i = 1 ; i < N * N; i++){
            if(i % 2 == 0){
                idx += 2;
                arr[idx] = i + 1;
            }else{
                if(idx - 1 >= 0 && arr[idx - 1] == 0){
                    idx--;
                    arr[idx] = i + 1;
                }else{
                    idx++;
                    arr[idx] = i + 1;
                }
            }

//            for(int a : arr){
//                bw.write(a + " ");
//            }bw.write("\n");

        }

        StringBuilder sb = new StringBuilder();

        sb.append("YES\n");

        for(int i = 0; i <  N; i++){
            for(int j = 0 ; j < N ;j++){
                if(i % 2 == 0) sb.append(arr[i * N + j]  + " ");
                else sb.append(arr[(i + 1) * N - 1 - j] + " ");
            }sb.append("\n");
        }

        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();

    }
}