import java.awt.Graphics;
import java.awt.Color;

/**
 * This class describes a shot/bullet object which is a subclass of shape. It contains methods to move and reset
 * the shot, and detect its collision with other shapes.
 */
public class PlayerShot extends Shape {

	private boolean shotFired = false;

	/**
	* This method is a constructor for PlayerShot class, used in the text version, where width and length are 0 by default.
	* @param startingRow the row where the shot begins when it is fired 
	* @param newSpeed how many spaces the shot moves up each time the game is redrawn. 
	*/
    public PlayerShot(int startingRow, int newSpeed) {
        super(0,startingRow,0,0);
		setVSpeed(newSpeed);
    }
	
	/**
	* This is a constructor for PlayerShot class, used in the GUI version. where width and length need to be specified
 	* @param startingY the y coordinate where the shot starts at when it is fired
	* @param newSpeed how many spaces the shot moves up each time the game is redrawn. 
	* @param newWidth the width of the shot.
	* @param newHeight the length of the shot
	*/
    public PlayerShot(int startingY, int newSpeed, int newWidth, int newHeight) {
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
    public void tryShot(int shipLocation) {
    	if (!shotFired && shipLocation >= 0) {
    		shotFired = true;
    		setXCoord(shipLocation);
    	}
    }
    
    /** 
     * Updates the shot's location by changing its y coordinate by its vertical speed.
     * If movement causes the shot to go out of the game boundaries, the shot will 
     * be reset so a new shot can be fired.
     */
    public void moveShot() {
		setYCoord(getYCoord()-getVSpeed());

		if (getYCoord()+ getHeight() < 0) {
            shotFired = false;
            resetY();
        }
    }

    /**
    * This method is for the GUI version. It checks collisions of the bullet with a circular target
    * and returns true if they overlap.
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

    /** 
     * This method checks collisions with the shot in the text-based version of the game,
     * based on the direction the target is moving.
     * 
     * @param  targetRow  the row the target is in
     * @param  targetCol  the column the target is currently in
     * @param  lastCol  the column the target was in previously
	 * @return whether or not a hit was detected
     */
    public boolean checkTextHit(int targetRow, int targetCol, int lastCol) {
        boolean hit = false;
		if(getYCoord() <= targetRow && getYCoord()> targetRow - getVSpeed()){
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
	 * Draws the shot as a rectangle on the screen.
	 * @param the Graphics object g
	*/
    public void draw(Graphics g) {
    	if(shotFired) {
            g.setColor(Color.RED);
            g.fillRect(getXCoord(),getYCoord(), getWidth(), getHeight());
    	}
    }

}












