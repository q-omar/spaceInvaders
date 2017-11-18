//this class does not compile, just some thoughts


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class Shots extends Shape{
    
    private boolean playerShotFired;
    private boolean alienShotFired;

    public Shots(int xLoc, int yLoc, int width, int height, int speed){ //constructor for GUI 
        super(xLoc, yLoc, width, height);
        setVSpeed(speed);
    }

    public Shots(int xLoc, int yLoc, int speed){ //constructor for text, one might need to be specified in SHape class? 
        super(xLoc, yLoc, speed);
    }

    public void setPlayerShot(boolean shotStatus){
        playerShotFired = shotStatus;
    }

    public void setAlienShot(boolean shotStatus){
        alienShotFired = shotStatus;
    }

	/** This method returns the width of the shot set by the constructor.
	* @return width of shot
    */
    
    public boolean getAlienShot(){
        return alienShotFired;
    }

    public boolean getPlayerShot(){
        return playerShotFired;
    }

    /** method moveShot actually updates the shots location as a number so that the
     * getter methods can be used to display them on the board and speed is how
     * many rows up it moves 
     */
    public void moveAlienShot() {
        setYCoord(getYCoord()+getVSpeed());
    }

    public void movePlayerShot(){
        setYCoord(getYCoord()-getVSpeed());
    }


    
    /** the inBounds method checks if the bullet goes past the top of the screen, 
     *  fired being set to false will stop the board from attemping to draw it.
     *  The next time a bullet is fired, shotRow will be reset.
     */

    public void inBounds() {
        if (getYCoord() + getHeight() > 600) { //hardcoded screenlength for now 
            alienShotFired = false;
            setYCoord(0);
        }
        if (getYCoord() + getHeight() < 0) { //hardcoded screenlength for now 
            playerShotFired = false;
            setYCoord(440);
        }
    }

    //alien hitdetection might need a tiny bit of tweaking, got a bit funky after extending to shape
    //i tihnk it has to do with the alien shot being a rectangle? idk 

    //hitdetection method should basically work for all kinds of hit detection, i think
    //this could probably be used for barrier hit detection as well but because barrier hit detection
    //might need some other stuff, that other stuff could go in other methods? 
    
    public boolean checkGuiHit(int targetX, int targetY, int targetLength) {
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
        }

        return hit;
    }



    public boolean checkTextHit(){
        boolean hit = false;
        if( getYCoord() <= targetRow && getYCoord()>=targetRow - getHSpeed()){
            if(targetCol == lastCol && getXCoord() ==targetCol){
                hit = true;
            } else if ( targetCol >= getXCoord() && getXCoord() > lastCol){
                hit = true;
            } else if (targetCol <= getXCoord() && getXCoord() < lastCol){
                hit = true;
            }
        }
        if (hit){
            System.out.println("A hit!");
            //setYCoord(initialY);
            shotFired(false);
        }
        return hit;
    }


   
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getXCoord(),getYCoord(), getWidth(), getHeight());
    }


  
}
            
