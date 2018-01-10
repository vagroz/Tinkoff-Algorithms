import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;
import java.io.*;
import java.math.BigInteger;
import java.time.Clock;
import java.util.TreeSet;

public class TaskB {
    static String fileName = "company";

    public static void main(String[] args)throws IOException {
        FileInputStream file = new FileInputStream(fileName + ".in");
        BufferedReader br = new BufferedReader(new InputStreamReader(file));

        TreeSet<Operation> set = new TreeSet<Operation>();
        int N = Integer.parseInt(br.readLine());
        int orderTime = 0;
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++){
                String inp[] = br.readLine().split(" ");
                set.add(new Operation(inp[0], Integer.parseInt(inp[1]), orderTime));
                orderTime++;
            }
        }
        int balans = 0;
        int min = 0;
        for (Operation t: set){
            balans += t.money;
            if (balans < min)
                min = balans;
        }

        int res = 0 - min;
        FileWriter fw = new FileWriter(fileName + ".out");
        fw.write(res + "\n");
        fw.close();

    }
}

class Operation implements Comparable<Operation>{
    LocalTime time;
    int money;
    int order;

    Operation(String stringTime, int money, int order){
        String[] mas = stringTime.split(":");
        int h = Integer.parseInt(mas[0]);
        int m = Integer.parseInt(mas[1]);
        int s = Integer.parseInt(mas[2]);
        time = LocalTime.of(h, m, s);
        this.money = money;
        this.order = order;
    }

    @Override
    public int compareTo(Operation o) {
        int v = time.compareTo(o.time);
        if (v == 0){
            if (this.money > o.money)
                return 1;
            else if (this.money < o.money)
                return  -1;
            else{
                if (this.order > o.order)
                    return 1;
                else
                    return -1;
            }
        } else {
            return v;
        }
    }


}
