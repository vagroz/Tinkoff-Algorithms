import java.io.*;
import java.util.*;

public class TaskG {

    public static void main(String[] args) throws IOException {
        String fileName = "distance";
        Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(fileName + ".in")));
        int N = sc.nextInt();
        int M = sc.nextInt();
        Vertice[] vertices = new Vertice[N+1];
        int S = sc.nextInt();
        int F = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            vertices[i] = new Vertice(i);
        }
        vertices[S].distance = 0;
        TreeSet<Vertice> verticesSet = new TreeSet<Vertice>();
        verticesSet.add(vertices[S]);


        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int w = sc.nextInt();
            vertices[x].edges.add(new OutEdge(vertices[y], w));
            vertices[y].edges.add(new OutEdge(vertices[x], w));
        }


        while (!verticesSet.isEmpty()) {
            Vertice v = verticesSet.pollFirst();
//            System.out.println("Cur: " + (v.getNumber()+1) + " d=" + v.distance);
            long currentD = v.distance;
            int i = v.getNumber();
            for (OutEdge edge: v.edges) {

                    long oldD = edge.getNeighbor().distance;
                    if (oldD > currentD + edge.getWeight()) {
                        edge.getNeighbor().distance = currentD + edge.getWeight();
                        edge.getNeighbor().lastVertice = v;
//                        Vertice vToAdd = vertices[j];
//                        for (Vertice t: verticesSet)
//                            if (vToAdd==null ? t==null : vToAdd.equals(t))
//                                System.out.println(" >--- "+ t.getNumber() + " = " + vertices[j].getNumber());
//                        System.out.println(verticesSet.add(vToAdd));
                        verticesSet.add(edge.getNeighbor());
                    }

            }
//            for (Vertice t: verticesSet) {
//                System.out.print((t.getNumber()+1) + " -d=" + t.distance + "; ");
//            }
//            System.out.println();
        }


        FileWriter fw = new FileWriter(new File(fileName + ".out"));
        fw.write ((vertices[F].distance == Long.MAX_VALUE ? -1 : vertices[F].distance) + "\n");
        if (vertices[F].distance != Long.MAX_VALUE){
            Stack<Vertice> result = new Stack<Vertice>();
            Vertice v = vertices[F];
            while(v != null){
                result.push(v);
                v = v.lastVertice;
            }
            while(!result.isEmpty())
                fw.write(result.pop().getNumber() + " ");
        }
        fw.close();
    }
}

class OutEdge {
    OutEdge(Vertice v, int w){
        this.v = v;
        weight = w;
    }

    private Vertice v;
    private int weight;

    int getWeight() {return weight;}
    Vertice getNeighbor() {return v;}
}

class Vertice implements Comparable<Vertice> {
    Vertice(int number) {
        v = number;
        distance = Long.MAX_VALUE;
        edges = new Vector<OutEdge>();
    }

    private int v;
    long distance;
    Vector<OutEdge> edges;
    Vertice lastVertice;

    @Override
    public int compareTo(Vertice o) {
        if (this.distance - o.distance < 0)
            return -1;
        else if (this.distance == o.distance)
            return this.v - o.v;
        else
            return 1;
    }

    int getNumber() {
        return v;
    }
}
