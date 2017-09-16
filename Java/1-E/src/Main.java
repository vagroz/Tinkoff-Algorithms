import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;


public class Main {
	final static int DELTA = 9999;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input"));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//Scanner sc = new Scanner (System.in);//new File ("trucks.in"));
		
		int h = Integer.parseInt (st.nextToken());
		int [] mas;
		int startheight = 1;
		if (h<=DELTA){
			mas = new int [h+DELTA];
		}else{
			mas = new int [2*DELTA+1];
			startheight=h-DELTA;
		}
		mas[h-startheight]+=1;
		
		while (st.hasMoreElements()){
			h = Integer.parseInt(st.nextToken());
			mas[h-startheight]+=1;
		}
		
		
		for (int i=0; i<mas.length; i++){
			if (mas[i]>0){
				for (int j=0; j<mas[i]; j++){
					System.out.print (i+startheight+" ");
				}
			}
		}
		System.out.println();
		/*FileWriter fw = new FileWriter("trucks.out");
		fw.write(ntrucks+"");
		fw.write("\n");
		fw.close();*/
	}

}
