package dataFrame

import visitor.Visitor

import scala.jdk.CollectionConverters._

class ScalaFileDF(df: JavaDataframes.DataFrame) extends ScalaDataFrame {

  override def at(row: Int, column: String): Object =
    df.at(row, column)

  override def iat(row: Int, column: Int): Object =
    df.iat(row, column)

  override def columns(): Int =
    df.columns()

  override def size(): Int =
    df.size()

  override def getCategories(): List[String] = df.getCategories.asScala.toList

  override def accept(v: Visitor): Unit = super.accept(v)

  override def getField(label: String): List[Object] = df.getData.get(label).asScala.toList

  override def toString: String = df.toString
}