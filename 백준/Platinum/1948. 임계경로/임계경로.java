import java.util.*;
import java.io.*;

class info{
    int end;
    int time;
    boolean visited;

    info(int end, int time){
        this.end = end;
        this.time = time;

        this.visited = false;
    }

}

public class Main {
    static int in_degree[];
    static ArrayList<ArrayList<info>> graph = new ArrayList<>();
    static ArrayList<ArrayList<info>> reverse_graph = new ArrayList<>();

    static int n;
    static int memorial[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine()); // 도시의 개수
        int m = Integer.parseInt(br.readLine()); // 도로의 개수

        in_degree = new int[n];
        memorial = new int[n];

        for(int i = 0 ; i < n; i++){
            graph.add(new ArrayList<>());
            reverse_graph.add(new ArrayList<>());
        }

        for(int i = 0 ; i < m ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());

            graph.get(start).add(new info(end, time));
            reverse_graph.get(end).add(new info(start, time));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        topological_sort();


//        for(int i : memorial){
//            bw.write(i + " ");
//        } bw.write("\n");


        int cnt = back_tracking(start, end);

        bw.write(memorial[end] + "\n" + cnt);

        bw.flush();
        bw.close();

    }

    static void topological_sort(){
        Queue<Integer> que = new LinkedList<>();

        for(int i = 0 ; i < n; i++){
            if(in_degree[i] == 0){
                que.add(i);
            }
        }

        while(!que.isEmpty()){
            int idx = que.poll();

            for(info io : graph.get(idx)){
                int i = io.end;
                int time = io.time;

                in_degree[i] -= 1;

                // 가장 길게 오는 경우
                memorial[i] = Math.max(memorial[i], memorial[idx] + time);

                if(in_degree[i] == 0){
                    que.add(i);
                }

            }

        }
    }

    static int back_tracking(int start, int end){
        Queue<Integer> que = new LinkedList<>();
        que.add(end);

        int cnt = 0;

        while (!que.isEmpty()){
            int idx = que.poll();

            if(idx == start){
                continue;
            }

            for(info arrival : reverse_graph.get(idx)){
                int i = arrival.end;
                int time = arrival.time;

                if(memorial[idx] == memorial[i] + time){

                    if(!arrival.visited){
                        cnt += 1;
                        que.add(i);
                        arrival.visited = true;
                    }
                }

            }

        }

        return cnt;
    }

}