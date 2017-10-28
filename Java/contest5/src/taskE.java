import java.io.*;
import java.util.*;

public class taskE {
    static Vector<Integer>[] graph;
    static boolean detailIsDone[];
    static int weights[];

    static int bfs(int t){
        LinkedList<Integer> bfsQ = new LinkedList<Integer>();
        bfsQ.add(t);
        int totalTime = 0;
        while (!bfsQ.isEmpty()){
            int x = bfsQ.remove();
            if (!detailIsDone[x]){
                detailIsDone[x] = true;
                totalTime += weights[x];
                for (int j: graph[x])
                    bfsQ.add(j);

            }
        }
        return totalTime;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("details.in")));
        String[] inp = br.readLine().split(" ");
        int N = inp.length;
        graph = new Vector[N];
        weights = new int[N];
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(inp[i]);
        }
        for (int i = 0; i < N; i++)
            graph[i] = new Vector<Integer>();

        detailIsDone = new boolean[N];

        for (int i = 0; i < N; i++) {
            inp = br.readLine().split(" ");
            if (!inp[0].equals(""))
                for (String t: inp)
                    graph[i].add(Integer.parseInt(t) - 1);
        }

        int res = bfs(0);

        FileWriter fw = new FileWriter(new File("details.out"));
        fw.write(res+"\n");

        fw.close();

    }
}
