import java.io.*;

public class TaskD {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("lcstr.in")));
        String m1 = br.readLine();
        char[] message1 = m1.toCharArray();
        String m2 = br.readLine();
        char[] message2 = m2.toCharArray();
        short[] dpOld = new short[message1.length + 1];
        short[] dpNext = new short[message1.length + 1];

        short [] maxL = new short[2];

        for (short i = 1; i <= message2.length; i++) {
            for (short j = 1; j <= message1.length; j++) {
                if (message1[j-1] == message2[i-1]){
                    short res = (short) (dpOld[j-1] + 1);
                    if (res > maxL[0]){
                        maxL[0] = res;
                        maxL[1] = j;
                    }
                    dpNext[j] = res;
                }
            }
            dpOld = dpNext;
            dpNext = new short[message1.length + 1];
        }

        FileWriter fw = new FileWriter("lcstr.out");
        fw.write(m1.substring(maxL[1]-maxL[0], maxL[1]));
        fw.close();
    }
}
