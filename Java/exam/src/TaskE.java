import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;
import java.io.*;
import java.math.BigInteger;
import java.time.Clock;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

public class TaskE {
    static String fileName = "hemo";

    public static void main(String[] args)throws IOException {
        FileInputStream file = new FileInputStream(fileName + ".in");
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        FileWriter fw = new FileWriter(fileName + ".out");
        Stack<Integer> stack = new Stack<Integer>();
        Vector<Long> sums = new Vector<Long>();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++){
            String s = br.readLine();
            if (s.charAt(0) == '+'){
                int hem = Integer.parseInt(s);
                stack.push(hem);
                if (sums.isEmpty())
                    sums.add((long)hem);
                else
                    sums.add(sums.lastElement()+hem);
            } else if (s.charAt(0) == '-') {
                int hem = stack.pop();
                fw.write(hem + "\n");
                sums.remove(sums.size()-1);
            } else {
                int k = Integer.parseInt(s.substring(1));
                int ind = sums.size() - k - 1;
                long res = sums.lastElement();
                if (ind >= 0)
                    res -= sums.get(ind);
                fw.write(res + "\n");
            }
        }

        fw.close();

    }
}

