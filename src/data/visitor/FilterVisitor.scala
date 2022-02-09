package visitor
import dataFrame.ScalaDataFrame

class FilterVisitor(pred: Object => Boolean, label: String) extends Visitor with Visitable {
  var listResult: List[Object] = List[Object]()
  var column: String = label

  override def visit(sDf: ScalaDataFrame): Unit = {
    //listResult = sDf.getField(column).filter(pred)
    for {
      i <- 0 until sDf.size()
      if pred(sDf.at(i, column))
    } yield listResult = sDf.at(i, column) :: listResult

  }
  override def reset(): Unit = listResult = List[Object]()

  /**
   * Method to demonstrade Multiple inheritance and Meta Class usage
   * @param visitor in this case, it will not perform the operation
   */
  override def accept(visitor: Visitor): Unit =
    println(this.getClass.getName+" has been visited by "+visitor.getClass.getName)
}

