import scala.io.StdIn
import java.io._
import java.util.Scanner

object t3A extends App {
  val N = StdIn.readInt()
  val k = StdIn.readInt()
//  val sc = new Scanner(new File ("grasshopper.in"))
//  val N = sc.nextInt()
//  val k = sc.nextInt()

  val cells = new Array[Int](N)

  def getWays(i: Int): Int = {
    if (i<0 || i>=N) 0
    else cells(i)
  }

  cells(0)=1
  for (i <- 1 until N){
    var sum = 0
    for (j <- 1 to k)
      sum += getWays(i-j)
    cells(i)=sum
  }
  println(cells(N-1))
//  val fw = new FileWriter("grasshopper.out")
//  fw.write(cells(N-1).toString)
//  fw.close()
}
