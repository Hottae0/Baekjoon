import java.util.*;
import java.io.*;

public class Main {
    static long arr[];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int end = Integer.parseInt(br.readLine());

        arr = new long[10]; // 0 ~ 9 의 숫자 + 총합

        // 어떤 숫자 A의 일의 자리가 0 && B의 일의 자리가 9일 때 / A에서 B까지 0 ~ 9의 숫자가 등장하는 횟수 = (B/10 - A/10 + 1) * (원래 숫자에서의 자릿수)다.
        // -> 와 이걸 어떻게 아냐....

        int start = 1;
        int digit = 1;

        while(start <= end){

            while(start % 10 != 0 && start <= end){
                count(start++, digit);
            }

            while (end % 10 != 9 && start <= end){
                count(end-- , digit);
            }

            if(start > end) break; // 내리다가 멈출 수 있음.

            start /= 10;
            end /= 10;

            long cnt = (end - start + 1) * digit;

            for(int i = 0 ; i < arr.length; i++){
                arr[i] += cnt;
            }

            digit *= 10;
        }

        for(long i : arr){
            bw.write(i + " ");
        }

        bw.flush();
        bw.close();
    }


    static void count(int num, int digit){
        while (num > 0){
            arr[num % 10] += digit;
            num /= 10;
        }
    }


}