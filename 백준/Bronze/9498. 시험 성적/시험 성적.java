import java.util.* ;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int A = Integer.parseInt(br.readLine());

        bw.write((A>=90)?"A": (A>=80)? "B": (A>=70)? "C": (A>=60)? "D": "F");

        bw.flush();
        bw.close();
    }

}