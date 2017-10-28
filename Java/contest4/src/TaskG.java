import  java.io.*;

public class TaskG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("zerosum.in")));
        int n = Integer.parseInt(br.readLine());
        int data[] = new int[2*n];
        String[] input = br.readLine().split(" ");
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            data[i] = Integer.parseInt(input[i]);
            data[i+n] = -data[i];
            maxSum += data[i];
        }
        input = null;
//        System.out.println(2*n+1 + " "+2*maxSum+1);
        //boolean[][] dp = new boolean[2*n+1][2*maxSum+1];
        boolean[][] dp = new boolean[n+1][2*maxSum+1];
        dp[0][maxSum] = true;
        for (int i = 1; i<=n; i++) {
            for (int s = -maxSum; s <= maxSum; s++) {
                int lastS = s - data[i-1];
                dp[i][s+maxSum] = /*dp[i-1][s+maxSum] || */(lastS >= - maxSum && lastS <= maxSum) && dp[i - 1][lastS + maxSum];
                lastS = s + data[i-1];
                dp[i][s+maxSum] = dp[i][s+maxSum] || (lastS >= - maxSum && lastS <= maxSum) && dp[i - 1][lastS + maxSum];
            }
        }


//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        char [] res = new char[n];
        int sum = 0;
        for (int i = n-1; i>=0; i--) {

//            System.out.println(data[i] + " " + sum + ";");
//            for (int j = 0; j < dp[i].length; j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();

            if (dp[i][maxSum+sum - data[i]]){
                res[i] = '+';
                sum -= data[i];
            }else{
                res[i] = '-';
                sum += data[i];
            }
        }

//        System.out.println(new String(res));
        FileWriter fw = new FileWriter("zerosum.out");
        fw.write(new String(res));
        fw.close();
    }
}
