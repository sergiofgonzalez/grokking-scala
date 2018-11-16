val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

capitals.get("France")
capitals.get("Spain")


/* using pattern matching to get the value from an Option */
def show(x: Option[String]): String =x match {
  case Some(s) => s
  case None =>   "n/a"
}

show(capitals.get("France"))
show(capitals.get("Spain"))