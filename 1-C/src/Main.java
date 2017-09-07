import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;


public class Main {
	public static void main(String[] args) throws IOException {
		/*BufferedReader br = new BufferedReader(new FileReader("olymp.in"));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());*/
		
		Scanner sc = new Scanner (System.in);//new File ("trucks.in"));
		
		int n = sc.nextInt(); int k = sc.nextInt();
		int ntrucks = 0;
		
		Stack <Integer> stack = new Stack <Integer>();
		stack.push(n);
		
		while (!stack.isEmpty()){
			int a = stack.pop();
			if (a>k){
				stack.push(a/2);
				stack.push(a/2+a%2);
			}else{
				ntrucks++;
			}
		}
		
		FileWriter fw = new FileWriter("trucks.out");
		
		fw.write(ntrucks+"");
	
		fw.write("\n");
		fw.close();
	}

}
