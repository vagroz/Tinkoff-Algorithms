import scala.annotation.tailrec
import scala.io.StdIn

object t3F extends App{
  val n = StdIn.readLine().toInt

  @tailrec
  def dp(triaple:(Int, Int, Int), i: Int ): (Int, Int, Int) = {
    if (i == n)
      triaple
    else {
      val a = triaple._1 + triaple._2 + triaple._3
      val b = triaple._1
      val c = triaple._2
      dp((a, b, c), i+1)
    }
  }
  val (a,b,c) = dp ((1,1,0), 1)
  println (a+b+c)

}
