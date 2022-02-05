package visitor
import dataFrame.{ScalaDataFrame, ScalaDirectory, ScalaFileDF}

class CounterVisitor extends visitor.Visitor {

  var files: Int = 0
  var dirs: Int = 0

  override def visit(sDf: ScalaDataFrame): Unit ={
    sDf match {
      case directory: ScalaDirectory =>
        dirs += 1
        sDf.asInstanceOf[ScalaDirectory].getChildren.foreach(x=>visit(x))
      case file: ScalaFileDF =>files+=1
    }
  }

  override def reset(): Unit =
    files=0
    dirs=0
}
