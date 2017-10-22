# e03 &mdash; Reading lines from a file
> basic Scala program that read lines from a file

## Description
Illustrates how to write a program that read lines from a file and prints them out prepended with the number of characters in each line.

### Running the application
Type `sbt` and then:

```bash
sbt:Hello> run                  # you'll get error and usage info
sbt:Hello> run README.md        # will print README.md lines
sbt:Hello> run Non-existing.txt # java.io.FileNotFoundException
```

You can return to the command line just by typing: 
```bash
sbt:Hello> exit
```
