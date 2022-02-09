package dataFrame
import visitor.Visitable


trait ScalaDataFrame extends Visitable {

  def at(row: Int, column: String): Object

  def iat(row: Int, column: Int): Object

  def columns(): Int

  def size(): Int

  def getCategories(): List[String]

  /**
   * Add a method to the DataFrame to retrieve one of the columns as a list so that we can apply operations to all values of a column next.
   * @param label: A column
   * @return Values List
   */
  def getField(label: String): List[Object]

  def accept (v: visitor.Visitor): Unit = {
    v.reset()
    v.visit(this)
  }
}
