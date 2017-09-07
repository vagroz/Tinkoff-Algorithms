import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("olymp.in"));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		br.close();
		
		Vector <Integer> numbers = new Vector <Integer> (N),
				scores = new Vector <Integer> (N);
		for (int i = 0; i<N; i++){
			int score = Integer.parseInt(st.nextToken());
			int j = 0;
			while (j<i && scores.get(j)>score)
				j++;
			scores.add(j, score);
			numbers.add (j, i);
		}
		
		FileWriter fw = new FileWriter("olymp.out");
		for (int i = 0; i<N; i++){
			fw.write(numbers.get(i)+1+" ");
		}
	
		fw.write("\n");
		fw.close();
	}

}
