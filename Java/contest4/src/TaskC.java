import java.io.*;

public class TaskC {
    static int[] getIntArray (String s){
        String[] mas = s.split(" ");
        int [] res = new int [mas.length];
        for (int i = 0; i < mas.length; i++) {
            res[i] = Integer.parseInt(mas[i]);
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("cinema.in")));
        int[] nmk = getIntArray(br.readLine());
        int[] data1 = getIntArray(br.readLine());
        int[] data2 = getIntArray(br.readLine());
        int[] data3 = getIntArray(br.readLine());

        int[][][] dp = new int[nmk[0]+1][nmk[1]+1][nmk[2]+1];

        for (int i = 1; i <= nmk[0]; i++) {
            for (int j = 1; j <= nmk[1]; j++) {
                for (int k = 1; k <= nmk[2]; k++) {
                    if (data1[i-1] == data2[j-1] && data2[j-1] == data3[k-1])
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    else {
                        int a = dp[i-1][j][k];
                        int b = dp[i][j-1][k];
                        int c = dp[i][j][k-1];

                        dp[i][j][k] = Math.max(a, Math.max(b, c));
                    }
                }
            }
        }
        System.out.println(dp[nmk[0]][nmk[1]][nmk[2]]);
        FileWriter fw = new FileWriter("cinema.out");
        fw.write(dp[nmk[0]][nmk[1]][nmk[2]]+"\n");
        fw.close();
    }

}
