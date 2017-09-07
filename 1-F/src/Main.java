import java.io.*;
import java.util.ArrayDeque;
import java.util.Scanner;



public class Main {
	final static int DELTA=20;
	public static void main(String[] args) throws IOException {
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//new FileReader("input"));
		String brackets = br.readLine();
		br.close();*/
		Scanner sc = new Scanner (System.in);//new File ("trucks.in"));
		int N = sc.nextInt();
		int i = 0;
		String [] results = new String[N];
		
		
		
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(i);
		MyTime currentTime = new MyTime(sc.nextInt(), sc.nextInt());
		MyTime exitTime = currentTime.plus(DELTA);
		sc.nextInt();
		
		MyTime next = new MyTime (sc.nextInt(), sc.nextInt());
		int k = sc.nextInt();
		i++;
		
		while (i<N || !queue.isEmpty()){
			//System.out.println (exitTime.toS()+" - cur: "+currentTime.toS());
			if (exitTime.eq(currentTime)){
				results[queue.poll()]=currentTime.toS();
				if (!queue.isEmpty()){
					exitTime = currentTime.plus(DELTA);
				}
			}
			
			if (i<N && currentTime.eq(next)){
				if (k<queue.size()){
					results[i]=currentTime.toS();
				}else{
					queue.offer(i);
					if (queue.size()==1){
						exitTime = currentTime.plus(DELTA);
					}
				}
				i++;
				if (i<N){
					next = new MyTime (sc.nextInt(), sc.nextInt());
					k = sc.nextInt();
				}
			}
			currentTime.inc();
		}
		for (int j =0; j<results.length; j++)
			System.out.println(results[j]);
		/*FileWriter fw = new FileWriter("trucks.out");
		fw.write(ntrucks+"");
		fw.write("\n");
		fw.close();*/
	}

}

class MyTime{
	private int hours;
	private int minutes;
	public void inc(){
		minutes+=1;
		if (minutes==60){
			hours+=1;
			minutes=0;
		}
	}
	public boolean less(MyTime time){
		if (time.hours>this.hours || 
				(time.hours==this.hours && time.minutes > this.minutes))
			return true;
		else return false;
	}
	public boolean eq(MyTime time){
		if (minutes == time.minutes && hours == time.hours)
			return true;
		else
			return false;
	}
	public int minus (MyTime time){
		return (this.hours*60+this.minutes)-(time.hours*60+time.minutes);
	}
	public MyTime plus (int m){
		int newminutes=minutes+m;
		int newhours=hours+newminutes/60;
		newminutes%=60;
		return new MyTime (newhours, newminutes);
	}
	MyTime (int h, int m){
		hours = h;
		minutes = m;
	}
	public String toS (){
		return hours+" "+minutes;
	}
}
