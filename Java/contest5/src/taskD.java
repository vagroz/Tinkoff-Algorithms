import java.io.*;
import java.util.*;

public class taskD {
    static int steps[];
    final static int   OVERFLOW = 10000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("crazycalc.in")));
        String[] inp = br.readLine().split(" ");
        int x = Integer.parseInt(inp[0]);
        int y = Integer.parseInt(inp[1]);

        Keys[] keys = new Keys[3];
        keys[0] = new Key1();
        keys[1] = new Key2();
        keys[2] = new Key3();

        steps = new int[OVERFLOW];
        Arrays.fill(steps, -1);
        steps[x] = 0;
        LinkedList<Integer> bfsQ = new LinkedList<Integer>();
        bfsQ.add(x);

        while (!bfsQ.isEmpty()) {
            int current = bfsQ.remove();
            int d = steps[current] + 1;
            for(Keys button: keys){
                int next = button.calc(current);
                if (next >= 0 && next < OVERFLOW
                    && (steps[next]==-1 || steps[next] > d)){
                    steps[next] = d;
                    bfsQ.add(next);
                }
            }
        }

        FileWriter fw = new FileWriter(new File("crazycalc.out"));
        fw.write(steps[y] + "\n");
        fw.close();

    }
}

abstract class Keys{
    abstract int calc(int x);
}

class Key1 extends Keys{
    @Override
    int calc(int x) {
        return x*3;
    }
}

class Key2 extends Keys{
    @Override
    int calc(int x){
        int sum = 0, y = x;
        while (y != 0){
            sum += y % 10;
            y /= 10;
        }
        return x + sum;
    }
}

class Key3 extends  Keys{
    @Override
    int calc (int x){
        return x - 2;
    }
}