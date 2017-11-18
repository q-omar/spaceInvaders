import java.awt.Graphics;
import java.awt.Color;

public class PlayerShip extends Shape{
	/**
	* This class holds the method mechanics of the player ship
	*/  

    /** Constructor that centers the starting location of the ship based on the length of the screen and lets
    * one set its speed.
	* @param screenLength is the length of the screen
	* @param newY is the y coordinate of the ship
	* @param size is the size of the ship
	* @newSpeed the horizontal speed of the ship
    *****************************************************************/
    public PlayerShip(int screenLength, int newY, int size, int newSpeed) {
    	super(screenLength/2, newY, size, size);
        setHSpeed(newSpeed);
    }
    
    /**This method checks if horizontal index position of player ship is out of bounds
    * on the board array's horizontal parameter
    */
    public void inBounds(int boardWidth) { 
        if (getXCoord() >= boardWidth) {
            setXCoord(boardWidth - getHSpeed());
        } else if (getXCoord() < getHSpeed()) {
            setXCoord(0);
        }
    }
    
    /** This method moves the ship left or right horizontally depending on the string input by the player
	* @param is the string input that decides which way the ship moves
	*/
    
    public void move(String direction) {
    	if (direction.equals("A")) { 
    		setXCoord(getXCoord()-getHSpeed());
    	} else if (direction.equals("D")) {
    		setXCoord(getXCoord()+getHSpeed());
    	}
    }
    
    
	/** This method draws the ship
	@param Graphics object,g
	*/
	public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(getXCoord()-10,getYCoord(), getWidth(), getHeight());
    }
	
	@Override
    public String toString() {
        return "Column: " + getXCoord() + "  Speed:  " + getHSpeed() + "  Last X: " + getLastXCoord(); // Made this for testing
    }
}
