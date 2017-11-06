
import java.awt.Graphics;
import java.awt.Color;

public class Alien extends Shape{
/*************************************************
This class holds the method mechanics of the alien ship
*************************************************/

    private boolean isAlive = true;

    public Alien(int hSpeed, int vSpeed, int newSize) {
    	super(0, 0, newSize, newSize);
        setHSpeed(hSpeed);
        setVSpeed(vSpeed);
    }
    
    /*******************************************
    method: isAlive
    @return returns bool indicating whether alien is alive
    ********************************************/
    public boolean isAlive() {
        return isAlive;
    }

    /*************************
    method: destroyAlien
            sets isAlive to false
    **************************/
    public void destroyAlien() {
        isAlive = false;
    }

    /*************************
    method: inBounds
            checks if alien ship reaches one row on board before the last row
            sets alienEnd flag to true if so, "ending" the alien
    @return the status of alien
    **************************/
    public boolean inBounds(int boundary) {
        boolean alienEnd = false;
        if (getYCoord() >= boundary - 1){
            alienEnd = true;
        }
        return alienEnd;
    }
    
    /*********************************************************************************************************
    method: moveRight
            moves the alien's horizontal index to the right by horizontalSpeed number of spaces, checking
            if it reaches or overreaches the righthand boundary of the board, keeping it in the index before
            the last index of the width of board if so, and moving it down vertically by verticalSpeed number   
            of spaces
    @param boardWidth - index width of board
    *********************************************************************************************************/
    public void moveRight(){ 
        setXCoord(getXCoord()+getHSpeed());
    }
    
    public void moveDown(){
    	setYCoord(getYCoord()+getVSpeed());
    }
    /*********************************************************************************************************
    method: moveLeft
            moves the alien's horizontal index to the left by horizontal speed number of spaces, checking   
            if it reaches or overreaches the lefthand boundary of the board, keeping it in the index before
            the first index of the width of board if so, and moving it down vertically by verticalSpeed number
            of spaces
    *********************************************************************************************************/
    public void moveLeft() {
    	setXCoord(getXCoord()-getHSpeed());
    }

    /**
    * Draws the alien as a circle onto the screen.
    * @param the Graphics object  
    */
    public void draw(Graphics g) {
         g.setColor(Color.GREEN);
         g.fillOval(getXCoord(),getYCoord(),getWidth(),getHeight());

    }

}