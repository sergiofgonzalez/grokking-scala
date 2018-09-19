def greet() = { println("hi!") }

() == greet()

var message = ""

// reassignment to var returns `Unit`
(message = "Hello to Jason Isaacs!") == "Hello to Jason Isaacs!"