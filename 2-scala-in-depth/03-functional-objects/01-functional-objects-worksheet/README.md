# 01 &mdash; Basic Types and Operations
> worksheets for practicing on Basic Types and Operations

To run the project, open it using IntelliJ IDEA and run the corresponding worksheet.

## Worksheets
+ [01 &mdash; Hello Rational Class](./01-rational-class.sc)
Illustrates how to create the Rational class with class parameters.

+ [02 &mdash; Hello Class Parameters](./02-hello-class-parameters.sc)
Illustrates how to use class parameters in the body of the function.

+ [03 &mdash; Overriding `toString`](./03-overriding-to-string.sc)
Illustrates how to override the default `toString` inherited from `java.lang.Object`.

+ [04 &mdash; Adding Preconditions`](./04-adding-preconditions.sc)
Illustrates how to override the default `toString` inherited from `java.lang.Object`.

+ [05 &mdash; `add` Method v1`](./05-add-method-v1.sc)
Illustrates an initial implementation of the `add` method that does not compile because the class parameters cannot be accessed.

+ [06 &mdash; `add` Method v2`](./06-add-method-v2.sc)
Illustrates an implementation of the `add` method on which the numerator and denominator are defined as fields.

+ [07 &mdash; `lessThan` and `max` Method`](./07-lessThan-and-max-method.sc)
Illustrates an implementation of the `lessThan` and `max` method on which `this` is used to reference the current object instance.

+ [08 &mdash; Adding an aux constructor for Integers](./08-aux-constructor-for-ints.sc)
Illustrates how to write an auxiliary constructor for rationals having *1* as denominator.

+ [09 &mdash; Auxiliary constructors must invoke another constructor](./09-aux-constructor-must-invoke-another-constructor.sc)
Illustrates how an auxiliary constructor must invoke another constructor as its first action.

+ [10 &mdash; Adding normalization](./10-adding-normalization.sc)
Introduces the normalization concept with a private method that computes the *greatest common divisor* between the numerator and denominator.

+ [11 &mdash; Adding operators](./11-adding-operators.sc)
Renames existing methods to that the usage of those methods as operators will feel more natural.