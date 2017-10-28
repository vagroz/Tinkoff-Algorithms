import java.io.*;
import java.util.Vector;

public class taskA {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("reverse.in")));
        int N = Integer.parseInt(br.readLine());
        Vector<Integer>[] graph = new Vector[N];
        for (int i = 0; i < N; i++){
            graph[i] = new Vector<Integer>();
        }

        for (int i = 1; i <= N; i++) {
            String[] mas = br.readLine().split(" ");
            for (int j = 0; j < mas.length; j++) {
                if (mas[j].equals("")) break;
                int a = i;
                int b = Integer.parseInt(mas[j]);
                graph[b-1].add(a);
            }
        }


        FileWriter fw = new FileWriter(new File("reverse.out"));
        fw.write(N + "\n");
        for (int i = 1; i<=N; i++){
            for (Integer j : graph[i - 1]) {
                fw.write(j.toString()+" ");
            }
            fw.write("\n");
        }
        fw.close();
    }
}
