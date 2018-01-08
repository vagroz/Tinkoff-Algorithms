import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class TaskB {
    static Vector<Integer>[] graph;
    static int[] labelsIn, labelsOut;
    static boolean isTree = true;
    static int currentTime = 1;
    static void dfs(int v) {
        labelsIn[v] = currentTime++;
        for (int j: graph[v]){
            if (labelsIn[j] == 0){
                dfs(j);
            }else if (labelsOut[j] != 0) {
                isTree = false;
            }
        }
        labelsOut[v] = currentTime++;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "tree";
        Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(fileName + ".in")));
        int N = sc.nextInt();
        labelsIn = new int[N];
        labelsOut = new int[N];
        graph = new Vector[N];
        for (int i = 0; i < N; i++)
            graph[i] = new Vector<Integer>();
        for (int i = 0; i < N; i++) {
            String[] inp;
            do {
                inp = sc.nextLine().split(" ");
            } while (inp.length < N);
            for (int j = 0; j < i; j++) {
                if (inp[j].equals("1")){
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        dfs(0);

        if (isTree)
            for (int i = 0; i < N; i++){
                if (labelsIn[i] == 0){
                    isTree = false;
                    break;
                }
            }

        FileWriter fw = new FileWriter(new File(fileName + ".out"));
        if (isTree)
            fw.write("YES");
        else
            fw.write("NO");
        fw.close();
    }
}
