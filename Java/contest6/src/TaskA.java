import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class TaskA {
    static Vector<Integer>[] graph;
    static short[] labels;
    static boolean isBipartite = true;
    static void dfs(int v) {
        short nextLabel = (short) (labels[v] == 1 ? 2 : 1);
        if (isBipartite)
            for (int j : graph[v]) {
                if (labels[j] == 0) {
                    labels[j] = nextLabel;
                    dfs(j);
                } else if (labels[j] != nextLabel){
                    isBipartite = false;
                }
            }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new InputStreamReader(new FileInputStream("tree.in")));
        int N = sc.nextInt();
        int M = sc.nextInt();
        labels = new short[N+1];
        graph = new Vector[N+1];

        for (int i = 0; i <= N; i++)
            graph[i] = new Vector<Integer>();

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x].add(y);
            graph[y].add(x);
        }
        for (int i = 0; i <= N; i++){
            if(labels[i] == 0){
                labels[i] = 1;
                dfs(i);
            }
        }

        FileWriter fw = new FileWriter(new File("bipartite.out"));
        if (isBipartite)
            fw.write("YES");
        else
            fw.write("NO");
        fw.close();
    }
}
