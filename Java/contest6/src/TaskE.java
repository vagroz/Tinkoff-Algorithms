import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class TaskE {
    static Vector<Integer>[] graph;
    static int[] labelsIn, labelsOut;

    static int currentTime = 1;
//    static Vector<Set<Integer>> components = new Vector<Set<Integer>>();
    static Stack<Integer> result = new Stack<>();
    static boolean isTopSort = true;
    static void dfs(int v) {
        labelsIn[v] = currentTime++;
        for (int j: graph[v]) {
            if (isTopSort && labelsIn[j]==0)
                dfs(j);
            else if (labelsOut[j] == 0) {
                isTopSort = false;
                break;
            }
        }
        result.push(v);
        labelsOut[v] = currentTime++;
    }
    public static void main(String[] args) throws IOException {
        String fileName = "topsort";
        Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(fileName + ".in")));
        int N = sc.nextInt();
        int M = sc.nextInt();
        labelsIn = new int[N+1];
        labelsOut = new int[N + 1];
        graph = new Vector[N+1];


        for (int i = 0; i <= N; i++)
            graph[i] = new Vector<Integer>();

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x].add(y);
//            graph[y].add(x);
        }
        for (int i = 1; i <= N; i++) {
            if (labelsIn[i] == 0)
                dfs(i);
        }

        FileWriter fw = new FileWriter(new File(fileName + ".out"));
        if (isTopSort){
            while (!result.isEmpty()) {
                fw.write(result.pop() + " ");
            }
        } else {
            fw.write("-1");
        }

        fw.close();
    }
}
