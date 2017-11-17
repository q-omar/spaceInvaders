import java.awt.Graphics;

public abstract class Shape {
	/** This abstract class contains the size of the shape and the topLeft coordinate   
	* and methods to change and get values of the Shape. 
	* @param xCoord, yCoord are the  integer coordinates of player ship, shot and aliens. 
	* @param lastXCoord, lastYCoord are the previous  integer coordinates of the shapes, used in the text version. 
	* @param vSpeed,hSpeed are vertical and horizontal speeds of ship, shot and alien
	* @param width and length are used to draw ship, shot and alien for the GUI version.
	*/
    private int xCoord = 0;
    private int yCoord = 0;
    private int lastXCoord = 0;
    private int lastYCoord = 0;
	private int hSpeed;
	private int vSpeed;
	private int width;
	private int height;
	private boolean shotFired= false;

	 /** 
    * A constructor requiring the x and y coordinates of the shape and its desired size.
    * @param  newX and newY are the x and y coordinates of the shape.
	* @param  newWidth  is an integer value for the width of the shape.
	* @param  newHeight  is an integer value for the height of the shape.
    */
	public Shape(int newX, int newY, int newWidth, int newHeight) {
		xCoord = newX;
		yCoord = newY;
		width = newWidth;
		height = newHeight;
	}
	
    /* This is a getter method that returns the shape's horizontal position.
    * @return returns shape's horizontal position
    */
	public int getXCoord() {
		return xCoord;
	}
	
	/* This is a getter method that returns the shape's vertical position.
    * @return returns shape's vertical position
    */
	public int getYCoord() {
		return yCoord;
	}
	
	/* This is a getter method that returns the shape's  last horizontal position.
    * @return returns shape's last horizontal position
    */
	public int getLastXCoord() {
		return lastXCoord;
	}
	/* This is a getter method that returns the shape's  last vertical position.
    * @return returns shape's last vertical position
    */
	public int getLastYCoord() {
		return lastYCoord;
	}
	
	/* This is a getter method that returns the shape's width. 
    * @return returns shape's width
    */
	public int getWidth() {
		return width;
	}
	
	/* This is a getter method that returns the shape's height.
    * @return returns shape's height
    */
	public int getHeight() {
		return height;
	}
	
    /* This is a getter method that returns the shape's horizontal speed. 
    * @return returns shape's horizontal speed.
    */
	public int getHSpeed() {
		return hSpeed;
	}
	
	/* This is a getter method that returns the shape's vertical speed. 
    * @return returns shape's vertical speed.
    */
	public int getVSpeed() {
		return vSpeed;
	}
	
	public boolean getShotFired(){
		return shotFired;
	}
	
	public void shotFired(boolean shotStatus){
		shotFired= shotStatus;
	}
	
    /** This class sets horizontal position of the shape.
	* @param newX is the shape's horizontal position.
    */
    public void setXCoord(int newX){
    	if (newX != xCoord) {
        	lastXCoord = xCoord;
        	lastYCoord = yCoord;
            xCoord = newX;
    	}
    }
    
    /** This class sets vertical position of the shape.
	* @param newX is the shape's vertical position.
    */
    public void setYCoord(int newY){
    	lastXCoord = xCoord;
    	lastYCoord = yCoord;
        yCoord = newY;
    }
	
	/** This is a setter method for the shape's horizontal speed.
    */
	public void setHSpeed(int newSpeed) {
		hSpeed = newSpeed;
	}
	
	/** This is a setter method for the shape's vertical speed.
    */
	public void setVSpeed(int newSpeed) {
		vSpeed = newSpeed;
	}

	/** 
	* An abstract method to draw a shape.
	* @param  the Graphics object
	*/
	public abstract void draw(Graphics g);
	
}
