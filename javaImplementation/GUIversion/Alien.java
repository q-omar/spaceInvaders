import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class Alien{
/*************************************************
This class holds the method mechanics of the alien ship
*************************************************/
    
    private int alienYcoord;
    private int lastAlienY = alienYcoord;
    private int alienXcoord = 158; 
    private int lastAlienX = alienXcoord;
    private int horizontalSpeed = 3; 
    private int verticalSpeed = 3;
    private boolean isAlive = true;

    private int radius = 20;
    
    /************************
    method: setAlienX
            sets alien horizontal index position
    *************************/
    public void setAlienX(int xCoord){
        alienXcoord= xCoord;
    }
    
    /************************
    method: setAlienY
            sets alien vertical index position
    *************************/
    public void setAlienY( int yCoord){
        alienYcoord= yCoord;
    }
    
    
    /************************
    method: getAlienY
    @return returns alien's vertical index position
    *************************/
    public int getAlienY() {
        return alienYcoord;
    }
    
    /**************************
    method: getAlienX
    @return returns alien's horizontal index position
    **************************/
    public int getAlienX(){
        return alienXcoord;
    }
    
    /***************************
    method: getLastAlienY
    @return returns alien's previous vertical index position
    ****************************/
    public int getLastAlienY(){
        return lastAlienY;
    }
    
    /*****************************
    method: getLastAlienX
    @return returns alien's previous horizontal index position
    ******************************/
    public int getLastAlienX(){
        return lastAlienX;
    }

    /*****************************
    method: getSize
    @return diameter of the alien
    ******************************/
    public int getRadius(){
        return radius;
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
    public boolean inBounds(int boardHeight) {
        boolean alienEnd = false;
        if (alienYcoord >= boardHeight - 1){
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
        lastAlienY = alienYcoord;
        lastAlienX = alienXcoord;
        alienXcoord += horizontalSpeed;
    }
    
    public void moveDown(){
        lastAlienY = alienYcoord;
        lastAlienX = alienXcoord;
        alienYcoord += verticalSpeed;
    }
    /*********************************************************************************************************
    method: moveLeft
            moves the alien's horizontal index to the left by horizontal speed number of spaces, checking   
            if it reaches or overreaches the lefthand boundary of the board, keeping it in the index before
            the first index of the width of board if so, and moving it down vertically by verticalSpeed number
            of spaces
    *********************************************************************************************************/
    public void moveLeft() {
        lastAlienX = alienXcoord;
        lastAlienY = alienYcoord;
        alienXcoord -= horizontalSpeed;
    }

    /**
    * Draws the alien as a circle onto the screen.
    * @param the Graphics object  
    */
    public void draw(Graphics g) {
         g.setColor(Color.GREEN);
         g.fillOval(alienXcoord,alienYcoord,radius*2,radius*2);

    }

}