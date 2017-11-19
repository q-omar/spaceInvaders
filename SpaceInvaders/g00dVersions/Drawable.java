import java.awt.Graphics;

/**
 * This interface indicates that it is possible to draw the object that implements it.
 */
public interface Drawable {
	/** 
	* An abstract method to draw some object.
	* @param  the Graphics object
	*/
	public abstract void draw(Graphics g);
}