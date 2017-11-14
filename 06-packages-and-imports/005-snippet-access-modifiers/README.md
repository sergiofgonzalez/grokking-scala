# 005 &mdash; Hello, Access Modifiers (snippet)
> introduces access modifiers as a way to get fine-grained control of the visibility

## Description
Access modifiers is an Scala concept that lets you set up a very fine-grained control over visibility. The syntax is `private[X]` or `protected[X]` where *X* might be an enclosing package, class or singleton object.

For example:
```scala
package rockets

package navigation {
  private[rockets] class Navigator {
  ...
  }
}
```

This means that class `Navigator` is private, except for members of the rockets package. 

You can even use:
```scala
class Navigator { 
  private[this] var speed = 200
}
```

which creates and *object-private* definition &mdash; it ensures that this variable will not be seen from other objects of the same class.


## Running the Snippet
The snippets are intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load access-modifiers.scala
```