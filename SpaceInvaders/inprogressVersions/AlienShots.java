import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class AlienShots extends Shape{
	
    private boolean shotFired = false;
	int lastShotRow = 0;
	
	//barrier stuff must be moved to likely Barrier class
	int barrier1HP = 3;
	int barrier2HP = 3;
	int barrier3HP = 3;

    public AlienShots(int xLoc, int yLoc, int width, int height, int speed){
        super(xLoc, yLoc, width, height);
        setVSpeed(speed);
    }
	
	public int getBarrier1HP(){
		return barrier1HP;
	}
	
	public int getBarrier2HP(){
		return barrier2HP;
	}
	
	public int getBarrier3HP(){
		return barrier3HP;
	}
	
	public void updateBarrier1(){
		barrier1HP--;
	}
	
	public void updateBarrier2(){
		barrier2HP--;
	}
	
	public void updateBarrier3(){
		barrier3HP--;
	}
	
	public int getLastShotRow(){
		return lastShotRow;
	}
	
    public void shotFired(boolean shotStatus){
        shotFired = shotStatus;
    }

	/** This method returns the width of the shot set by the constructor.
	* @return width of shot
    */
    
    public boolean getShotFired(){
        return shotFired;
    }

    /** method moveShot actually updates the shots location as a number so that the
     * getter methods can be used to display them on the board and speed is how
     * many rows up it moves 
     */
    public void moveShot() {
		lastShotRow = getYCoord();
        setYCoord(getYCoord()+getVSpeed());
    }

    
    /** the inBounds method checks if the bullet goes past the top of the screen, 
     *  fired being set to false will stop the board from attemping to draw it.
     *  The next time a bullet is fired, shotRow will be reset.
     */

    public void inBounds() {
        if (getYCoord() + getHeight() > 600) { //hardcoded screenlength for now 
            shotFired = false;
            setYCoord(0);
        }
    }
	//***************************************************
	public boolean alienShotShip(int shipXCoord, int shipYCoord){
		boolean hit = false;
		
		if (getXCoord() == shipXCoord){
			if (getYCoord() == shipYCoord){
				hit = true;
			}
		}
		
		return hit;
	}
	
    //alien hitdetection might need a tiny bit of tweaking, got a bit funky after extending to shape
    //i tihnk it has to do with the alien shot being a rectangle? idk 
    
    public boolean alienHitDetection(int targetX, int targetY, int targetLength) {
        boolean hit = false;
        int xCoord;
        targetX += targetLength; // Set x and y to center of target circle
        targetY += targetLength;

        if (getXCoord() >= targetX - 0.5 * getWidth()) {
            xCoord = getXCoord(); // Checks top left point of bullet
        } else {
            xCoord = getXCoord() + getWidth(); // Checks top right point of bullet
        }

        // From Prof. Verwaal's code for the distance method in the Point class used in Team Assignment 4
        int xdiff = xCoord - targetX;
        int ydiff = getYCoord() - targetY;
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        if (distance <= targetLength) {
            hit = true;
            shotFired(false);
            setYCoord(0);
        }

        return hit;
    }


   
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getXCoord(),getYCoord(), getWidth(), getHeight());
    }

}
