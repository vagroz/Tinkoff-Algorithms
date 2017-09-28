import scala.io.StdIn.readLine

object t3C extends App {
  val input = readLine().split(" ").map(_.toInt)
  val (x,y) = (input(0), input(1))
  val mas = Array.ofDim[Long](x,y)
  mas(0)(0) = 1

  def setValue(i: Int,j: Int, vl: Long) {
    if (i>=0 && i<x && j>=0 && j<y)
      mas(i)(j)+=vl
  }

  def propogateWays(i: Int, j: Int){
    if (i>=0 && i<x && j>=0 && j<y){
      val seq = Seq ((+1, +2), (-1, +2), (+2, -1), (+2, +1))
      seq.foreach(xxx => setValue(i+xxx._1, j+xxx._2, mas(i)(j)))
    }
  }

  for (n <- 0 to x+y-2){
    for (j <- 0 to n){
      val i = n - j
      propogateWays(i,j)
    }
  }
//  for (i <- 0 until x){
//    for (j <- 0 until y){
//      print (mas(i)(j)+" ")
//    }
//    println ()
//  }
  println (mas(x-1)(y-1))
}
