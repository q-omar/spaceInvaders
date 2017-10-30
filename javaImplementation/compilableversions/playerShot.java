import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class playerShot{
   
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
    int shotColumn = 195;
    int speed;

    public playerShot(int startingRow, int newSpeed) { // This is for the text version, where width and length will be 0 by default
        initialRow = startingRow;
        shotRow = initialRow;
        lastShotRow = initialRow;
        speed = newSpeed;
    }

    public playerShot(int startingRow, int newSpeed, int newWidth, int newLength) { // This is for the GUI version
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

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

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
     * is used to display it on the board  
     */

    public int getShotColumn(){
        return shotColumn;
    }

    public void setShotColumn(int column) {
        shotColumn = column;
    }


    /** method moveShot actually updating the shots location as a number so that the
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


     /** the checkHit method uses
      * @param targetRow,targetCol which are passed from InvadersGame class, being
      * the aliens current row and columns to check if there is a match with the shots
      * location at which point it returns either false or true  
     */
    
    public boolean checkHit(int targetRow, int targetCol) {
        boolean hit = false;
        if (targetCol == shotColumn && shotRow <= targetRow && shotRow >= targetRow - speed) {
            hit = true;
            System.out.println("A hit!");
        }
        return hit;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(shotColumn,shotRow, width, length);
    }

}