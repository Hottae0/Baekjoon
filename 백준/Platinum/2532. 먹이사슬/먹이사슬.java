import java.io.*;
import java.util.*;

class info{
    int num;
    int L;
    int R;

    info(int num, int L, int R){
        this.num = num;
        this.L = L;
        this.R = R;
    }
}

public class Main {
    static int arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ArrayList<info> arr = new ArrayList<>();

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            arr.add(new info(n, l, r));
        }

        Collections.sort(arr, new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                if(o1.L == o2.L){
                    return o1.R - o2.R; // R은 내림차순.
                }

                return o2.L - o1.L; // L은 오름차순
            }
        });

        ArrayList<info> binary_search = new ArrayList<>();
        binary_search.add(new info(0, Integer.MAX_VALUE, 0));

        for(int i = 0 ; i < N; i++){

//            for(info a : binary_search){
//                bw.write(a.num + " " + a.L + " " + a.R + " // ");
//            }bw.write("\n");

            info tmp = arr.get(i);

            int start = 0;
            int end = binary_search.size() - 1;

            if(check(tmp, binary_search.get(end))){
                binary_search.add(tmp);
                continue;
            }

            while(start < end){
                int mid = (start + end) / 2;

                if(check(tmp, binary_search.get(mid))){
                    start = mid + 1;
                }else{
                    end = mid;
                }

            }
            binary_search.set(end, tmp);

        }

//        for(info a : binary_search){
//            bw.write(a.num + " " + a.L + " " + a.R + " // ");
//        }bw.write("\n");
        
        bw.write(binary_search.size() - 1 + "");



        bw.flush();
        bw.close();
    }

    static boolean check(info io1, info io2){ // io1 이 먹이 사슬 상위임? 을 물어보고 있음
        if(io1.L < io2.L && io1.R >= io2.R) return true;
        if(io1.L == io2.L && io1.R > io2.R) return true;

        return false;
    }
}