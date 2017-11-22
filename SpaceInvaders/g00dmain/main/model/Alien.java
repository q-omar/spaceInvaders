package model;

import java.awt.Graphics;
import java.awt.Color;

public class Alien extends Shape{
	/**This class holds the method mechanics of the alien ship
	* @param isAlive is a boolean for the status of an alien
	*/

    private boolean isAlive = true;
	
	/**This is a constructor for the Alien class that calls the Shape constructor that sets the coordinates, size and speed
	* @param hSpeed, vSpeed are the horizontal and vertical of an alien
	* @param newSize is the length and width of the shape
	*/
    public Alien(int hSpeed, int vSpeed, int newSize) {
    	super(0, 5, newSize, newSize);
        setHSpeed(hSpeed);
        setVSpeed(vSpeed);
    }
    
    /**This is a getter for the status of an alien
    * @return returns bool indicating whether or not alien is alive
    */
    public boolean isAlive() {
        return isAlive;
    }

    /** This class sets isAlive to false
    */
    public void destroyAlien() {
        isAlive = false;
    }

    /** This class checks if alien ship reaches one row on board before the last row
    * sets alienEnd flag to true if so, "ending" the game
	* @param boundary is set as the window size and boundary of the aliens
    * @return the status of alien
    */
    public boolean reachedEnd(int boundary) {
        boolean alienEnd = false;
        if (getYCoord() >= boundary - 1){
            alienEnd = true;
        }
        return alienEnd;
    }
    
    /**This class moves the alien's horizontal index to the right by hSpeed number of spaces
    */
    public void moveRight(){ 
        setXCoord(getXCoord()+getHSpeed());
    }
    
	/**This class moves the alien's vertical index down by vSpeed number of spaces when the alien reaches either border of the board
    */
    public void moveDown(){
    	setYCoord(getYCoord()+getVSpeed());
    }
    /**This class moves the alien's horizontal index to the left by hSpeed number of spaces
    */
    public void moveLeft() {
    	setXCoord(getXCoord()-getHSpeed());
    }

    /** the draw method sets the color and dimensions of the shot
	 * @param the Graphics object g
	*/
    public void draw(Graphics g) {
         g.setColor(Color.GREEN);
         g.fillOval(getXCoord(),getYCoord(),getWidth(),getHeight());

    }

}
