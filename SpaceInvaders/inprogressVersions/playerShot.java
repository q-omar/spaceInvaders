import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class playerShot extends Shape{
   
    /** This class contains mechanics for when the player ship decides to shoot 
    @param shotFired used when a shot should be displayed on the board or not 
    @param shotRow,lastShotRow control how shot moves upward and erases previous row location
    @param shotColumn column in which shot travelsmn
    @param speed setting how fast shot should travel upward in rows on the board
    */

    boolean shotFired;

    int width;
    int length;

    int initialRow;
    int shotRow;
    int lastShotRow;
    int shotColumn;
    int speed;
	
	
	public playerShot(Point newTopLeftPoint,int newLength, int newWidth) {
        super(newTopLeftPoint, newLength, newWidth);
    }
	
	public playerShot(Point newTopLeftPoint) {
        super(newTopLeftPoint);
    }
	
	

    public void shotFired(boolean shotStatus){
        shotFired = shotStatus;
    }
	/** This method returns the width of the shot set by the constructor.
	* @return width of shot
	*/
    public int getWidth() {
        return width;
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
