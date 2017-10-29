import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class taskE {
    static Vector<Integer>[] graph;
    static boolean detailIsDone[];
    static BigInteger weights[];

    static void dfs(int t){
        detailIsDone[t] = true;
        for (int next: graph[t]){
            if(!detailIsDone[next])
                dfs(next);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("details.in")));
        String[] inp = br.readLine().split(" ");
        int N = inp.length;
        graph = new Vector[N];
        weights = new BigInteger[N];
        for (int i = 0; i < N; i++) {
            weights[i] = new BigInteger(inp[i]);
        }
        for (int i = 0; i < N; i++)
            graph[i] = new Vector<Integer>();

        detailIsDone = new boolean[N];

        for (int i = 0; i < N; i++) {
            String in = br.readLine();
            if (in != null && !in.equals(""))
                for (String t: in.split(" "))
                    graph[i].add(Integer.parseInt(t) - 1);
        }

        dfs(0);
        //int last = 0;
        //int res = 0
        BigInteger res = BigInteger.ZERO;
        for(int i = 0; i < N; i++) {
            /*if (res < last)
                throw new ArithmeticException("Need a BigInteger!");*/
            if (detailIsDone[i]){
                res = res.add(weights[i]);
            }
        }



        FileWriter fw = new FileWriter(new File("details.out"));
        fw.write(res.toString());

        fw.close();

    }
}
