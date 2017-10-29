val range10to100 = 10 to 100
range10to100

10 to 100 contains 55  // <- true
10 to 100 contains 555 // <- false

10.to(20).contains(15)
10.to(20).contains(55)

/* you can exclude the upper bound using `until` */
0 to 5 contains 5
0 until 5 contains 5