
import java.awt.Graphics;
import java.awt.Color;

public class PlayerShip extends Shape{
/*************************************************
This class holds the method mechanics of the player ship
*************************************************/  

    /*****************************************************************
    * Constructor that centers the starting location of the ship based on the length of the screen and lets
    * one set its speed.
    *****************************************************************/
    public PlayerShip(int screenLength, int newY, int size, int newSpeed) {
    	super(screenLength/2, newY, size, size);
        setHSpeed(newSpeed);
    }
    
    /****************************
    method: inBounds
            checks if horizontal index position of player ship is out of bounds
            on the board array's horizontal parameter
    ****************************/
    public void inBounds(int boardWidth) { 
        if (getXCoord() >= boardWidth) {
            setXCoord(boardWidth - getHSpeed());
        } else if (getXCoord() < getHSpeed()) {
            setXCoord(0);
        }
    }
    
    // Condensed ship movement to 1 method
    
    public void move(String direction) {
    	if (direction.equals("A")) { // If moving left, subtract speed
    		setXCoord(getXCoord()-getHSpeed());
    	} else if (direction.equals("D")) {
    		setXCoord(getXCoord()+getHSpeed());
    	}
    }
    
    
	/**********************************
	method: draw
			draws the ship
			@param Graphics object,g
	*************************************/
	public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(getXCoord()-10,getYCoord(), getWidth(), getHeight());
    }
	
	@Override
    public String toString() {
        return "Column: " + getXCoord() + "  Speed:  " + getHSpeed() + "  Last X: " + getLastXCoord(); // Made this for testing
    }
}
