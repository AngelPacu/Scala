package dataFrame

class ScalaDirectory() extends ScalaDataFrame {

  var children: List[ScalaDataFrame] = List()

  /*override def at(row: Int, column: String): Object = (children) match {
    case (Nil) => "Element not found"
    /*case (x :: xs) => if(x.size <= row) at(row-x.size,column) else x.at(row, column)*/ //X = df
    case (x :: xs) => if(this.size<=row) "Index not valid, element not found" else aux(row, column,0) //X = df
  }
   */

 /* def aux (row: Int, column: String, actual: Int): Object = (children) match {
    case (Nil) => "NULL"
    case (x::xs) => if (x.size()<=row) aux(row-x.size(),column,actual+1) else x.at(row,column)
  }

  */

  override def at(row: Int, column: String): Object = (children) match {
    case (Nil) => "NULL"
    case (x :: xs) => if(this.size<=row) "Index not valid, element not found" else null //aux(row,column,children)
  }

  def aux (row: Int, column: Object, lista: List[ScalaDataFrame]): Object = (children) match {
    case (Nil) => "NULL"
    case (x::lista) => if (x.size()<=row) aux(row-x.size(),_,lista) else x.at(row,_)
  }

  override def iat(row: Int, column: Int): Object = null

  override def columns(): Int =
    children.foldLeft(0) { (acc, df) => acc + df.columns() }

  override def size(): Int = {
    children.foldLeft(0) { (acc, df) => acc + df.size() }
  }

  def add(child: ScalaDataFrame): Unit =
    children = child :: children

  def remove(child: ScalaDataFrame): Unit =
    children = children.filter(_!=child)

  //override def getCategories(): List[String] = ???
}
