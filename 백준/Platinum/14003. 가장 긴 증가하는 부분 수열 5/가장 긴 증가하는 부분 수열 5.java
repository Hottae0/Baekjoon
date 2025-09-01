import java.io.*;
import java.util.*;


public class Main {
    static int N;

    static int arr[];
    static int index[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        index = new int[N];
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i  < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.MIN_VALUE);

        for(int i = 0 ; i < N; i++){

            int tmp = arr[i];

            int start = 0;
            int end = list.size() - 1;

            if(tmp > list.get(end)){ // 가장 큰 숫자가 들어옴.
                list.add(tmp);
                index[i] = list.size() - 1;

            }else{ // 아니라면 이분 탐색을 통해 넣을 자리를 찾아야함
                while(start < end){

                    int mid = (start + end) / 2;

                    if(list.get(mid) < tmp){
                        start = mid + 1;
                    }else{
                        end = mid;
                    }

                }

                list.set(end, tmp);
                index[i] = end;
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size() - 1 + "\n");

        int cnt = list.size() - 1;
        Stack<Integer> stack = new Stack<>();

        for(int i = N - 1 ; i >= 0; i--){
            if(index[i] == cnt){
                stack.add(arr[i]);
                cnt -= 1;
            }
        }

        while (stack.size() > 0){
            sb.append(stack.pop() + " ");
        }

        bw.write(sb.toString().trim());

        // 확인용
//        for(int i = 0; i < N; i++){
//            bw.write(index[i] + " ");
//        }

        bw.flush();
        bw.close();
    }
}