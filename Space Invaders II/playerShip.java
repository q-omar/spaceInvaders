import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class playerShip{
/*************************************************
This class holds the method mechanics of the player ship
*************************************************/  

    private int location = 30;
    private int lastLocation = location;
    private int speed = 3;

    /**
    * Constructor that centers the starting location of the ship based on the length of the screen and lets
    * one set its speed.
    */
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
    
    /****************************
    method: getLocation
            moves the player ship speed characters left or right depending on user input
    @param left or right direction the player ship is to go
    ****************************/
    public void shipMovement(String direction) {
        lastLocation = location;

        if (direction.equals("A") && location > 0) { 
            location=location-speed;
            
        } else if (direction.equals("D")) { 
            location=location+speed;
        }

    }
	
	public void moveRight(){
		lastLocation = location;
		location += speed;
	}
	
	public void moveLeft(){
		lastLocation = location;
		location-= speed;
	}
	public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(location-10,440, 20, 20);
    }
}