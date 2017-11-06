import java.awt.Graphics;
import java.awt.Color;

public class playerShot {
   
    /**This class contains mechanics for when the player ship decides to shoot 
    * @param shotFired used when a shot should be displayed on the board or not 
    * @param shotRow,lastShotRow control how shot moves upward and erases previous row location
    * @param shotColumn column in which shot travelsmn
    * @param speed setting how fast shot should travel upward in rows on the board
    */
    boolean shotFired;

    int initialY;
    
    int width;
    int length;

    int shotRow;
    int lastShotRow;
    int shotColumn;
    int speed;

	/**This method is a constructor for playerShot class, used in the text version, where width and length are 0 by default.
	* @param startingRow is the row in which the ship is present at the tie of the shot being fired. 
	* @param newSpeed is the how many spaces the alien moves up each time the board is redrawn. 
	*/
    public playerShot(int startingRow, int newSpeed) {
        initialY = startingRow;
        shotRow = initialY;
        lastShotRow = initialY;
        speed = newSpeed;
    }
	/**This is a constructor for playerShot class, used in the GUI version. where width and length need to be specified
 	* @param startingRow is the row in which the ship is present at the tie of the shot being fired. 
	* @param newSpeed is the how many spaces the alien moves up each time the board is redrawn. 
	* @param newWidth and newLength are the width and length of the shot.
	*/
    public playerShot(int startingRow, int newSpeed, int newWidth, int newLength) {

        initialY = startingRow;
        shotRow = startingRow;
        lastShotRow = shotRow;
        speed = newSpeed;
        width = newWidth;
        length = newLength;
    }
    
    /** This is a getter method for the row of the shot.
	* @return the row of the shot
    */
    public int getShotRow(){
        return shotRow;
    }
	
    /** This is a getter method for the last row of the shot.
	* @return the last row of the shot
    */
    public int getLastShotRow(){
        return lastShotRow;
    }

    /** the getter and setter methods for shot column are similar where first 
     * the ships current column and the getter method is used to display it on the board . 
	 * @return the current column of the ship. 
     */

    public int getShotColumn(){
        return shotColumn;
    }

    public void setShotColumn(int column) {
        shotColumn = column;
    }
    
	/** This is a setter method for shotFired that returns whether or not a shot was fired 
	*/
    public void shotFired(boolean shotStatus){
        shotFired = shotStatus;
    }
    
	/** This method resets the shot's position if a shot was fired and it is off screen
	*/
    public void resetShot(boolean shotStatus, int newX) {
    	shotFired = shotStatus;
    	shotColumn = newX;
    }
    
	/** This method is used with shotFired and 
	* @return true if a shot is fired, false otherwise.
	*/
	
    public boolean getShotFired(){
        return shotFired;
    }

    /** method moveShot actually updates the shots location as a number so that the
     * getter methods can be used to display them on the board and speed is how
     * many rows up it moves 
     */
    public void moveShot() {
        lastShotRow = shotRow;
        shotRow -= speed;
    }
    
    /** the inBounds method checks if the bullet goes past the top of the screen, 
     *  fired being set to false will stop the board from attemping to draw it.
     *  The next time a bullet is fired, shotRow will be reset.
     */

    public void inBounds() {
        if (shotRow + length < 0) {
            shotFired = false;
            shotRow = initialY;
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

        if (shotColumn >= targetX - 0.5 * width) {
            xToCheck = shotColumn; // Checks top left point of bullet
        } else {
            xToCheck = shotColumn + width; // Checks top right point of bullet
        }

        // From Prof. Verwaal's code for the distance method in the Point class used in Team Assignment 4
        int xdiff = xToCheck - targetX;
        int ydiff = shotRow - targetY;
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        if (distance <= targetWidth) {
            hit = true;
            shotFired = false;
            shotRow = initialY;
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
        if (targetCol == shotColumn && shotRow <= targetRow && shotRow >= targetRow - speed) {
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
        g.fillRect(shotColumn,shotRow, width, length);
    }

}
