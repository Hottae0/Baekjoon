import java.io.*;
import java.util.*;

class info{
    int x1;
    int y1;

    int x2;
    int y2;

    info(int a, int b, int c, int d){
        this.x1 = a;
        this.y1 = b;

        this.x2 = c;
        this.y2 = d;
    }

}

public class Main {
    static int N;
    static int parent[];

    static info line[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        parent = new int[N];
        for(int i = 1; i < N; i++){
            parent[i] = i;
        }

        line = new info[N];


        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            if(x1 < x2){
                line[i] = new info(x1, y1, x2, y2);
            }else if(x1 == x2){
                if(y1 < y2){
                    line[i] = new info(x1, y1, x2, y2);
                }else{
                    line[i] = new info(x2, y2, x1, y1);
                }
            }else{
                line[i] = new info(x2, y2, x1, y1);
            }

        }

        for(int i = 0; i < N - 1; i++){
            info A = line[i];

            for(int j = i + 1; j < N; j++){
                info B = line[j];

                if(find(i) == find(j)) continue;

                int abc = ccw(A.x1, A.y1, A.x2, A.y2, B.x1, B.y1);
                int abd = ccw(A.x1, A.y1, A.x2, A.y2, B.x2, B.y2);
                int cda = ccw(B.x1, B.y1, B.x2, B.y2, A.x1, A.y1);
                int cdb = ccw(B.x1, B.y1, B.x2, B.y2, A.x2, A.y2);

                if(abc * abd <= 0 && cda * cdb <= 0){
                    if (abc * abd == 0 && cda * cdb == 0) {
                        if(!isOverlapping(A.x1, A.y1, A.x2, A.y2, B.x1, B.y1, B.x2, B.y2)){
                            continue;
                        }
                    }

                    union(i, j);
                }
            }

        }

        int cnt[] = new int[N + 1];
        int max = 0;
        int count = 0;


        for(int i = 0; i < N; i++){
            int num = find(i);

            if(cnt[num] == 0){
                count ++;
            }

            max = Math.max(max, ++cnt[num]);
        }

        bw.write(count + "\n" + max);

        bw.flush();
        bw.close();
    }

    static int ccw(int a, int b, int c ,int d, int e , int f){
        long D = (long)(c - a)*(f - b) - (long)(d - b)*(e - a);

        if(D > 0) return 1;
        else if(D == 0) return 0;
        return -1;
    }

    static boolean isOverlapping(int a, int b, int c ,int d, int e , int f, int g, int h){
        return isLessOrEqual(e, f, c, d) && isLessOrEqual(a, b, g, h);
    }

    static boolean isLessOrEqual(int x1, int y1, int x2, int y2) {
        if (x1 != x2) return x1 < x2;
        return y1 <= y2;
    }



    static boolean union(int a, int b){ // 집합의 대푯값을 작은걸로~
        int x = find(a);
        int y = find(b);

        if(x == y){
            return false;
        }

        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }

        return true;
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]);
    }


}