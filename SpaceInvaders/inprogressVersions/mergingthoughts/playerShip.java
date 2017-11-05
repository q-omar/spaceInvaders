
import java.awt.Graphics;
import java.awt.Color;

public class playerShip{
/*************************************************
This class holds the method mechanics of the player ship
*************************************************/  

    private int location = 30;
    private int lastLocation = location;
    private int speed = 3;

    /*****************************************************************
    * Constructor that centers the starting location of the ship based on the length of the screen and lets
    * one set its speed.
    *****************************************************************/
    public playerShip(int screenLength, int newSpeed) {
        location = screenLength/2;
        speed = newSpeed;
    }

    /****************************
    method: getLocation
    @return current horizontal index of the player ship
    ****************************/
    public int getLocation() {
        return location;
    }

    /****************************
    method: getLastLocation
    @return previous horizontal index of the player ship
    ****************************/
    public int getLastLocation() {
        return lastLocation;
    }

    /****************************
    method: setLocation
            sets new horizontal index position of the ship
    @param  index position for player ship to be given
    ****************************/
    public void setLocation(int newLocation) {
        lastLocation = location;
        location = newLocation;
    }
    
    /****************************
    method: getSpeed
    @return amount of characters that the player ship can move left or right
    ****************************/
    public int getSpeed() {
        return speed;
    }
    
    /****************************
    method: getLocation
            checks if horizontal index position of player ship is out of bounds
            on the board array's horizontal parameter
    ****************************/
    public void inBounds(int boardWidth) { 
        if (location >= boardWidth) {
            location = boardWidth - speed;
        } else if (location < speed) {
            location = 0;
        }
    }
    
    // Condensed ship movement to 1 method
    
    public void move(String direction) {
    	lastLocation = location;
    	if (direction.equals("A")) { // If moving left, subtract speed
    		location -= speed;
    	} else if (direction.equals("D")) {
    		location += speed;
    	}
    }
    
	/**********************************
	method: draw
			draws the ship
			@param Graphics object,g
	*************************************/
	public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(location-10,440, 20, 20);
    }
}
