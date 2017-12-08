package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;


/**
* This class holds the method mechanics of the player ship
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
    
	PlayerShip(PlayerShip toCopy) {
		super(toCopy.getXCoord(), toCopy.getYCoord(), toCopy.getWidth(), toCopy.getHeight(),
				toCopy.getLastXCoord(), toCopy.getLastYCoord());
		setHSpeed(toCopy.getHSpeed());
	}
    
    /**This method checks if horizontal index position of player ship is out of bounds
    * on the board array's horizontal parameter
    */
    void inBounds(int boardWidth) {
        if (getXCoord() >= boardWidth) {
            setXCoord(boardWidth - getHSpeed());
        } else if (getXCoord() < getHSpeed()) {
            setXCoord(0);
        }
    }
    
    /** This method moves the ship left or right horizontally depending on the string input by the player
	* @param is the string input that decides which way the ship moves
	*/
    
    void move(String direction) {
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
        g.fillRect(getXCoord(),getYCoord(), getWidth(), getHeight());
        Image imageShip = new javax.swing.ImageIcon("ship.jpg").getImage();
        g.drawImage(imageShip, getXCoord(),getYCoord(),null);
    }
	
}
