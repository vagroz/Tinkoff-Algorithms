import java.util.Scanner;

public class TaskA {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] mas = new long[10];
        for (int i = 1; i < 10; i++) {
            mas[i] = 1;
        }
        for (int j = 2; j <= N; j++) {
            long[] masNew = new long[10];
            for (int i = 0; i < 10; i++) {
                if (i > 0)
                    masNew[i-1] += mas[i];
                if (i < 9)
                    masNew[i+1] += mas[i];
                masNew[i] += mas[i];
            }
            mas = masNew;
        }
        long res = 0;
        for (long elem: mas){
            res += elem;
        }
        System.out.println(res);
    }
}
