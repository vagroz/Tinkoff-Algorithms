import java.io.*;
import java.util.*;

public class TaskF {

    public static void main(String[] args) throws IOException {
        String fileName = "dijkstra";
        Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(fileName + ".in")));
        int N = sc.nextInt();
        Vertice[] vertices = new Vertice[N];
        int S = sc.nextInt() - 1;
        int F = sc.nextInt() - 1;
        for (int i = 0; i < N; i++) {
            vertices[i] = new Vertice(i);
        }
        vertices[S].distance = 0;
        TreeSet<Vertice> verticesSet = new TreeSet<Vertice>();
        verticesSet.add(vertices[S]);

        int[][] graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }


        while (!verticesSet.isEmpty()) {
            Vertice v = verticesSet.pollFirst();
//            System.out.println("Cur: " + (v.getNumber()+1) + " d=" + v.distance);
            long currentD = v.distance;
            int i = v.getNumber();
            for (int j = 0; j < N; j++) {
                if (j != i && graph[i][j] != -1) {
                    long oldD = vertices[j].distance;
                    if (oldD > currentD + graph[i][j]) {
                        vertices[j].distance = currentD + graph[i][j];
//                        Vertice vToAdd = vertices[j];
//                        for (Vertice t: verticesSet)
//                            if (vToAdd==null ? t==null : vToAdd.equals(t))
//                                System.out.println(" >--- "+ t.getNumber() + " = " + vertices[j].getNumber());
//                        System.out.println(verticesSet.add(vToAdd));
                        verticesSet.add(vertices[j]);
                    }
                }
            }
//            for (Vertice t: verticesSet) {
//                System.out.print((t.getNumber()+1) + " -d=" + t.distance + "; ");
//            }
//            System.out.println();
        }


        FileWriter fw = new FileWriter(new File(fileName + ".out"));
        fw.write ((vertices[F].distance == Long.MAX_VALUE ? -1 : vertices[F].distance) + " ");
        fw.close();
    }
}

class Vertice implements Comparable<Vertice> {
    Vertice(int number) {
        v = number;
        distance = Long.MAX_VALUE;
    }

    private int v;
    long distance;

    @Override
    public int compareTo(Vertice o) {
        if (this.distance - o.distance < 0)
            return -1;
        else if (this.distance == o.distance)
            return this.v - o.v;
        else
            return 1;
    }

//    public boolean equals(Vertice o){
//        return this.v == o.v;
//    }

    int getNumber() {
        return v;
    }
}
