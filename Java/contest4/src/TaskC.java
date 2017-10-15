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

        int[][] dpOld = new int[nmk[1]+1][nmk[2]+1];
        int[][] dpNext = new int[nmk[1]+1][nmk[2]+1];

        for (int i = 1; i <= nmk[0]; i++) {
            dpNext = new int[nmk[1]+1][nmk[2]+1];
            for (int j = 1; j <= nmk[1]; j++) {
                for (int k = 1; k <= nmk[2]; k++) {
                    if (data1[i-1] == data2[j-1] && data2[j-1] == data3[k-1])

                    {
                        dpNext[j][k] = dpOld[j-1][k-1] + 1;
                    }
                    else {
                        int a = dpOld[j][k];
                        int b = dpNext[j-1][k];
                        int c = dpNext[j][k-1];
                        dpNext[j][k] = Math.max(a, Math.max(b, c));
                    }
                }
            }
            dpOld = dpNext;
        }
//        System.out.println(dp[nmk[0]][nmk[1]][nmk[2]]);
        FileWriter fw = new FileWriter("cinema.out");
        fw.write(dpNext[nmk[1]][nmk[2]]+"\n");
        fw.close();
    }

}
