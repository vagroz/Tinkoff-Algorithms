import java.util.Scanner

import scala.annotation.tailrec

object t3B extends App{
  val sc = new Scanner(System.in)
  val (x,y) = (sc.nextInt()-1, sc.nextInt()-1)

  val nextLine: (Seq[Int]) => Seq[Int] = line =>
    (line:+0).foldLeft[Seq[(Int, Int)]](Seq((0,0))){(seq, n)=>
      seq:+(n,seq.last._1+n)
    } map {_._2} drop 1

  @tailrec
  def getPifagorLine(n: Int, i: Int = 0, line: Seq[Int] = Seq(1)): Seq[Int]={
    if (i==n)
      line
    else
      getPifagorLine(n, i+1, nextLine(line))
  }

  val xy = x+y
  if (xy%3 != 0)
    println (0)
  else {
    val n = xy/3
    val pifagorLine = getPifagorLine(n)
    println (pifagorLine(x-n))
  }

}
