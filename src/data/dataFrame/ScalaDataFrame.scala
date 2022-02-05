package dataFrame
import visitor.Visitable


trait ScalaDataFrame extends Visitable{

  def at(row: Int, column: String): Object

  def iat(row: Int, column: Int): Object

  def columns(): Int

  def size(): Int

  def getCategories(): List[String]

  def getField(label: String): List[Object]

  def accept (v: visitor.Visitor): Unit = {
    v.reset()
    v.visit(this)
  }
}
