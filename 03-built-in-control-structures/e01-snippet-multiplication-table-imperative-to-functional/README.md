# e01 &mdash; Printing Multiplication Table: imperative to functional (snippet)
> an example of how to migrate pure imperative code to a more functional style

## Description
The example illustrates two implementations for a program that prints the multiplication table:
```
   0   1   2   3   4   5   6   7   8   9  10
   0   2   4   6   8  10  12  14  16  18  20
   0   3   6   9  12  15  18  21  24  27  30
   0   4   8  12  16  20  24  28  32  36  40
   0   5  10  15  20  25  30  35  40  45  50
   0   6  12  18  24  30  36  42  48  54  60
   0   7  14  21  28  35  42  49  56  63  70
   0   8  16  24  32  40  48  56  64  72  80
   0   9  18  27  36  45  54  63  72  81  90
   0  10  20  30  40  50  60  70  80  90 100
```

The first approach is *purely imperative*, using nested while loops, while the second approach is functional:
+ creating functions that are orchestrated to obtain the final result
+ returning data is preferred over performing side-effect actions

## Running the Snippet
The snippet is intended to be run from `sbt consoleQuick`. Once inside the console type:
```
scala> :load multiplication-table-imperative-to-functional.scala
```