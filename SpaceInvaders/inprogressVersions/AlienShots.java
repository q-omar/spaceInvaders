import java.util.Random;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class AlienShots extends AlienArray{
    
    private boolean shotFired = false;
    private int width = 5;
    private int length = 10;
    private int reset = 0;
    private int alienShotRow;
    private int alienLastShotRow;
    private int alienShotCol;
    private int speed = 10;

    

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

    public int getAlienShotRow(){
        return alienShotRow;
    }


    /** the getter and setter methods for shot column are similar where first 
     * @param column in line 52 is the ships current column and the getter method
     * is used to display it on the board . 
	 * @return the current column of the ship. 
     */

    public int getAlienShotCol(){
        return alienShotCol;
    }

    public int getAlienLastShotRow(){
        return alienLastShotRow;
    }
    


    /** method moveShot actually updates the shots location as a number so that the
     * getter methods can be used to display them on the board and speed is how
     * many rows up it moves 
     */
    public void moveShot() {
        alienLastShotRow = alienShotRow;
        alienShotRow += speed;
    }

    
    /** the inBounds method checks if the bullet goes past the top of the screen, 
     *  fired being set to false will stop the board from attemping to draw it.
     *  The next time a bullet is fired, shotRow will be reset.
     */

    public void inBounds() {
        if (alienShotRow + length >600) { //hardcoded screenlength for now 
            shotFired = false;
            alienShotRow = reset;
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;//this includes the minimum into the random selection 
        return randomNum;
    }
    
    public void shotGeneration(){
        aliensMovement(400); //had to add alien movement here for reasons 
        int rand1 = randInt(0, getRowsAliens()-1); //generate a random number for column/row to fire 
        int rand2 = randInt(0, getNumAliens()-1);

        if (!shotFired){
            int randomNum = randInt(0,0); //generate a 10% chance for example for any one alien to fire 
            if (randomNum==0){
                if (getAliens()[rand1][rand2].isAlive()){ //check if that alien is alive 
                    shotFired = true;
                    alienShotRow = getAliens()[rand1][rand2].getYCoord() + getAliens()[0][0].getWidth(); //if it is, alien shot is generated at its location 
                    alienShotCol = getAliens()[rand1][rand2].getXCoord(); 
                }

            }
        }
    }


    
    public boolean checkAlienHit(int targetX, int targetY, int targetLength) {
        boolean hit = false;
        int xCoord;
        targetX += targetLength; // Set x and y to center of target circle
        targetY += targetLength;

        if (alienShotCol >= targetX - 0.5 * width) {
            xCoord = alienShotCol; // Checks top left point of bullet
        } else {
            xCoord = alienShotCol + width; // Checks top right point of bullet
        }

        // From Prof. Verwaal's code for the distance method in the Point class used in Team Assignment 4
        int xdiff = xCoord - targetX;
        int ydiff = alienShotRow - targetY;
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        if (distance <= targetLength) {
            hit = true;
            shotFired = false;
            alienShotRow = reset;
        }

        return hit;
    }

     /** the checkHit method uses
      * @param targetRow,targetCol which are passed from InvadersGame class, being
      * the aliens current row and columns to check if there is a match with the shots
      * location at which point it returns either false or true  
	  * @return whether a shot hits an alien
     */
    /** 
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
    public void drawAlienShot(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(alienShotCol,alienShotRow, width, length);
    }

}
            
