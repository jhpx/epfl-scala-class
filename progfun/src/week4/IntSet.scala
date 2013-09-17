object intsets {
  val t1 = new NonEmpty(3, new Empty, new Empty)  //> t1  : NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : IntSet = {.3{.4.}}
  val a:List[NonEmpty] = List(new NonEmpty(1,new Empty,new Empty))
                                                  //> a  : List[NonEmpty] = List({.1.})
  val b:List[IntSet] = a                          //> b  : List[IntSet] = List({.1.})
  
  val a2:Array[NonEmpty] = Array(new NonEmpty(1,new Empty,new Empty))
                                                  //> a2  : Array[NonEmpty] = Array({.1.})
 // val b2:Array[IntSet] = a2
}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(X: Int): Boolean
  def union(other: IntSet): IntSet
}

class Empty extends IntSet {
  def contains(X: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = "."
  def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
  override def toString = "{" + left + elem + right + "}"
  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}