package dataFrame

import scala.annotation.tailrec

object ScalaListFilter {

  @tailrec
  def listFilterMapTail[A](cond: A=>Boolean, operation: A=>A, listCat: List[A], listaAcc: List[A]): List[A] = listCat match {
    case Nil => listaAcc
    case x :: xs => if(cond(x)) listFilterMapTail(cond,operation,xs,operation(x) :: listaAcc) else listFilterMapTail(cond,operation,xs,listaAcc)
  }
  def listFilterMapStack[A](cond: A=>Boolean, operation: A=>A, listCat: List[A]): List[A] = listCat match {
    case Nil => Nil
    case x :: xs => if(cond(x)) operation(x) :: listFilterMapStack(cond,operation,xs) else listFilterMapStack(cond,operation,xs)
  }

  def roundOver100(list: List[Double]): List[Double] = {
    listFilterMapStack[Double]((x:Double)=> x>100.0, (y:Double)=> y.round, list)
  }

  def replaceWord(list: List[String], originalWord: String, replacementWord:String): List[String] = {
    listFilterMapTail[String]((x:String)=>x.contains(originalWord), (x:String)=>x.replace(originalWord, replacementWord), list, List())
  }

  def replaceNotFiltering(list: List[String], originalWord: String, replacementWord:String): List[String] = {
    listFilterMapStack((x:String)=>true, (x:String)=>x.replace(originalWord, replacementWord), list)
  }

  def curry[Int] (list: List[Int]) (f:Int=>Int):List[Int] = {
    list.map(f)
  }
}
