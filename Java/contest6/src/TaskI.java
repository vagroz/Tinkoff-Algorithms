import java.io.*;
import java.util.*;

import java.math.BigInteger;

public class TaskI {
    final static int NO_EDGE = -1;
    final static int NO_EDGE_INP = 100000;
    final static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        String fileName = "negcycle";
        Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(fileName + ".in")));
        int N = sc.nextInt();
        long[][] graph = new long[N][N];
        short [][] nextV = new short[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
                if (graph[i][j] != NO_EDGE_INP)
                    nextV[i][j] = (short)j;
                else {
                    nextV[i][j] = -1;
                    graph[i][j] = INF;
                }
            }
        }


        long[][] dp = graph;

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(dp[i][k] < INF && dp[k][j] < INF && dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                        nextV[i][j] = nextV[i][k];
                    }
                }
            }
        }

        boolean isCycle = false;
        short startV = -1;
        for (short i = 0; i < N; i++)
            if (dp[i][i] < 0) {
                isCycle = true;
                startV = i;
            }

        FileWriter fw = new FileWriter(new File(fileName + ".out"));
        if (!isCycle)
            fw.write("NO\n");
        else{
            fw.write("YES\n");
            Vector<Short> res = new Vector<Short>();
            res.add(startV);
            short i = nextV[startV][startV];
            while (i != startV){
                if (i == -1)
                    throw new SecurityException("Too long cycle!");
                if (res.contains(i)) {
                    startV = i;
                    break;
                }else{
                    res.add(i);
                }
                i = nextV[i][startV];
            }
            int start = res.indexOf(startV);
            fw.write((res.size()-start)+"\n");
            for (int j = start;  j < res.size(); j++)
                fw.write((res.get(j)+1)+" ");
        }
        fw.close();
    }
}

