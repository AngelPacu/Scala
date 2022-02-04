package dataFrame

trait ScalaDataFrame {

  def at(row: Int, column: String): Object

  def iat(row: Int, column: Int): Object

  def columns(): Int

  def size(): Int

  //def getCategories(): List[String]

  //def accept (visitor: Visitor, column: String): Unit = visitor.visit()


}
