
/* Integral types */
val integer = 12345
val longInteger = 12345L
val hexInteger = 0x23
// val octInteger = 023 /* this is already considered obsolete */

/* you can explicitly declare the type for a literal */
val i: Byte = 2
i

/* For characters you can use the Unicode escape sequence or the character */
val a = "\u0061"
val capitalC = 'C'

/* You can use Unicode for variable names */
val \u03c0 = Math.PI


/* Real numbers */
val f = 1.234f
val d = 1.0

/* Note that when using a method on a float/double literal you need to put a space! */
1. toString
1.toString

/* strings */
val greeting = "Hello to \"Jason Isaacs\""

// multiline strings
val greetingMultiline = """Hello
to
Jason
Isaacs"""

val greetingMultiline2 = """Hello
  | to
  | Jason
  | Isaacs"""

val greetingMultiline3 = """Hello
  | to
  | Jason
  | Isaacs""".stripMargin


// more multiline and stripMargin
val greetingMultiline4 =
  """Welcome to Ultamix 3000.
     Type "HELP" for usage notes."""

println(greetingMultiline4.stripMargin)  // this does not work as expected, but it guess it's a console related issue

// string interpolation
val name = "Jason Isaacs"
val greeting = s"Hello to $name!!!"
val greeting2 = s"Hello to ${ name }!!!"

// inline formatting + string interpolation
val firstName = "Idris"
var height = 1.90
val formattedGreeting = f"Hi, I'm ${ firstName }%s and I'm $height%2.2f metres tall"
