def greetMe(name: String) = println(s"Hey there, $name!")

() == greetMe("yay") // true

var line = ""
while ((line = readLine()) != "")  // this will never end
  println(s"Read: $line")