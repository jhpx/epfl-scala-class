package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
* This class is a test suite for the methods in object FunSets. To run
* the test suite, you can either:
* - run the "test" command in the SBT console
* - right-click the file in eclipse and chose "Run As" - "JUnit Test"
*/
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
* Link to the scaladoc - very clear and detailed tutorial of FunSuite
*
* http://doc.scalatest.org/1.8/index.html#org.scalatest.FunSuite
*
* Operators
* - test
* - ignore
* - pending
*/

  /**
* Tests are written using the "test" operator and the "assert" method.
*/
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
* For ScalaTest tests, there exists a special equality operator "===" that
* can be used inside "assert". If the assertion fails, the two values will
* be printed in the error message. Otherwise, when using "==", the test
* error message will only say "assertion failed", without showing the values.
*
* Try it out! Change the values so that the assertion fails, and look at the
* error message.
*/
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
* When writing tests, one would often like to re-use certain values for multiple
* tests. For instance, we would like to create an Int-set and have multiple test
* about it.
*
* Instead of copy-pasting the code for creating the set into every test, we can
* store it in the test class using a val:
*
* val s1 = singletonSet(1)
*
* However, what happens if the method "singletonSet" has a bug and crashes? Then
* the test methods are not even executed, because creating an instance of the
* test class fails!
*
* Therefore, we put the shared values into a separate trait (traits are like
* abstract classes), and create an instance inside each test method.
*
*/

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val evens = (x:Int) => x%2 == 0
    val odds = (x:Int) => x%2 != 0
    val multsOf3 = (x:Int) => x%4 == 0
    val multsOf4 = (x:Int) => x%4 == 0
    val integers = (x:Int) => true
  }

  /**
* This test is currently disabled (by using "ignore") because the method
* "singletonSet" is not yet implemented and the test would fail.
*
* Once you finish your implementation of "singletonSet", exchange the
* function "ignore" by "test".
*/
  test("singletonSet(1) contains 1") {
    
    /**
* We create a new instance of the "TestSets" trait, this gives us access
* to the values "s1" to "s3".
*/
    new TestSets {
      /**
* The string argument of "assert" is a message that is printed in case
* the test fails. This helps identifying which assertion failed.
*/
      assert(contains(s1, 1), "Singleton")
    }
  }
  
  test("singletonSet(1) doesn't contain 2") {
    new TestSets {
      assert(!contains(s1, 2), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("empty intersection contains no elements") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersection 1")
      assert(!contains(s, 2), "Intersection 2")
      assert(!contains(s, 3), "Intersection 3")
    }
  }
  
  test("difference contains no elements") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Difference 1")
      assert(!contains(s, 2), "Difference 2")
      assert(!contains(s, 3), "Difference 3")
    }
  }
  
  test("filter") {
    new TestSets {
      val s = filter(evens, x => x > 0)
      assert(contains(s, 2), "Positive Evens 2")
      assert(!contains(s, -2), "Positive Evens -2")
      assert(!contains(s, 3), "Positive Evens 3")
    }
  }
  
  test("forall") {
    new TestSets {
      assert(!forall(evens,x => x%4 == 0), "All evens aren't divisible by 4")
      assert(forall(multsOf4,x => x%2 == 0), "All multiples of 4 are even")
    }
  }
  
  test("exists") {
    new TestSets {
      assert(exists(evens,x => x%4 == 0), "A multiple of 4 exists in the evens")
      assert(!exists(multsOf4,x => x == 3), "3 doesn't exist in the multiples of 4")
    }
  }
  
  test("map integers times 2") {
    new TestSets {
      val s = map(integers,x => 2*x)
      assert(contains(s,2), "2 is in the integers doubled")
      assert(!contains(s,3), "3 isn't in the integers doubled")
      assert(contains(s,2000),"2000 is the integers doubled")
      assert(!contains(s,4000),"4000 is the integers doubled, but cross the bounder")
    }
  }
  
  test("map set - 1") {
    new TestSets {
      val s = map(singletonSet(1000),x => x-1)
      assert(contains(s,999), "999 is in 100 - 1")
    }
  }
  
}