package model;

import java.awt.*;

/**
 * This class contains information about an Alien object and contains methods to move its location or remove it.
 */
public class Alien extends Shape{
    private boolean isAlive = true;
    
	Alien(Alien toCopy) {
		super(toCopy);
		setHSpeed(toCopy.getHSpeed());
		setVSpeed(toCopy.getVSpeed());
		isAlive = toCopy.isAlive;
	}
	
	/**This is a constructor for the Alien class that sets its coordinates, size and speed
	* @param hSpeed the horizontal speed of the alien
	* @param vSpeed the vertical speed of the alien
	* @param newSize the length and width of the shape
	*/
    Alien(int hSpeed, int vSpeed, int newSize) {
    	super(0, 5, newSize, newSize);
        setHSpeed(hSpeed);
        setVSpeed(vSpeed);
    }
    
    /**
    * This is a getter for the life status of an alien
    * @return boolean indicating whether or not alien is alive
    */
    public boolean isAlive() {
        return isAlive;
    }

    /** 
     * This method sets isAlive to false.
     */
    void destroyAlien() {
        isAlive = false;
    }

    /** 
    * This method checks if alien has reached 1 space before a given vertical boundary. If so, it returns true.
	* @param the vertical boundary to be checked (eg. the height of the screen)
    * @return the status of alien
    */
    boolean reachedEnd(int boundary) {
        boolean reachedEnd = false;
        if (getYCoord() >= boundary - 1){
            reachedEnd = true;
        }
        return reachedEnd;
    }
    
    /**This class moves the alien's horizontal index to the right by hSpeed number of spaces
    */
    void moveRight(){
        setXCoord(getXCoord()+getHSpeed());
    }
    
	/**This class moves the alien's vertical index down by vSpeed number of spaces.
    */
    void moveDown(){
    	setYCoord(getYCoord()+getVSpeed());
    }
    /**This class moves the alien's horizontal index to the left by hSpeed number of spaces
    */
    void moveLeft() {
    	setXCoord(getXCoord()-getHSpeed());
    }

    /** 
     * Draws the alien onto the screen.
     * Image source: 
     * https://yt3.ggpht.com/-daOPyvV7NVg/AAAAAAAAAAI/AAAAAAAAAAA/gwRYg1wXn9I/s900-c-k-no-mo-rj-c0xffffff/photo.jpg
	 * @param the Graphics object g
	*/
    public void draw(Graphics g) {
         g.setColor(Color.GREEN);
         g.fillOval(getXCoord(),getYCoord(),getWidth(),getHeight());

         Image imageAlien = new javax.swing.ImageIcon("a.gif").getImage(); //
         g.drawImage(imageAlien, getXCoord(),getYCoord(),null);
         
    }

}
