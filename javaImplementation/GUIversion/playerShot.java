public class playerShot{
   
    /** This class contains mechanics for when the player ship decides to shoot 
    @param shotFired used when a shot should be displayed on the board or not 
    @param shotRow,lastShotRow control how shot moves upward and erases previous row location
    @param shotColumn column in which shot travelsmn
    @param speed setting how fast shot should travel upward in rows on the board
    */

    boolean shotFired;
    int shotRow;
    int lastShotRow = shotRow;
    int shotColumn;
    int speed = 5;
    
    /** shotFired method is used with getShotFired method where
     * @param shotStatus is passed from InvadersGame class to check if a shot 
     * is on the board or not
     */

    public void shotFired(boolean shotStatus){
        shotFired = shotStatus;
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

    public void setShotRow(int row) {
        lastShotRow = shotRow;
        shotRow = row;
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
     * shotFired being set to false will stop the board from attemping to draw it.
     *  The next time a bullet is fired, shotRow will be reset.
     */
    public void inBounds() { 
        if (shotRow < 0) {
            shotFired = false;
        }
    }


     /** the checkHit method uses
      * @param targetRow,targetCol which are passed from InvadersGame class, being
      * the aliens current row and columns to check if there is a match with the shots
      * location at which point it returns either false or true  
     */
    
    public boolean checkHit(int targetRow, int targetCol) {
        boolean hit = false;
        if (targetCol == shotColumn && shotRow <= targetRow && shotRow >= targetRow - 5) {
            hit = true;
            System.out.println("A hit!");
        }
        return hit;
    }

}
