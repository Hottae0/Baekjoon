import java.io.*;
import java.util.*;




public class Main {
    static char arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder ans = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        String S = br.readLine();

        if(S.length() == 1){
            bw.write(S);
        }else{
            arr = S.toCharArray();

            int start = 0;
            ArrayList<Integer> end = new ArrayList<>();

            for(int i = 0; i < N; i++){
                start = i;
                char max = arr[i];

                for(int j = i + 1; j < N; j++){
                    if(max <= arr[j]){
                        if(max < arr[j]) {
                            end.clear();
                            max = arr[j];
                        }

                        end.add(j);
                    }
                }

                if(max != arr[i]) break;
                ans.append(max);
            }

            ArrayList<String> tmp = new ArrayList<>();

            for(int i = 0 ; i < end.size(); i++){
                StringBuilder sb = new StringBuilder();

                sb.append(S.substring(start, end.get(i) + 1));
                sb.reverse();

                tmp.add(sb + S.substring(end.get(i) + 1) );
            }

            Collections.sort(tmp, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o2.compareTo(o1);
                }
            });

            if(tmp.size() > 0) ans.append(tmp.get(0));

            bw.write(ans.toString());
        }

        bw.flush();
        bw.close();
    }
}