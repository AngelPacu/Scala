package visitor

trait Visitable {
  def accept(visitor: Visitor) : Unit
}
