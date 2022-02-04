package dataFrame

class ScalaFileDF(df: JavaDataframes.DataFrame) extends ScalaDataFrame {

  override def at(row: Int, column: String): Object =
    df.at(row, column)

  override def iat(row: Int, column: Int): Object =
    df.iat(row, column)

  override def columns(): Int =
    df.columns()

  override def size(): Int =
    df.size()

  /*override def getCategories(): List[String] =

   */

}