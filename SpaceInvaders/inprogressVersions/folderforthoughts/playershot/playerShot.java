import java.awt.Graphics;
import java.awt.Color;

public class playerShot extends Shape {
	
	private int screenheight =0;
	/**This method is a constructor for playerShot class, used in the text version, where width and length are 0 by default.
	* @param startingRow is the row in which the ship is present at the tie of the shot being fired. 
	* @param newSpeed is the how many spaces the alien moves up each time the board is redrawn. 
	*/
	
    public playerShot(int startingRow, int newSpeed) {
        super(startingRow,0,0,0);
		setHSpeed(newSpeed);
		
    }
	
	/**This is a constructor for playerShot class, used in the GUI version. where width and length need to be specified
 	* @param startingRow is the row in which the ship is present at the tie of the shot being fired. 
	* @param newSpeed is the how many spaces the alien moves up each time the board is redrawn. 
	* @param newWidth and newLength are the width and length of the shot.
	*/
    public playerShot(int startingRow, int newSpeed, int newWidth, int newLength, int screenHeight) {
		super(startingRow,0,newWidth, newLength);
		setHSpeed(newSpeed);
		screenheight= screenHeight;
    }
    
	/** This method resets the shot's position if a shot was fired and it is off screen
	*/
    public void resetShot(boolean shotStatus, int newX) {
    	shotFired(shotStatus);
    	setXCoord(newX);
		setYCoord(screenheight);
    }
    
    /** method moveShot actually updates the shots location as a number so that the
     * getter methods can be used to display them on the board and speed is how
     * many rows up it moves 
     */
    public void moveShot() {
		setYCoord(getYCoord()-getHSpeed());
    }
    
    /** the inBounds method checks if the bullet goes past the top of the screen, 
     *  fired being set to false will stop the board from attemping to draw it.
     *  The next time a bullet is fired, shotRow will be reset.
     */

    public void inBounds() {
        if (getYCoord()+ getHeight() < 0) {
            shotFired(false);
            setYCoord(screenheight);
        }
    }
    /**
    * This method is for the GUI version. It checks collisions of the bullet with a circular alien
    * and returns true if they overlap.
    * @param the location of the alien in the AlienArray that is being check for a hit.
    * @return true if the alien was hit, false otherwise. 
    */

    public boolean checkHit(int targetX, int targetY, int targetWidth) {
        boolean hit = false;
        int xToCheck;
        targetX += targetWidth/2; // Set x and y to center of target circle
        targetY += targetWidth/2;

        if (getXCoord() >= targetX - 0.5 * getWidth()) {
            xToCheck = getXCoord(); // Checks top left point of bullet
        } else {
            xToCheck = getXCoord() + getWidth(); // Checks top right point of bullet
        }

        // From Prof. Verwaal's code for the distance method in the Point class used in Team Assignment 4
        int xdiff = xToCheck - targetX;
        int ydiff = getYCoord() - targetY;
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        if (distance <= targetWidth) {
            hit = true;
            shotFired(false);
            setYCoord(screenheight);
        }
        return hit;
    }

     /** the checkHit method uses
      * @param targetRow,targetCol which are passed from InvadersGame class, being
      * the aliens current row and columns to check if there is a match with the shots
      * location at which point it returns either false or true  
	  * @return whether a shot hits an alien
     */
    
    public boolean checkHit(int targetRow, int targetCol) {
        boolean hit = false;
        if (targetCol == getXCoord() && getYCoord() <= targetRow && getYCoord() >= targetRow - getHSpeed()) {
            hit = true;
            System.out.println("A hit!");
        }
        return hit;
    }
	/** the draw method sets the color and dimensions of the shot
	 * @param the Graphics object g
	*/
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getXCoord(),getYCoord(), getWidth(), getHeight());
    }

}