import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class TaskE {
    static int[] dp;

    static int getDp(int i) {
        if (i < 1) return Integer.MIN_VALUE;
        else return dp[i];
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new InputStreamReader(new FileInputStream("atm.in")));
        int n = sc.nextInt();
        Vector<Integer> inputMas = new Vector<Integer>();
        for (int i = 0; i < n; i++) {
            inputMas.add(sc.nextInt());
        }
        int req = sc.nextInt();
        dp = new int[req+1];
        Arrays.fill(dp, Integer.MIN_VALUE);

        for (int i = 1; i <= req; i++) {
            if (inputMas.contains(i))
                dp[i] = 1;
            else {
                int min = Integer.MAX_VALUE;
                for (int elem: inputMas){
                    int candidateDp = getDp(i-elem) + 1;
                    if (candidateDp > 0 && candidateDp < min)
                        min = candidateDp;
                }
                dp[i] = (min == Integer.MAX_VALUE ? -1 : min);
            }
        }
        int res = dp[req];
        if (res <= 0) res = -1;
//        System.out.println(res);
//        System.out.println(Arrays.toString(dp));
        int currentSum = req;
        FileWriter fw = new FileWriter("atm.out");
        fw.write(res+"\n");
        try {
            int[] resultMas = new int[res];
            for (int i = 0; i < res-1; i++){
                for (int e: inputMas){
                    if (e < currentSum && dp[currentSum]-dp[currentSum-e]==1){
                        resultMas[i] = e;
                        currentSum -= e;
                        break;
                    }
                }
            }
            resultMas[res - 1] = currentSum;
            for(int e: resultMas)
                fw.write(e+" ");
        }catch(NegativeArraySizeException e){}

        fw.close();
    }
}
