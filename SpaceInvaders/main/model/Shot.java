package model;

import java.awt.Graphics;

import java.awt.Color;

/**
 * This class describes a shot/bullet object which is a subclass of shape. It contains methods to move and reset
 * the shot, and detect its collision with other shapes.
 */
public class Shot extends Shape {

	private boolean shotFired = false;
	
	/**
	 * A copy constructor for the Shot class.
	 * @param toCopy the Shot to be duplicated
	 */
	Shot(Shot toCopy){
		super(toCopy);
		shotFired = toCopy.shotFired;
	}

	/**
	* This method is a constructor for Shot class, used in the text version, where width and length are 0 by default.
	* @param startingRow the row where the shot begins when it is fired 
	* @param newSpeed how many spaces the shot moves up each time the game is redrawn. 
	*/
    Shot(int startingRow, int newSpeed) {
        super(0,startingRow,0,0);
		setVSpeed(newSpeed);
    }
	
	/**
	* This is a constructor for Shot class, used in the GUI version. where width and length need to be specified
 	* @param startingY the y coordinate where the shot starts at when it is fired
	* @param newSpeed how many spaces the shot moves up each time the game is redrawn. 
	* @param newWidth the width of the shot.
	* @param newHeight the length of the shot
	*/
    Shot(int startingY, int newSpeed, int newWidth, int newHeight) {
		super(0,startingY, newWidth, newHeight);
		setVSpeed(newSpeed);
    }
    
    /**
     * Returns whether or not a shot has been fired and is currently active
     * @return  the status of the shot
     */
    public boolean getShotFired(){
		return shotFired;
	}

	/**
	*  Called when the user attempts to fire a new bullet. A new bullet will only be fired if one is not
	*  already active.
	*  @param  shipLocation  the x-coordinate of the ship, which will set the location of the new shot
	*/
    void tryShot(int shipLocation) {
    	if (!shotFired && shipLocation >= 0) {
            shotFired = true;
    		setXCoord(shipLocation);
    	}
    }
    
    /**
     * Called to generate a new alien shot at the specified x and y coordinates.
     * @param newX the starting x-coordinate of the shot
     * @param newY the starting y-coordinate of the shot
     */
    void newAlienShot(int newX, int newY) {
    	setXCoord(newX);
    	setYCoord(newY);
    	shotFired = true;
    }
    
    /** 
     * Updates the shot's location by changing its y coordinate by its vertical speed.
     * If movement causes the shot to go out of the game boundaries, the shot will 
     * be reset so a new shot can be fired.
     */
    void moveShot() {
		setYCoord(getYCoord()+getVSpeed());

		if (getYCoord()+ getHeight() < 0) {
            shotFired = false;
            resetY();
        }
    }
    
    /**
    * This method is for the GUI version. It checks collisions of the bullet with a rectangular target
    * and returns true if the top left or top right point of the bullet overlaps.
    * 
    * @param targetX  the x coordinate of the top left point of the rectangle
    * @param targetY  the y coordinate of the top left point of the rectangle
    * @param width    the width of the rectangle in pixels
    * @param height   the height of the rectangle in pixels
    * @return whether or not the target was hit
    */
    boolean checkHitRectangle(int targetX, int targetY, int width, int height) {
    	boolean hit = false;
    	int shotX = getXCoord();
    	int shotY = getYCoord();
    	int shotWidth = getWidth();
		int targetXBound = targetX + width;
    	int targetYBound = targetY + height;
    	
    	// If the left side of the shot overlaps the target
    	if (shotX >= targetX && shotX <= targetXBound) {
    		// If the top left of the shot overlaps
    		if (shotY >= targetY && shotY <= targetYBound) {
    			hit = true;
    		}
    			    		
    	// Else if the right side of the shot overlaps
    	} else if (shotX + shotWidth >= targetX && shotX + shotWidth <= targetXBound) {
    		// If the top right of the shot overlaps
    		if (shotY >= targetY && shotY <= targetYBound) {
    			hit = true;
    		}
    	}
    	
    	if (hit) {
    		shotFired = false;
    		resetY();
    	}
    	
    	return hit;
    }  

	/**
     * This method checks collisions with the shot in the text-based version of the game,
     * based on the direction the target is moving.
     * 
     * @param  targetRow  the row the target is in
     * @param  targetCol  the column the target is currently in
     * @param  lastCol  the column the target was in previously
	 * @return whether or not a hit was detected
     */
    boolean checkTextHit(int targetRow, int targetCol, int lastCol) {
        boolean hit = false;
        int speed = getVSpeed();
        
        if (speed < 0) {
        	speed *= -1;
        }
        
		if(getYCoord() <= targetRow && getYCoord()> targetRow - speed){
			if(targetCol == lastCol && getXCoord() ==targetCol){
				hit = true;
			} else if ( targetCol >= getXCoord() && getXCoord() > lastCol){
				hit = true;
			} else if (targetCol <= getXCoord() && getXCoord() < lastCol){
				hit = true;
			}
		}
		if (hit){
			System.out.println("A hit!");
			shotFired = false;
			resetY();
		}
        return hit;
    }
    
    /**
     * Checks if the shot will move past a given vertical boundary. If so, the shot is reset and removed
     * from the screen.
     * @param height a vertical boundary that the shot cannot travel past
     */
    void inBounds(int height) {
        if (getYCoord() + getVSpeed() >= height) { 
            shotFired = false;
            resetY();
        }
    }

    /**
     * Draws the shot as a rectangle, if it is currently active.
     * @param g the Graphics object
     */
    public void draw(Graphics g) {
    	if(shotFired) {
            g.setColor(Color.RED);
            g.fillRect(getXCoord(),getYCoord(), getWidth(), getHeight());
    	}
    }

}












