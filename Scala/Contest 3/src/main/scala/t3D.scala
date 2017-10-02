import scala.annotation.tailrec
import scala.io.StdIn

object t3D extends App {
  val n = StdIn.readLine.toInt
  val dp = (0 to n).map{ x => if (x != 1) (-1, 0) else (0, 0) }.toVector

  @tailrec
  def func(dp: Vector[(Int, Int)], number: Int): Vector[(Int, Int)] = {
    val (last, ways) = dp(number)
    val nextNums = Seq(number * 2, number * 3, number + 1)
    val tasks = nextNums.collect{case x if x <= n && (dp(x)._2 == 0 || dp(x)._2 > ways + 1)
        && dp(x)._2 > ways + 1 =>
      (x, ways + 1)
    }
    val newDp = tasks.foldLeft(dp){(ddpp, task) =>
      ddpp.updated(task._1, (number, task._2))
    }

    if (number == n){
      newDp
    }else{
      func(newDp, number + 1)
    }
  }

  val result = func (dp, 1)
  println(result.last._2)

  val way: (Int, Seq[Int]) => Seq[Int] = (i, data) => {
    if (i >= 1){
      val newData = i +: data
      way(result(i)._1, newData)
    }else{
      data
    }
  }

  way (n, Seq()).foreach(x => print (s"$x "))
  println

}
