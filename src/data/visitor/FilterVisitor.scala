package visitor
import dataFrame.ScalaDataFrame

class FilterVisitor(pred: Object => Boolean, label: String) extends Visitor {
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
}

