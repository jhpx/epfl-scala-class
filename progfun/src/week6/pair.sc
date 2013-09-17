package week6

object pair {
  def isPrime(n: Int): Boolean = (2 until n) forall (n % _ != 0
                                                  //> isPrime: (n: Int)Boolean)
  val n =                                         //> n  : Int = 77
  (1 until n) flatMap (i =>
    (1 until i) map (j => (i, j))) filter (p => isPrime(p._1 + p._2)
                                                  //> res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ), (4,1), (4,3), (5,2), (6,1), (6,5)))
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (i,                                     //> res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ), (4,1), (4,3), (5,2), (6,1), (6,5)) j)
  def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
    (for { (x, y) <- (xs zip ys) } yield x        //> scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double* y).sum

}