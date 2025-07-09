import java.util.*;
import java.io.*;

class Node{
    HashMap<Character, Node> child;
    boolean end;

    Node(){
        this.child = new HashMap<>();
        this.end = false;
    }
}

class Trie{
    Node root;

    Trie(){
        this.root = new Node();
    }

    void insert(String S){
        Node nd = this.root;

        for(int i = 0 ; i < S.length() ; i++){
            char c = S.charAt(i);

            nd.child.putIfAbsent(c, new Node());
            nd = nd.child.get(c);
        }

        nd.end = true;
    }

    float search(String s){ // 여기가 잘 못 됐다
        Node nd = this.root;

        float cnt = 0;

        int idx = 0;

        while(idx < s.length()){
            cnt += 1;

            char c = s.charAt(idx++);
            nd = nd.child.get(c);

            while(idx < s.length() && nd.child.size() == 1 && !nd.end){
                nd = nd.child.get(s.charAt(idx++));
            }

        }

        return cnt;
    }
    

}


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String N;

        StringBuilder sb = new StringBuilder();

        while( (N = br.readLine()) != null ){
            int n = Integer.parseInt(N);

            //bw.write(n + " \n");

            Trie trie = new Trie();

            String arr[] = new String[n];

            for(int i = 0 ; i < n; i++){
                String S = br.readLine();

                arr[i] = S;
                trie.insert(S);
            }

            float sum = 0;

            for(int i = 0; i < n; i++){
                String S = arr[i];

                sum += trie.search(S);
//                sb.append((int)sum + " ");
            } //sb.append("\n");

            sum = sum / (float)n;

            sb.append(String.format("%.2f\n", sum));
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();

    }
}