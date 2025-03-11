import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        sb.append((int)Math.pow(2,N) - 1 + "\n");

        Hanoi(N,1 ,2,3);
        System.out.println(sb);

    }


    static void Hanoi(int n, int start, int mid, int end){
        if(n == 1){
            sb.append(start + " " + end +"\n");
            return;
        }

        Hanoi(n-1, start, end, mid);

        sb.append(start + " " + end + "\n");

        Hanoi(n-1, mid, start, end);

    }
}