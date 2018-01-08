import java.io.*;
import java.util.*;

public class taskB {
    static boolean[][] graph;
    static int distantions[];
    static void bfs(int t){
        LinkedList<Integer> bfsQ = new LinkedList<Integer>();
        bfsQ.add(t);
        distantions[t]=0;
        while (!bfsQ.isEmpty()){
            int x = bfsQ.remove();
            System.out.println(x);
            int d = distantions[x]+1;
            for (int j = 0; j < graph[x].length; j++){
                if(graph[x][j]){
                    if (distantions[j] == -1 || distantions[j] > d) {
                        distantions[j] = d;
                        bfsQ.add(j);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("mindist.in")));
        String[] inp = br.readLine().split(" ");
        int N = Integer.parseInt(inp[0]);
        int x = Integer.parseInt(inp[1])-1;
        graph = new boolean[N][N];

        distantions = new int[N];
        Arrays.fill(distantions, -1);

        for (int i = 0; i<N; i++) {
            String[] mas = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = mas[j].equals("1");
            }
        }

        bfs(x);

        FileWriter fw = new FileWriter(new File("mindist.out"));
        for (int y: distantions)
            fw.write(y+" ");

        fw.close();

    }
}
