import java.io.*;

public class TaskB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] masStr = br.readLine().split(" ");
        int[] mas = new int[masStr.length];
        for (int i = 0; i < masStr.length; i++) {
            mas[i] = Integer.parseInt(masStr[i]);
        }

        int[] dp = new int[mas.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if ((mas[i] % mas[j] == 0) && (dp[j] + 1 > dp[i])) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int max = 0;
        for (int e: dp)
            if (e > max) max = e;
        System.out.println(max);
    }
}
