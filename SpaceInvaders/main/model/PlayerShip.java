package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;


/**
* This class holds methods to manipulate the player ship.
*/  
public class PlayerShip extends Shape{

    /** Constructor that centers the starting location of the ship based on the length of the screen and lets
    * one set its speed.
	* @param screenLength is the length of the screen
	* @param newY is the y coordinate of the ship
	* @param size is the size of the ship
	* @newSpeed the horizontal speed of the ship
    *****************************************************************/
    PlayerShip(int screenLength, int newY, int size, int newSpeed) {
    	super(screenLength/2, newY, size, size);
        setHSpeed(newSpeed);
    }
    
    /**
     * A copy constructor for the PlayerShip class.
     * @param toCopy the ship to be copied
     */
	PlayerShip(PlayerShip toCopy) {
		super(toCopy);
		setHSpeed(toCopy.getHSpeed());
	}
    
    /**This method checks if the horizontal position of the player ship is past a given horizontal boundary
     * (ie. the width of the screen). If so, the ship's position is reset to stay in bounds.
    */
    void inBounds(int boardWidth) {
        if (getXCoord() >= boardWidth) {
            setXCoord(boardWidth - getHSpeed());
        } else if (getXCoord() < getHSpeed()) {
            setXCoord(0);
        }
    }
    
    /** This method moves the ship left or right depending on the string input by the player
	* @param is the string input that decides which way the ship moves
	*/
    void move(String direction) {
    	if (direction.equals("A")) { 
    		setXCoord(getXCoord()-getHSpeed());
    	} else if (direction.equals("D")) {
    		setXCoord(getXCoord()+getHSpeed());
    	}
    }
    
	/** This method draws the ship. Image source: "https://uploads.scratch.mit.edu/users/avatars/869/0158.png"
	* @param Graphics object,g
	*/
	public void draw(Graphics g) {
        Image imageShip = new javax.swing.ImageIcon("ship.gif").getImage();
        g.drawImage(imageShip, getXCoord()-9,getYCoord(),null);
    }
	
}
