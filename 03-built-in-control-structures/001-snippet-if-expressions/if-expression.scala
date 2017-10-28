/* default value management */

val args = Nil

var filename = "default.txt"
if (!args.isEmpty) 
  filename = args(0)

/* as if is an expression its result can be assigned to a variable */
val filename = if (!args.isEmpty) args(0) else "default.txt"