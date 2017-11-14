# 004 &mdash; Scala's private and protected modifiers (snippet)
> rules and features of private and protected access modifiers in Scala

## Description
Scala's private members are more restrictive than in Java, because a member labeled private is visible only inside the class or object that contains the member definition.

```scala
class Outer {
  class Inner {
    private def f() = { println("f") }
    
    class InnerInner {
      f() // OK: we're inside the class that defines f
    }
  }
  (new Inner).f() // Err: method f() is not visible
}
```

Protected members are also more restrictive in Scala, as those are only available from subclasses of the class in which the member is defined (no same package access like in Java).
```scala
package p {
  class Super {
    protected def g() = { println("g") }
  }
  class Sub extends Super {
    g() // OK
  }
  class OtherInSamePackage {
    (new Super).g() // Err: Access to protected is not permitted, even when in the same package
  }
}
```

Scala has no explicit modifier for public members &mdash; any member not labeled `private` or `protected` is considered public.


## Running the Snippet
The snippets are intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load scala-private.scala
```

and for the protected example:
```
scala> :paste scala-protected.scala
```