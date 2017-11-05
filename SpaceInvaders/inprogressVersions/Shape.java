import java.awt.Graphics;
import java.awt.Rectangle;

/* This abstract class contains the size of the shape and the topLeft coordinate of the object which is of type Point 
and methods to change and get values of the Shape. */
public abstract class Shape {

    private Point topLeft;
    private int length; 
	private int width;

    /** 
    * A constructor requiring the location of the top left point of the shape and its desired size.
    * @param  newTopLeftPoint  the top left coordinate of the shape
	* @param  newSize          an integer value for the size of the shape
    */
    public Shape(Point newTopLeftPoint, int newLength, int newWidth) {
        topLeft = new Point(newTopLeftPoint.getXCoord(), newTopLeftPoint.getYCoord());
        length = newLength;
		width = newWidth;
    }
	
	 public Shape(Point newTopLeftPoint) {
        topLeft = new Point(newTopLeftPoint.getXCoord(), newTopLeftPoint.getYCoord());
    }
	/** 
    * A method returning a copy of the shape's top left point.
    */
    public Point getTopLeft() {
        return new Point(topLeft.getXCoord(), topLeft.getYCoord());
    }
	
	public int getColumn(){
		return topLeft.getXCoord();

	}
	
	public int getRow(){
		return topLeft.getYCoord();
	}
	public int getLength(){
		return length;
	}
	
	public int getWidth(){
		return width;
	}
	
	/** 
    * A method moving the selected object down on the board.
	* @param  amount  how far the shape is displaced down the board
    */
    public void moveDown(int amount) {
        topLeft.moveDown(amount);
    }
    /** 
    * A method moving the selected object up on the board.
    * @param  amount  how far the shape is displaced up the board
    */
    public void moveUp(int amount) {
        topLeft.moveUp(amount);
    }
    /** 
    * A method moving the selected object left on the board.
    * @param  amount  how far the shape is displaced left on the board
    */
    public void moveLeft(int amount) {
        topLeft.moveLeft(amount);
    }
	/** 
    * A method moving the selected object right on the board.
    * @param  amount  how far the shape is displaced right on the board
    */
    public void moveRight(int amount) {
        topLeft.moveRight(amount);
    }
	/** 
    * An abstract method to draw a shape.
    * @param  the Graphics object
    */
    public abstract void draw(Graphics g); 
}
