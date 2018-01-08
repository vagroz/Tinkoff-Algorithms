import java.io.*;
import java.util.*;

public class TaskH {

    public static void main(String[] args) throws IOException {
        String fileName = "floyd";
        Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(fileName + ".in")));
        int N = sc.nextInt();
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        int[][] dp = graph.clone();

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }



        FileWriter fw = new FileWriter(new File(fileName + ".out"));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fw.write(dp[i][j]+" ");
            }
            fw.write("\n");
        }
        fw.close();
    }
}

