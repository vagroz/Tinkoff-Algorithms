import java.io.*;
import java.util.*;

import java.math.BigInteger;

public class TaskJ {
    final static int NO_EDGE = -1;
    final static int NO_EDGE_INP = 100000;
    final static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        String fileName = "gasstation";
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName + ".in")));
        String[] inp = br.readLine().split(" ");
        int N = inp.length;
        Vertice[] cities = new Vertice[N+1];
        for (int i = 1; i <= N; i++) {
            int price = Integer.parseInt(inp[i-1]);
            cities[i] = new Vertice(i, price);
        }
        while(true){
            try{
                String[] mas = br.readLine().split(" ");
                int x = Integer.parseInt(mas[0]);
                int y = Integer.parseInt(mas[1]);
                cities[x].neighbors.add(cities[y]);
                cities[y].neighbors.add(cities[x]);
            }catch(Exception e){
                break;
            }
        }


        //в distance храним минимальную стоимость бензина,
        //за которую можем оказаться в этом городе с пустым баком
        TreeSet<Vertice> verticeSet = new TreeSet<Vertice>();
        cities[1].distance = 0;
        verticeSet.add(cities[1]);

        //dijkstra
        while(!verticeSet.isEmpty()){
            Vertice v = verticeSet.pollFirst();
            int price = v.getPrice();
            for (Vertice w: v.neighbors){
                //путь1: в v заправили только бак
                if (v.distance + price < w.distance) {
                    w.distance = v.distance + price;
                    verticeSet.add(w);
                }

                //путь2: в v заправили бак и канистру
                for(Vertice w2: w.neighbors){
                    if (w2.distance > v.distance + 2*price){
                        w2.distance = v.distance + 2*price;
                        verticeSet.add(w2);
                    }
                }
            }
        }



        FileWriter fw = new FileWriter(new File(fileName + ".out"));
        if (cities[N].distance == Long.MAX_VALUE)
            fw.write("-1\n");
        else
            fw.write(cities[N].distance + "\n");
        fw.close();
    }
}


class Vertice implements Comparable<Vertice> {
    Vertice(int number, int price) {
        v = number;
        this.price = price;
        distance = Long.MAX_VALUE;
        neighbors = new Vector<Vertice>();
    }

    private int v;
    private int price;
    long distance;
    Vector<Vertice> neighbors;

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
    int getPrice() {return price;}
}

