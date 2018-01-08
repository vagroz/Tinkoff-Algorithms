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


        //в emptyDist храним минимальную стоимость бензина,
        //за которую можем оказаться в этом городе с пустыми баком и канистрой
        //в fullDist - с одним запасом
        TreeSet<Vertice> verticeSet = new TreeSet<Vertice>();
        cities[1].emptyCanisterDist = 0;
        cities[1].fullCanisterDist = cities[1].getPrice();
        verticeSet.add(cities[1]);

        //dijkstra
        while(!verticeSet.isEmpty()){
            Vertice v = verticeSet.pollFirst();
            int price = v.getPrice();
            for (Vertice w: v.neighbors){
                //1) едем оптимально к соседу с полным баком и канистрой
                long priceFull = Math.min(v.fullCanisterDist + price, v.emptyCanisterDist + 2*price);
                if(w.fullCanisterDist > priceFull){
                    w.fullCanisterDist = priceFull;
                    verticeSet.add(w);
                }
                //2) едем оптимально к соседу с пустой канистрой
                long priceEmpty = Math.min(v.emptyCanisterDist + price, v.fullCanisterDist);
                if(w.emptyCanisterDist > priceEmpty){
                    w.emptyCanisterDist = priceEmpty;
                    verticeSet.add(w);
                }
            }
        }



        FileWriter fw = new FileWriter(new File(fileName + ".out"));
        if (cities[N].emptyCanisterDist == Long.MAX_VALUE)
            fw.write("-1\n");
        else
            fw.write(cities[N].emptyCanisterDist + "\n");
        fw.close();
    }
}


class Vertice implements Comparable<Vertice> {
    Vertice(int number, int price) {
        v = number;
        this.price = price;
        emptyCanisterDist = Long.MAX_VALUE;
        fullCanisterDist = Long.MAX_VALUE;
        neighbors = new Vector<Vertice>();
    }

    private int v;
    private int price;
    long emptyCanisterDist, fullCanisterDist; //цена добраться в этот город с пустой/полной канистрой
    Vector<Vertice> neighbors;

    @Override
    public int compareTo(Vertice o) {
        long thisDist = Math.min(this.emptyCanisterDist, this.fullCanisterDist);
        long oDist = Math.min(o.emptyCanisterDist, o.fullCanisterDist);
        if (thisDist - oDist < 0)
            return -1;
        else if (thisDist == oDist)
            return this.v - o.v;
        else
            return 1;
    }

    int getNumber() {
        return v;
    }
    int getPrice() {return price;}
}

