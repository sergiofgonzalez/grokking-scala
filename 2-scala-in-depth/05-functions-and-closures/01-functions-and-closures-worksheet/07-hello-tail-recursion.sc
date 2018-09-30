/* this is not tail-recursive (see the stack frame) */
def boom(x: Int): Int =
  if (x == 0) throw new Exception("boom!")
  else boom(x - 1) + 1

//boom(3)

/* this is tail-recursive (single stack frame) */
def boomTR(x: Int): Int =
  if (x == 0) throw new Exception("boom!")
  else boomTR(x - 1)

boomTR(10)