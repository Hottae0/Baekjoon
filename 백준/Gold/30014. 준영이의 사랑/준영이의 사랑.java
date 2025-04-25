import java.io.*;
import java.lang.reflect.WildcardType;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for(int i = 0 ; i < N; i++){
            pq.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0 ; i < N; i++){

        }

        Deque<Integer> deque = new ArrayDeque<>();

        while (pq.size() > 0){
            deque.addFirst(pq.poll());
            if(!pq.isEmpty()){
                deque.addLast(pq.poll());
            }
        }

        StringBuilder sb = new StringBuilder();

        long sum = deque.getFirst() * deque.getLast();

        int last = deque.pollFirst();
        sb.append(last + " ");

        while(!deque.isEmpty()){
            sum += last * deque.getFirst();
            last = deque.pollFirst();
            sb.append(last + " ");
        }

        bw.write(sum + "\n");
        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
    }

}