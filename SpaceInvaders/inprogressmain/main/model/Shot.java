package model;

import java.awt.Graphics;
import java.awt.Color;

public class Shot extends Shape {

	private boolean shotFired = false;

	/**
	* This method is a constructor for Shot class, used in the text version, where width and length are 0 by default.
	* @param startingRow the row where the shot begins when it is fired 
	* @param newSpeed how many spaces the shot moves up each time the game is redrawn. 
	*/
    public Shot(int startingRow, int newSpeed) {
        super(0,startingRow,0,0);
		setVSpeed(newSpeed);
		
    }
	
	/**This is a constructor for Shot class, used in the GUI version. where width and length need to be specified
 	* @param startingRow is the row in which the ship is present at the tie of the shot being fired. 
	* @param newSpeed is the how many spaces the alien moves up each time the board is redrawn. 
	* @param newWidth and newLength are the width and length of the shot.
	*/
    public Shot(int startingRow, int newSpeed, int newWidth, int newLength, int screenHeight) {
		super(0,startingRow, newWidth, newLength);
		setVSpeed(newSpeed);
    }
	
	/**
     * Returns whether or not a shot has been fired and is currently active
     * @return  the status of the shot
     */
    public boolean getShotFired(){
		return shotFired;
	}
	
	public void shotFired(boolean shotStatus){
		shotFired= shotStatus;
	}
    
	/** 
	*  Called when the user attempts to fire a new bullet. A new bullet will only be fired if one is not
	*  already active.
	*  @param  shipLocation  the x-coordinate of the ship, which will set the location of the new shot
	*/
    public void tryShot(int shipLocation) {
    	if (!shotFired && shipLocation >= 0) {
    		shotFired = true;
    		setXCoord(shipLocation);
    	}
    }
	
	public void newAlienShot(int newX, int newY) {
    	setXCoord(newX);
    	setYCoord(newY);
    	shotFired = true;
    }
	
	public boolean alienShotShip(int shipXCoord, int shipYCoord){
		boolean hit = false;
		if (getXCoord() >= shipXCoord-2 && getXCoord() <= shipXCoord+2){
			if (getYCoord() >= shipYCoord - 2){
				hit = true;
			}
		}
		return hit;
	}
    
     /** 
     * Updates the shot's location by changing its y coordinate by its vertical speed.
     * If movement causes the shot to go out of the game boundaries, the shot will 
     * be reset so a new shot can be fired.
     */
    public void moveShot() {
		setYCoord(getYCoord()-getVSpeed());

		if (getYCoord()+ getHeight() < 1) {  
            shotFired(false);
            resetY();
        }
    }
    
    /** the inBounds method checks if the bullet goes past the top of the screen, 
     *  fired being set to false will stop the board from attemping to draw it.
     *  The next time a bullet is fired, shotRow will be reset.
     */
	public void inBounds(int height) {
        if (getYCoord() + getVSpeed() >= height) { 
            shotFired = false;
            resetY();
        }
    }

    /**
    * This method is for the GUI version. It checks collisions of the bullet with a circular target
    * and returns true if the top left or top right point of the bullet overlaps.
    * 
    * @param targetX  the x coordinate of the top left point of the circle
    * @param targetY  the y coordinate of the top left point of the circle
    * @param targetDiameter  the diameter of the target circle
    * @return whether or not the target was hit
    */
    public boolean checkHit(int targetX, int targetY, int targetDiameter) {
        boolean hit = false;
        int xToCheck;
        targetX += targetDiameter/2; // Set x and y to center of target circle
        targetY += targetDiameter/2;

        if (getXCoord() >= targetX - 0.5 * getWidth()) {
            xToCheck = getXCoord(); // Checks top left point of bullet
        } else {
            xToCheck = getXCoord() + getWidth(); // Checks top right point of bullet
        }

        // From Prof. Verwaal's code for the distance method in the Point class used in Team Assignment 4
        int xdiff = xToCheck - targetX;
        int ydiff = getYCoord() - targetY;
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        if (distance <= (targetDiameter/2)) {
            hit = true;
            shotFired = false;
            resetY();
        }
        return hit;
    }

     /** the checkHit method uses
      * @param targetRow,targetCol which are passed from InvadersGame class, being
      * the aliens current row and columns to check if there is a match with the shots
      * location at which point it returns either false or true  
	  * @return whether a shot hits an alien
     */
    
    public boolean checkTextHit(int targetRow, int targetCol, int lastCol) {
        boolean hit = false;
        int speed = getVSpeed();
        
        if (speed < 0) {  // Temp fix for the change to negative speeds
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
    * This method is for the GUI version. It checks collisions of the bullet with a rectangular target
    * and returns true if the top left or top right point of the bullet overlaps.
    * 
    * @param targetX  the x coordinate of the top left point of the rectangle
    * @param targetY  the y coordinate of the top left point of the rectangle
    * @param width    the width of the rectangle in pixels
    * @param height   the height of the rectangle in pixels
    * @return whether or not the target was hit
    */
	public boolean checkHitRectangle(int targetX, int targetY, int width, int height) {
    	boolean hit = false;
    	int shotX = getXCoord();
    	int shotY = getYCoord();
    	int shotWidth = getWidth();
    	int shotHeight = getHeight();
    	
    	int targetXBound = targetX + width;
    	int targetYBound = targetY + height;
    	if (shotFired){
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
    	} 
    	
    	return hit;
    } 
	
	// trying to merge both GUI and TEXT barrier hit detection
	public boolean checkBarrierHit(Barrier barrier, int bheight, int bwidth){
		boolean hit = false;
		// I tried keeping all the practices from the other versions..
		if (shotFired){
			// where the shot is , vs barrierheight and its y coordinate (looking at heights)
			if ( getYCoord() >= bheight -barrier.getYCoord() ){
				// where shot is in the x and the width of the barrier
				if (getXCoord() <= bwidth - barrier.getXCoord() && getXCoord() >= bwidth - barrier.getXCoord()){
					
					hit = true;
					shotFired = false;
				}
			}

		}
		if (hit){
			System.out.println("Barrier hit!");
		}
		return hit;
	}
	
	
	/** the draw method sets the color and dimensions of the shot
	 * @param the Graphics object g
	*/
    public void draw(Graphics g) {
    	if(shotFired) {
            g.setColor(Color.RED);
            g.fillRect(getXCoord(),getYCoord(), getWidth(), getHeight());
    	}
    }

}

