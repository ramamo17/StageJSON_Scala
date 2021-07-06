sealed trait Tree[+T] { /* sealed secures the code +T is covariance,
                        ça fait que pour tout Type A et B avec B sous type de A
                        on a que Tree[B] est un sous type de Tree[A]   */
  def headOption: Option[T] //Option va faire qu'on aura soit None soit Some(T) et rien d'autre
  def isEmpty: Boolean
}
// retrouver ce que c'est un trait, un sealed trait, Option
// le code est bien à comprendre
//
object Tree {
  def apply[T](): Tree[T] = EmptyTree //def apply(...) enables the usual method name-less syntax of A(...)
  def apply[T](head: T, children: Vector[Tree[T]]): Tree[T] = NonEmptyTree(head, children)
  def apply[T](head: T, children: Tree[T]*): Tree[T] = NonEmptyTree(head, children.toVector)

  def unapply[T](tree: Tree[T]): Option[(T, Vector[Tree[T]])] = tree match {
    case NonEmptyTree(head, children) => Some((head, children))
    case EmptyTree => None
  } //def unapply(...) allows to create custom pattern matching extractors
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

// examples--------------------------------------

val tree1 = NonEmptyTree(1, Tree(1), NonEmptyTree(3), EmptyTree)
val tree2 = EmptyTree
val tree3 = Tree[Int]()
val tree4 = Tree("A", Tree(2), Tree(4))
val tree5 = Tree(2, Tree(4), EmptyTree)
val lst = List(1, 2, 3, 4) //avec Array on a obtient quelque chose de bizarre 
val tree6 = Tree(lst, Tree(9))
val tst = (tree6.headOption.get)
println (tst.asInstanceOf[List[Any]].tail)


tree6 match {
  case NonEmptyTree(head, children) => println("non empty!")
  case EmptyTree => println("empty!")
}
tree4 match {
  case EmptyTree => println("empty!")
  case t @ Tree(h, c) => println(t) // le t @ est un ajout de variable qui permet d acceder a Tree(h,c)
  case _ => println("err")
} // c'est ce match qui est le plur interessant car il permet d'acceder a l'arbre
