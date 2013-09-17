package week6

object test {
  val xs = Array(1, 2, 3, 44)                     //> xs  : Array[Int] = Array(1, 2, 3, 44)
  xs map (_ * 2)                                  //> res0: Array[Int] = Array(2, 4, 6, 88)

  val s = "Hello World"                           //> s  : String = Hello World
  s filter (_.isUpper)                            //> res1: String = HW

  s exists (_.isUpper)                            //> res2: Boolean = true
  s forall (_.isUpper)                            //> res3: Boolean = false

  val pairs = List(1, 2, 3) zip s                 //> pairs  : List[(Int, Char)] = List((1,H), (2,e), (3,l))
  pairs.unzip                                     //> res4: (List[Int], List[Char]) = (List(1, 2, 3),List(H, e, l))

  s flatMap (c => List('.', c))                   //> res5: String = .H.e.l.l.o. .W.o.r.l.d

  s.max                                           //> res6: Char = r
  s.sum                                           //> res7: Char = „M

  (1 to 3) flatMap (x => (1 to 2) map (y => (x, y)))
                                                  //> res8: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (1,2
                                                  //| ), (2,1), (2,2), (3,1), (3,2))

  def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double = (xs zip ys).map { case (x, y) => x * y }.sum
                                                  //> scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double
  def isPrime(n: Int): Boolean = (2 until n) forall (n % _ != 0)
                                                  //> isPrime: (n: Int)Boolean
  isPrime(7)                                      //> res9: Boolean = true

}