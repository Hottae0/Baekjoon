import java.io.*;
import java.util.*;

public class Main {
    static int arr[][];
    static boolean check[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        check = new boolean[N][N];

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean matches = false;

        for(int k = 0 ; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(i == k || j == k || i == j) continue;
                    // 최단 거리일 때만 살아남게

                    if(arr[i][j] > arr[i][k] + arr[k][j]){
                        matches = true;
                    } // 최신화가 일어남 == 주어진 배열이 잘못됐다.
                    else if(arr[i][j] == arr[i][k] + arr[k][j]){
                        check[i][j] = true;
                    }

                }
            }
        }
        // 주어진 표가 잘못되어 불가능인 경우가 존재한다.
        // 이를 걸러내기가 안되네;;

        long sum = 0;

        for(int i = 0 ; i < N; i++){
            for(int j = i + 1 ; j < N; j++){
                if(!check[i][j]) sum += arr[i][j];
            }
        }

        if(!matches)bw.write(sum + "");
        else {
            bw.write("-1");
        }

        bw.flush();
        bw.close();
    }

}