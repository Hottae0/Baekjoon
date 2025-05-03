import java.io.*;
import java.util.*;


public class Main {
    static boolean visit[];
    static int arr[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점 갯수
        int M = Integer.parseInt(st.nextToken()); // 간선 갯수

        arr = new int[N][N];

        for(int i = 0; i < M; i++){
            StringTokenizer info = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(info.nextToken()) - 1;
            int b = Integer.parseInt(info.nextToken()) - 1;

            arr[a][b] = 1;
            arr[b][a] = 1;

        }

        visit = new boolean[N];
        int cnt = 0;

        for(int i = 0 ; i < N; i++){
            if(!visit[i]){
                find(i);
                cnt++;
            }

        }

        System.out.println(cnt);


    }

    static void find(int j){
        visit[j] = true;
        for(int i = 0; i < visit.length; i++){
            if(arr[j][i] == 1 && !visit[i]){
                find(i);
            }

        }

    }
}