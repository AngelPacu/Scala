package visitor

import dataFrame.ScalaDataFrame
trait Visitor {

  def visit(sDf: ScalaDataFrame) : Unit
  def reset(): Unit

}
