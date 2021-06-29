sealed trait Tree[+T] {
  def headOption: Option[T]
  def isEmpty: Boolean
}
// retrouver ce que c'est un trait, un sealed trait, Option
// le code est bien à comprendre
//
object Tree {
  def apply[T](): Tree[T] = EmptyTree
  def apply[T](head: T, children: Vector[Tree[T]]): Tree[T] = NonEmptyTree(head, children)
  def apply[T](head: T, children: Tree[T]*): Tree[T] = NonEmptyTree(head, children.toVector)

  def unapply[T](tree: Tree[T]): Option[(T, Vector[Tree[T]])] = tree match {
    case NonEmptyTree(head, children) => Some((head, children))
    case EmptyTree => None
  }
}

case class NonEmptyTree[+T](head: T, children: Vector[Tree[T]]) extends Tree[T] {
  def headOption: Some[T] = Some(head)
  def isEmpty = false
}

object NonEmptyTree {
  def apply[T](head: T, children: Tree[T]*): NonEmptyTree[T] = NonEmptyTree(head, children.toVector)
}

case object EmptyTree extends Tree[Nothing] {
  def headOption = None
  def isEmpty = true
}

val tree1 = NonEmptyTree(1, Tree(1), NonEmptyTree(3), EmptyTree)
val tree2 = EmptyTree
val tree3 = Tree[Int]()
val tree4 = Tree("A", Tree(2), Tree(4))
val tree5 = Tree(2, Tree(4), EmptyTree)

tree4 match {
  case NonEmptyTree(head, children) => println("non empty!")
  case EmptyTree => println("empty!")
}
tree2 match {
  case t @ Tree(h, c) => println(t)
  case EmptyTree => println("empty!")
  
}
