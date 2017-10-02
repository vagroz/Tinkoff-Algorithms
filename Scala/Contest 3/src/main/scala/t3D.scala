import scala.io.StdIn
import scala.collection.immutable.Vector
import scala.math._

object t3D extends App {
  val inpt = StdIn.readLine.split(" ").map(_.toInt)
  val N = inpt(0);
  val M = inpt(1)
  val olenenok1 = Array.ofDim[Int](N, M)
  for (i <- 0 until N) {
    olenenok1(i) = StdIn.readLine.split(" ") map (_.toInt)
  }
  val dp = olenenok1.clone()
  for (i <- 0 until N) {
    dp(i) = dp(i).map(_ => Int.MaxValue)
  }

  val olenenok = olenenok1.toVector.map(_.toVector)
  dp(0)(0) = olenenok(0)(0)



  def app(x: Int, y: Int, value: Int): Unit = {
    if (x >= 0 && y >= 0 && x < N && y < M) {
      dp(x)(y) = min(value + olenenok(x)(y), dp(x)(y))

    }
  }

  def func(x: Int, y: Int): Unit = {
    //println (s"Get: {$x, $y}")
    if (x < 0 || y >= M) {
      val n = x + y
      //println (s"Out of range, pass: {${n+1}, ${0}}")
      func(n + 1, 0)
    } else if (x >= N) {
      //println (s"too much, pass: {${N-1}, ${y+x-N+1}}")
      func(N - 1, y + x - N + 1)
    } else {
      //println (s"Write in: [$x, $y]")
      val a = x + 1
      val b = y + 1
      val value = dp(x)(y)
      app(x, b, value)
      app(a, y, value)
      if (x != N - 1 || y != M - 1){
        //println (s"Pass: {${x-1}, ${y+1}}")
        func(x - 1, y + 1)
      }
    }
  }


  func(0, 0)
  /*for (i <- 0 until N){
    for (j <- 0 until M)
      print (s"${dp(i)(j)} ")
    println
  }*/
  println(dp(N - 1)(M - 1))

  val n = N + M - 2
  println(n+1)

  var xx = N - 1
  var yy = M - 1
  val way = for (i <- 1 to n) yield {
    val geo = (xx+1, yy+1)
    if (dp(xx - 1)(yy) == dp(xx)(yy) - olenenok(xx)(yy))
      xx -= 1
    else
      yy -= 1
    geo
  }
  {way :+ (1, 1)}.reverse foreach(println)

}
