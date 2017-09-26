import java.io.*;
import java.util.Stack;



public class Main {
	final static int DELTA = 9999;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//new FileReader("input"));
		String brackets = br.readLine();
		br.close();
		//Scanner sc = new Scanner (System.in);//new File ("trucks.in"));
		
		Stack <Character> stack = new Stack <Character>();
		boolean flag = false;
		for (int i=0; i<brackets.length(); i++){
			char c = brackets.charAt(i);
			char d;
			switch(c){
			case ']':
			case ')':
			case '}':
				if (stack.isEmpty()){
					flag = true;
				}else{
					d = stack.pop();
					if ((d != c-1) &&(d != c-2))
						flag=true;
				}
				break;
			default:
				stack.push(c);
			}
			if (flag)
				break;
		}
		if (!stack.isEmpty())
			flag=true;
		
		if (flag)
			System.out.println("NO");
		else
			System.out.println("YES");
		/*FileWriter fw = new FileWriter("trucks.out");
		fw.write(ntrucks+"");
		fw.write("\n");
		fw.close();*/
	}

}
