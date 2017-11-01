val numbers = List(1, -7, 5, -3, 10)
numbers.filter(_ > 0)

/*
  sometimes Scala will ask for placeholder
  types
*/
val sumFnWrong = _ + _ // Error: missing parameter type

val sumFn = (_: Int) + (_: Int)  // same as (x: Int, y: Int) => x + y
sumFn(5, 5)