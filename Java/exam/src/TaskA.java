import java.util.Scanner;
import java.io.*;
import java.math.BigInteger;
public class TaskA {
    static String fileName = "holidays";
    static boolean isOk(BigInteger h, BigInteger N, BigInteger M, BigInteger w) {
        BigInteger weeks = N.divide (w.add(h));
        BigInteger additional = N.mod(w.add(h)).min(w);
        //System.out.println(N+" "+M+" "+" "+w+"\n"+weeks);
        if (weeks.multiply(w).add(additional).compareTo(M) >= 0)
            return true;
        else
            return false;
    }
    public static void main(String[] args)throws IOException {
        Scanner sc = new Scanner(new File(fileName + ".in"));
        BigInteger N = sc.nextBigInteger();
        BigInteger M = sc.nextBigInteger();
        BigInteger w = sc.nextBigInteger();


        BigInteger h_min = BigInteger.ZERO;
        BigInteger h_max = N.subtract(M).add(BigInteger.ONE);
        while (h_min.add(BigInteger.ONE).compareTo(h_max) == -1) {
            BigInteger h = h_min.add(h_max).divide(new BigInteger("2"));
            if (isOk(h, N, M, w)) {
                h_min = h;
            }else{
                h_max = h;
            }
        }

        BigInteger res = h_min;
        //System.out.println(isOk(4, N, M, w));

        FileWriter fw = new FileWriter(fileName + ".out");
        fw.write(res.toString()+"\n");
        fw.close();

    }
}
