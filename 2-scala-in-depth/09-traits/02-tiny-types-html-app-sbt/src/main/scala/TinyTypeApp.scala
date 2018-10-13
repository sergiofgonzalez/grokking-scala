
object TinyTypeApp extends App {
  /*
    We are modeling the following HTML anchor element with tiny types:
      <a id="anchor-1"><h1 class="text-class">anchor text here</h1></a>
  */

  class Anchor(val value: String) extends AnyVal
  class Style(val value: String) extends AnyVal
  class Text(val value: String) extends AnyVal
  class Html(val value: String) extends AnyVal {
    override def toString: String = value
  }



  def titleAnchor(text: Text, anchor: Anchor, style: Style): Html =
    new Html(s"<a id='${anchor.value}'><h1 class='${style.value}>${text.value}</h1></a>")

  println(
    titleAnchor(new Text("Hello to Jason Isaacs!"), new Anchor("anchor1"), new Style("bold") )
  )


}