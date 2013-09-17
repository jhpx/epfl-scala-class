package week4

abstract class Bool {
  def ifThenElse[T](t: => T, e: => T): T
  def &&(x: => Bool): Bool = ifThenElse(x, myFalse)
  def ||(x: => Bool): Bool = ifThenElse(myTrue, x)
  def unary_!(): Bool = ifThenElse(myFalse, myTrue)

  def ==(x: => Bool): Bool = ifThenElse(x, x.unary_!)
  def !=(x: => Bool): Bool = ifThenElse(x.unary_!, x)

}

object myTrue extends Bool {
  def ifThenElse[T](t: => T, e: => T): T = t
}

object myFalse extends Bool {
  def ifThenElse[T](t: => T, e: => T): T = e
}