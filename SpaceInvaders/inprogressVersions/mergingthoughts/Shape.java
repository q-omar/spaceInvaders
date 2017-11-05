import java.awt.Graphics;

public abstract class Shape {
	
    private int xCoord = 0;
    private int yCoord = 0;
    private int lastXCoord = 0;
    private int lastYCoord = 0;
	private int hSpeed;
	private int vSpeed;
	private int width;
	private int height;

	
	public Shape(int newX, int newY, int newWidth, int newHeight) {
		xCoord = newX;
		yCoord = newY;
		width = newWidth;
		height = newHeight;
	}
	
    /************************
    method: getXCoord
    @return returns shape's horizontal position
    *************************/
	public int getXCoord() {
		return xCoord;
	}
	
    /************************
    method: getYCoord
    @return returns shape's vertical position
    *************************/
	public int getYCoord() {
		return yCoord;
	}
	
	public int getLastXCoord() {
		return lastXCoord;
	}
	
    /***************************
    method: getLastYCoord
    @return returns shape's previous vertical index position
    ****************************/
	public int getLastYCoord() {
		return lastYCoord;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
    /****************************
    method: getHSpeed
    @return amount of spaces that the player ship can move left or right
    ****************************/
	public int getHSpeed() {
		return hSpeed;
	}
	
	public int getVSpeed() {
		return vSpeed;
	}
	
    /************************
    method: setXCoord
            sets horizontal position
    *************************/
    public void setXCoord(int newX){
    	lastXCoord = xCoord;
    	lastYCoord = yCoord;
        xCoord = newX;
    }
    
    /************************
    method: setYCoord
            sets vertical position
    *************************/
    public void setYCoord(int newY){
    	lastXCoord = xCoord;
    	lastYCoord = yCoord;
        yCoord = newY;
    }
	
	public void setHSpeed(int newSpeed) {
		hSpeed = newSpeed;
	}
	
	public void setVSpeed(int newSpeed) {
		vSpeed = newSpeed;
	}

	/** 
	* An abstract method to draw a shape.
	* @param  the Graphics object
	*/
	public abstract void draw(Graphics g);
	

	
	
}