import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class playerShot{
   
    /** This class contains mechanics for when the player ship decides to shoot 
    @param shotFired used to determine if a shot should be displayed on the board or not 
    @param shotRow and lastShotRow control how shot moves upward and erases previous row location
    @param initialRow is used to reset the shot's position when refiring
    @param shotColumn column in which shot travels
    @param speed setting how fast shot should travel upward
    */

    boolean shotFired;

    int width;
    int length;

    int initialRow;
    int shotRow;
    int lastShotRow;
    int shotColumn;
    int speed;

    /**
    *  Constructor for the text version of the game.
    * @param  startingRow  sets the initial y-axis position of the shot
    * @param  newSpeed     sets the distance the shot should travel
    */
    public playerShot(int startingRow, int newSpeed) {
        initialRow = startingRow;
        shotRow = initialRow;
        lastShotRow = initialRow;
        speed = newSpeed;
    }

    /**
    *  Constructor for the GUI version of the game.
    * @param  startingRow  sets the initial y-axis position of the shot
    * @param  newSpeed     sets the distance the shot should travel
    * @param  newWidth     sets width of the bullet in pixels
    * @param  newLength    sets height of the bullet in pixels
    */
    public playerShot(int startingRow, int newSpeed, int newWidth, int newLength) {
        initialRow = startingRow;
        shotRow = startingRow;
        lastShotRow = shotRow;
        speed = newSpeed;
        width = newWidth;
        length = newLength;
    }
    
    /** 
    * Sets whether or not the bullet is currently active. Only one bullet can be fired at a time.
    * @param  shotStatus  new boolean value for shotFired
    */
    public void shotFired(boolean shotStatus){
        shotFired = shotStatus;
    }

    /**
    * Used to check if a bullet is currently active.
    * @return  shotFired  boolean status of the bullet; true when the bullet is on screen
    */
    public boolean getShotFired(){
        return shotFired;
    }

    /**
    * Returns the width of the bullet.
    * @return  width  the width of the shot in pixels
    */
    public int getWidth() {
        return width;
    }

    /**
    * Returns the length of the bullet.
    * @return  length  the length of the shot in pixels
    */
    public int getLength() {
        return length;
    }

    /**
    * Returns the y axis location/"row" of the bullet.
    * @return  shotRow  the row the bullet is in currently
    */
    public int getShotRow(){
        return shotRow;
    }

    /**
    * Returns the y axis location/"row" of the bullet before its last move.
    * Used in the text version to redraw the bullet.
    * @return  lastShotRow  the last row the bullet was in
    */
    public int getLastShotRow(){
        return lastShotRow;
    }

    /**
    * Returns the x axis location/"column" of the bullet.
    * @return  shotColumn  the column the bullet is in currently
    */
    public int getShotColumn(){
        return shotColumn;
    }

    /**
    * Sets the x axis location of the bullet to the passed parameter.
    * @param  column  the new horizontal location for the shot
    */
    public void setShotColumn(int column) {
        shotColumn = column;
    }


    /** method moveShot updates the shot's location as a number so that the
     * getter methods can be used to display them on the board. The location is changed
     * by the speed of the shot.
     */
    public void moveShot() {
        lastShotRow = shotRow;
        shotRow -= speed;
    }
    
    /** the inBounds method checks if the bullet goes past the top of the screen, 
     *  shotFired being set to false will stop the board from attemping to draw it.
     *  The next time a bullet is fired, shotRow will be reset.
     */
    public void inBounds() {
        if (shotRow + length < 0) {
            shotFired = false;
            shotRow = initialRow;
        }
    }
    /**
    * This method is for the GUI version. It checks collisions of the top left/right corners of the bullet 
    * with a circular object and returns true if they overlap.
    *
    * @param  targetX       the x-coordinate for the top left point of the circle
    * @param  targetY       the y-coordinate for the top left point of the circle
    * @param  targetRadius  the radius of the circle
    * @return hit           whether or not a collision was detected
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

     /** 
      * This method is for the text version. Checks if the bullet "collides" with a given target.
      * @param   targetRow  the array row the target is in
      * @param   targetCol  the array column the target is in
      * @return  hit        whether or not a the bullet "hit" the target 
     */
    
    public boolean checkHit(int targetRow, int targetCol) {
        boolean hit = false;
        if (targetCol == shotColumn && shotRow <= targetRow && shotRow >= targetRow - speed) {
            hit = true;
            System.out.println("A hit!");
        }
        return hit;
    }

    /**
    *  Draws the shot onto the GUI screen.
    *  @param  g  the graphics object
    */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(shotColumn,shotRow, width, length);
    }

}