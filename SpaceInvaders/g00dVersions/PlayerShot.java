
import java.awt.Graphics;
import java.awt.Color;

public class PlayerShot{
   
    /** This class contains mechanics for when the player ship decides to shoot 
    @param shotFired used when a shot should be displayed on the board or not 
    @param shotRow,lastShotRow control how shot moves upward and erases previous row location
    @param shotColumn column in which shot travelsmn
    @param speed setting how fast shot should travel upward in rows on the board
    */

    boolean shotFired;
	int hitCount1=3;
	int hitCount2=3;
	int hitCount3=3;
    int width=0;
    int length;

    int initialRow;
    int shotRow;
    int lastShotRow;
    int shotColumn;
    int speed;
	
	/** This method is a constructor for PlayerShot class, used in the text version, where width and length are 0 by default.
	* @param startingRow is the row in which the ship is present at the tie of the shot being fired. 
	* @param newSpeed is the how many spaces the alien moves up each time the board is redrawn. 
	*/
    public PlayerShot(int startingRow, int newSpeed) {
        initialRow = startingRow;
        shotRow = initialRow;
        lastShotRow = initialRow;
        speed = newSpeed;
    }
	/** This method is a constructor for PlayerShot class, used in the GUI version. where width and length need to be specified
 	* @param startingRow is the row in which the ship is present at the tie of the shot being fired. 
	* @param newSpeed is the how many spaces the alien moves up each time the board is redrawn. 
	* @param newWidth and newLength are the width and length of the shot.
	*/
    public PlayerShot(int startingRow, int newSpeed, int newWidth, int newLength) {

        initialRow = startingRow;
        shotRow = startingRow;
        lastShotRow = shotRow;
        speed = newSpeed;
        width = newWidth;
        length = newLength;
    }
    
    /** shotFired method is used with getShotFired method where
     * @param shotStatus is passed from InvadersGame class to check if a shot 
     * is on the board or not
     */

    public void shotFired(boolean shotStatus){
        shotFired = shotStatus;
    }
	/** This method returns the width of the shot set by the constructor.
	* @return width of shot
	*/
    public int getWidth() {
        return width;
    }
	
	public void setHit1(int aHit1){
        hitCount1 -= aHit1;
		//System.out.print(hitCount1);
    }
	
	public void setHit2(int aHit2){
        hitCount2 -= aHit2;
    }
	
	public void setHit3(int aHit3){
        hitCount3 -= aHit3;
    }
	public int getHit1(){
		return hitCount1;
	}
	
	public int getHit2(){
		return hitCount2;
	}
	
	public int getHit3(){
		return hitCount3;
	}
	/** This method returns the length of the shot set by the constructor.
	* @return length of shot
	*/
    public int getLength() {
        return length;
    }
	/** This method is used with shotFired and 
	* @return true if a shot is fired, false otherwise.
	*/
	
    public boolean getShotFired(){
        return shotFired;
    }
   
    /** if there is no current shot on board, getShotRow, getLastShotRow and 
     * setShotRow are all used in conjunction.
     * @param row in line 55 sets location of row shot is fired from and then 
     * getter methods display and 'erase' the shot 
     */

    public int getShotRow(){
        return shotRow;
    }

    public int getLastShotRow(){
        return lastShotRow;
    }

    /** the getter and setter methods for shot column are similar where first 
     * @param column in line 52 is the ships current column and the getter method
     * is used to display it on the board . 
	 * @return the current column of the ship. 
     */

    public int getShotColumn(){
        return shotColumn;
    }

    public void setShotColumn(int column) {
        shotColumn = column;
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
            shotRow = initialRow;
        }
    }
    /**
    * This method is for the GUI version. It checks collisions of the bullet with a circular alien
    * and returns true if they overlap.
    * @param the location of the alien in the AlienArray that is being check for a hit.
    * @return true if the alien was hit, false otherwise. 
    */

    public boolean checkHit(int targetX, int targetY, int targetRadius) {
        boolean hit = false;
        int xCoord;
        targetX += targetRadius; // Set x and y to center of target circle
        targetY += targetRadius;

        if (shotColumn >= targetX - 0.5 * width) {
            xCoord = shotColumn; // Checks top left point of bullet
        } else {
            xCoord = shotColumn + width; // Checks top right point of bullet
        }

        // From Prof. Verwaal's code for the distance method in the Point class used in Team Assignment 4
        int xdiff = xCoord - targetX;
        int ydiff = shotRow - targetY;
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        if (distance <= targetRadius) {
            hit = true;
            shotFired = false;
            shotRow = initialRow;
        }

        return hit;
    }

	public void checkBarrierHit(int barrier1, int barrierHeight, int barrierWidth, int targetY){
		int targetRadius = 30;
		//Math.sqrt((barrierHeight*barrierHeight)+(barrierWidth*barrierWidth));
		barrier1 += targetRadius;
		targetY -=barrierHeight;
		int xCoord;
				
		if (shotColumn >= barrier1 - 0.5 * width) {
            xCoord = shotColumn; // Checks top left point of bullet
        } else {
            xCoord = shotColumn + width; // Checks top right point of bullet
        }
		
		int xdiff1 = xCoord - barrier1;
        int ydiff = shotRow - targetY;
        double distance1 = Math.sqrt(xdiff1 * xdiff1 + ydiff * ydiff);
        if (distance1 <= targetRadius) {
            shotRow = initialRow;
			shotFired = false;	
			shotRow = initialRow;
			if (barrier1 == 85){
				setHit1(1);
			}
			else if (barrier1 == 200){
				setHit2(1);
			}
			else if (barrier1 == 315){
				setHit3(1);
			}
        }
	}
		
	
     /** the checkHit method uses
      * @param targetRow,targetCol which are passed from InvadersGame class, being
      * the aliens current row and columns to check if there is a match with the shots
      * location at which point it returns either false or true  
	  * @return whether a shot hits an alien
     */
    
    public boolean checkTextHit(int targetRow, int targetCol, int lastCol) {
        boolean hit = false;
        
        if (shotRow <= targetRow && shotRow >= targetRow - speed) { // If shot is above the target
        	
        	if (targetCol == lastCol && shotColumn == targetCol) { // Target moving down
                hit = true;
        	} else if (targetCol >= shotColumn && shotColumn > lastCol) { // If target moving right
        		hit = true;
        	} else if (targetCol <= shotColumn && shotColumn < lastCol) { // Target moving left
        		hit = true;
        	}
        }
        
        if (hit == true) {
            System.out.println("A hit!");
        	shotRow = initialRow;
        	shotFired = false;
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
	
	public void resetShot(boolean shotStatus, int newX) {
    	shotFired = shotStatus;
    	shotColumn = newX;
	}
}
