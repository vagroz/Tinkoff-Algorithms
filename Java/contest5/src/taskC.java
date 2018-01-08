import java.io.*;
import java.util.*;

public class taskC {
    static Vector<Integer>[] graph;
    static int distantions[];
    static int ways[];
    static void bfs(int t){
        LinkedList<Integer> bfsQ = new LinkedList<Integer>();
        bfsQ.add(t);
        distantions[t]=0;
        while (!bfsQ.isEmpty()){
            int x = bfsQ.remove();
            //System.out.println(x);
            int d = distantions[x]+1;
            for (int j : graph[x]){
                if (distantions[j] == -1 || distantions[j] > d) {
                    distantions[j] = d;
                    ways[j] = x;
                    bfsQ.add(j);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("mindist2.in")));
        String[] inp = br.readLine().split(" ");
        int N = Integer.parseInt(inp[0]);
        int M = Integer.parseInt(inp[1]);
        graph = new Vector[N];
        inp = br.readLine().split(" ");
        int x = Integer.parseInt(inp[0])-1;
        int y = Integer.parseInt(inp[1])-1;
        for (int i = 0; i < N; i++)
            graph[i] = new Vector<Integer>();

        distantions = new int[N];
        ways = new int[N];
        Arrays.fill(distantions, -1);
        Arrays.fill(ways, -1);

        for (int i = 0; i<M; i++) {
            inp = br.readLine().split(" ");
            int a = Integer.parseInt(inp[0]) - 1;
            int b = Integer.parseInt(inp[1]) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        bfs(x);

        FileWriter fw = new FileWriter(new File("mindist2.out"));
        fw.write(distantions[y] + "\n");
        if (distantions[y] != -1) {
            Stack<Integer> outWay = new Stack<Integer>();
            outWay.push(y+1);
            while (y != x) {
                outWay.push(ways[y]+1);
                y = ways[y];
            }
            while (!outWay.isEmpty()){
                fw.write(outWay.pop()+" ");
            }
        }
        fw.close();

    }
}
