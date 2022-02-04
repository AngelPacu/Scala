object EjemplosScala { // <- same name as file, object means it's a singleton (static)
  def main(args: Array[String]) {
    println("Hello Scala")
    val immutable: Int = 5
    var mutable: String = "hi"
    var anyTipe: Any = 123

    //immutable = 10 - error, val is immutable
    mutable = "hello" // ok, var is mutable
    anyTipe = "sasd" // "Any" can be any kind of object

    // Arrays
    var strArray = Array("One", "Two", "Three")
    if (!strArray.isEmpty) {
      strArray(0) = "Four"
    }

    // Whiles
    var count = 0
    while (count < 10) {
      println(10)
      count += 1
    }

    // Fors, both numbers included
    for (i <- 1 to 5) {
      // s"" denotes string with literals
      println(s"number is $i")
    }

    // Creating classes
    val animal = new Animal
    animal.name = "Tiger"
    animal.age = 2
    animal.talk

    val dog2 = new Dog("ha")

    val dog = new Dog("pepe", "Doggo")
    dog.age = 5
    dog.talk
    dog.walk(1)
    println("Dog age is: " + dog.age)

    // Use high order funtions
    println(apply(format, 50))

    // Define a list
    val nums = List(3, 6, 5, 8)

    // Define a high order function as variable (Like Predicate or Comparator in java)
    val multiply = (y: Int, x: Int) => x * y

    // Use function to all list members (lambda in java)
    val result = nums.map(num => multiply(num, 5))
    println("Result is " + result)

  }

  // Simple function
  def hello = println("Hello!")

  // More complex function
  /*
  def functionName(parameters) : returnType = {
    body
   } */
  def mulYTimes(x: Double, y: Int): Double = {
    var sum: Double = 0
    var inc: Int = y
    while (inc > 0) {
      sum += x
      inc -= 1
    }
    sum // return not needed, it gets it like a print
  }

  // High order functions
  // x: Type => Type2 --> x parameter is function, param is Type and returns Type2
  // y: Double --> Second parameter of this applu function
  def apply(x: Double => String, y: Double): String = x(y)

  // "R" counts as abstract type <T>
  def format[R](x: R): String = "The R chosen is " + x.toString

  // Recursive/nested functions
  def factorial(x: Int): Int = {
    def fact(x: Int, accumulator: Int): Int = {
      if (x <= 1) accumulator
      else fact(x - 1, x * accumulator)
    }
    fact(x, 1)
  }
}

// Classes, fields must be defined
class Animal {
  var name: String = null
  var age: Int = 0

  def talk = println("hi")
}

// Class inheritance, + constructor
class Dog(powner: String) extends Animal {
  def this(owner: String, name: String) {
    this(owner)
    this.name = name;
  }

  private var owner: String = powner

  override def talk = println("bark")

  def walk(km: Int) = println(s"$name walks $km km with $owner")
}