class Point(val x: Int, val y: Int)

trait Rectangular {
  def topLeft: Point
  def bottomRight: Point

  def left: Int = topLeft.x
  def right: Int = bottomRight.x
  def width: Int = right - left

  def top: Int = topLeft.y
  def bottom: Int = bottomRight.y
  def height: Int = bottom - top
}

class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular

val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
rect.left
rect.right
rect.top
rect.bottom
rect.width
rect.height