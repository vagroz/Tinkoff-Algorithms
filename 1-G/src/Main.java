import java.io.*;
import java.util.ArrayDeque;
import java.util.Scanner;



public class Main {
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner (System.in);//new File ("trucks.in"));
		int N = sc.nextInt();
		
		GoblinQueue queue = new GoblinQueue();
		
		for (int i=0; i<N; i++){
			char c = sc.next().charAt(0);
			switch (c){
			case '+':
				queue.push(sc.nextInt());
				break;
			case '*':
				queue.pushExclusive(sc.nextInt());
				break;
			case '-':
				System.out.println(queue.pop());
				break;
			default:
				System.out.println ("Bad request");
			}
		}
		sc.close();
		
	}

}

class GoblinQueue{
	private ArrayDeque<Integer> leftDeq, rightDeq;
	GoblinQueue(){
		leftDeq = new ArrayDeque<Integer>();
		rightDeq = new ArrayDeque<Integer>();
	}
	private void balance(){//size of left deq = size of right (or size + 1)
		while (rightDeq.size()>leftDeq.size())
			leftDeq.addLast(rightDeq.removeFirst());
	}
	public void push (int i){
		rightDeq.addLast(i);
		balance();
	}
	public void pushExclusive(int i){
		rightDeq.addFirst(i);
		balance();
	}
	public int pop(){
		int res = leftDeq.removeFirst();
		balance();
		return res;
	}
	public int size(){
		return leftDeq.size()+rightDeq.size();
	}
	public boolean isEmpty(){
		return leftDeq.isEmpty() && rightDeq.isEmpty();
	}
}
