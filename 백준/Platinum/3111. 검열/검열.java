import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String A = br.readLine();
        int cnt = A.length();

        String T = br.readLine(); // 긴 string

        char[] patF = A.toCharArray();
        char[] patR = new StringBuilder(A).reverse().toString().toCharArray();

        Deque<Character> input = new ArrayDeque<>();
        for (char c : T.toCharArray()) {
            input.addLast(c);
        }

        Deque<Character> left  = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();
        boolean forward = true;

        while (!input.isEmpty()) {
            if (forward) {
                char c = input.pollFirst();
                left.addLast(c);
                if (endsWith(left, patF)) {
                    // 꼬리에서 패턴 삭제
                    for (int i = 0; i < cnt; i++) left.removeLast();
                    forward = false;
                }
            } else {
                char c = input.pollLast();
                right.addLast(c);
                if (endsWith(right, patR)) {
                    for (int i = 0; i < cnt; i++) right.removeLast();
                    forward = true;
                }
            }
        }

        while (!left.isEmpty()) {
            char c = left.removeLast();
            right.addLast(c);
            if (endsWith(right, patR)) {
                for (int i = 0; i < cnt; i++) right.removeLast();
            }
        }

        // 3) right에 역순으로 쌓인 결과를 꺼내 뒤집어 출력
        StringBuilder sb = new StringBuilder(right.size());
        while (!right.isEmpty()) {
            sb.append(right.removeLast());
        }
        bw.write(sb.toString());
        
        bw.flush();
        bw.close();
    }

    static boolean endsWith(Deque<Character> dq, char[] pat) {
        if (dq.size() < pat.length) return false;
        Iterator<Character> it = dq.descendingIterator();
        for (int i = pat.length - 1; i >= 0; i--) {
            if (it.next() != pat[i]) return false;
        }
        return true;
    }
}