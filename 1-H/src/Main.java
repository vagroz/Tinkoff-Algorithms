import java.io.*;
import java.util.Scanner;
import java.util.Stack;



public class Main {
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner (System.in);//new File ("trucks.in"));
		int N = sc.nextInt(); int width = sc.nextInt();
		
		QueueWithMin mas = new QueueWithMin();
		
		for (int i=0; i<N; i++){
			mas.add(sc.nextInt());
			if (mas.size()==width){
				System.out.println(mas.min());
				mas.remove();
			}
		}
		
		sc.close();
	}

}

class QueueWithMin{
	private StackWithMin leftStack = new StackWithMin();
	private StackWithMin rightStack = new StackWithMin();
	
	public void add (int i){
		leftStack.push(i);
	}
	public int remove(){
		if (rightStack.isEmpty())
			while (!leftStack.isEmpty()){
				rightStack.push(leftStack.pop());
			}		
		return rightStack.pop();
	}
	public int min(){
		if (!leftStack.isEmpty() && !rightStack.isEmpty())
			return Math.min(leftStack.min(), rightStack.min());
		else if (leftStack.isEmpty())
			return rightStack.min();
		else 
			return leftStack.min();
	}
	
	public int size(){
		return leftStack.size()+rightStack.size();
	}
	public boolean isEmpty(){
		return leftStack.isEmpty() && rightStack.isEmpty();
	}
}


class StackWithMin {
	private Stack<Integer> stack, mins;
	StackWithMin(){
		stack = new Stack<Integer>();
		mins = new Stack<Integer>();
	}
	public void push(int i){
		stack.push(i);
		if (mins.isEmpty()){
			mins.push(i);
		}else{
			mins.push(Math.min(i, mins.peek()));
		}
	}
	public int peek(){
		return stack.peek();
	}
	public int pop(){
		mins.pop();
		return stack.pop();
	}
	public int min(){
		return mins.peek();
	}
	public int size(){
		return stack.size();
	}
	public boolean isEmpty(){
		return size() == 0;
	}
}
