package dataFrame
import visitor.Visitor

import scala.jdk.CollectionConverters._
import scala.annotation.tailrec
import scala.collection.convert.ImplicitConversions.`seq AsJavaList`
import scala.language.postfixOps


class ScalaDirectory() extends ScalaDataFrame {

  var children: List[ScalaDataFrame] = List()


  override def at(row: Int, column: String): Object = aux(_,_,children)

  /*def aux (row: Int, column: String, list: List[ScalaDataFrame]): Object = (list) match {
    case (Nil) => "Index not valid, element not found"
    case (x::list) => if (x.size()<=row) aux(row-x.size(),column,list) else x.at(row,column)
  }*/

  @tailrec
  private def aux(row: Int, column: String, list: List[ScalaDataFrame]): Object = (list) match {
    case (Nil) => "Index not valid, element not found"
    case (x::list) =>
      if(x.getCategories().contains(column))
          if (x.size()<=row) aux(row-x.size(),column,list) else x.at(row,column)
      else aux(row, column, list)
  }

  override def iat(row: Int, column: Int): Object =
    at(row, this.getCategories().get(column))

  override def columns(): Int =
    children.foldLeft(0) { (acc, df) => acc + df.columns() }

  override def size(): Int = {
    children.foldLeft(0) { (acc, df) => acc + df.size() }
  }

  def add(child: ScalaDataFrame): Unit =
    children = child :: children

  def remove(child: ScalaDataFrame): Unit =
    children = children.filter(_!=child)

  override def getCategories(): List[String] = children.map((x) =>
    x.getCategories()).reduce((x,y) =>x.filterNot(c=>y.contains(c))).distinct

  def getChildren:List[ScalaDataFrame] = children

  override def accept(v: Visitor): Unit = super.accept(v)

  override def getField(label: String): List[Object] = null;
//getFieldAux(label,children, List())

 /* private def getFieldAux(label: String, list: List[ScalaDataFrame], default:List[Object]): null /* List[Object] = (list) match{
    case Nil => default
    case (x::xs) => x.getField(String) :: getFieldAux(label,xs,default)
  }*/*/

}
