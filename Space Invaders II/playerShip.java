import java.awt.*;

public class playerShip{
/*************************************************
This class holds the method mechanics of the player ship
*************************************************/  

    private int location = 30;
    private int lastLocation = location;
    private int speed = 40; //pixels

    /**
    * Constructor that centers the starting location of the ship based on the length of the screen and lets
    * one set its speed.
    */
    public playerShip(int screenLength, int newSpeed) {
        location = screenLength/2;
        speed = newSpeed;
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
	public int getLocation() {
        return location;
    }
	
	
	
    public void inBounds(int boardWidth) { 
        if (location >= boardWidth) {
            location = boardWidth - speed;
        } 
		else if (location < speed) {
            location += 25 ;
        }
    }
    
    /****************************
    method: 
            moves the player ship speed characters left or right depending on user input
    @param left or right direction the player ship is to go
    ****************************/
    public void moveRight() {
        location+=speed;
	}
	
	public void moveLeft(){
		location-=speed;
	}
	public void draw(Graphics g){
		int xcoordShip = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(xcoordShip-25,450,50,50);
	}
}
