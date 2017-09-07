import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("postfix.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		br.close();
		
		
		Stack <Integer> stack = new Stack <Integer>();
		while (st.hasMoreTokens()){
			String token = st.nextToken();
			char op = token.charAt(0);
			int res;
			switch(op){
			case '+':
				res = stack.pop() + stack.pop();
				break;
			case '-':
				res = -(stack.pop() - stack.pop());
				break;
			case '*':
				res = stack.pop() * stack.pop();
				break;
			default:
				res = Integer.parseInt(token);
			}
			stack.push(res);
		}
		FileWriter fw = new FileWriter("postfix.out");
		String out = stack.pop().toString();
		fw.write(out);
		fw.close();
	}

}
