import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class TaskD {
    static Vector<Integer>[] graph;
    static int[] labels;

    static int currentComponent = 1;
    static Vector<Set<Integer>> components = new Vector<Set<Integer>>();

    static boolean isBipartite = true;
    static void dfs(int v) {
        labels[v] = currentComponent;
        components.lastElement().add(v);
        for (int j: graph[v]) {
            if (labels[j]==0)
                dfs(j);
        }
    }
    public static void main(String[] args) throws IOException {
        String fileName = "matrix2";
        Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(fileName + ".in")));
        int N = sc.nextInt();
        int M = sc.nextInt();
        labels = new int[N+1];
        graph = new Vector[N+1];

        for (int i = 0; i <= N; i++)
            graph[i] = new Vector<Integer>();

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x].add(y);
            graph[y].add(x);
        }
        for (int i = 1; i <= N; i++){
            if(labels[i] == 0){
                components.add(new HashSet<Integer>());
                dfs(i);
                currentComponent++;
            }
        }

        FileWriter fw = new FileWriter(new File(fileName + ".out"));
        fw.write(components.size()+"\n");
        for (Set<Integer> componenta: components) {
            fw.write(componenta.size()+"\n");
            for (int v: componenta)
                fw.write(v + " ");
            fw.write("\n");
        }
        fw.close();
    }
}
