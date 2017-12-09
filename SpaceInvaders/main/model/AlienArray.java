package model;


import java.awt.Graphics;

/** This class creates an array of aliens for the GUI and the text version of InvadersGame. 
* @param NUM_ALIENS is the number of aliens in each row.
* @param ROW_ALIENS is the number of rows of aliens. 
* @param moveRight is true if the aliens need to be moving right and is set to false if the ship needs to move left
* @param aliens is an array of alien objects
* @param leftBoundary,rightBoundary are used in the movement method where the aliens traverse to a certain point and then back
*/
public class AlienArray{

    private final int NUM_ALIENS;
    private final int ROW_ALIENS;
    private boolean moveRight = true;
    private final Alien[][] aliens;
    private int leftBoundary;
    private int rightBoundary;
    
    /**
     * A copy constructor that duplicates the given AlienArray.
     * @param toCopy the AlienArray to be copied
     */
	AlienArray(AlienArray toCopy){
		NUM_ALIENS = toCopy.NUM_ALIENS;
		ROW_ALIENS = toCopy.ROW_ALIENS;
		moveRight = toCopy.moveRight;
		aliens = new Alien[ROW_ALIENS][NUM_ALIENS];
		 for (int r = 0; r < ROW_ALIENS ; r++) {
            for (int c=0; c< NUM_ALIENS;c++){
            	aliens[r][c] = new Alien(toCopy.aliens[r][c]);
			}
		 }
		 leftBoundary = toCopy.leftBoundary;
		 rightBoundary = toCopy.rightBoundary;
	}

    /** 
    * A constructor for AlienArray that takes a String input. Sets up the aliens array depending on the version.
	* @param version determines whether the text or GUI version of aliens need to be drawn
    */
    AlienArray(String version){
        if (version.equals("GUI")) {
        	NUM_ALIENS = 8;
        	ROW_ALIENS = 3;
        	aliens = new Alien[ROW_ALIENS][NUM_ALIENS];
        	setGUIAliens();

        } else {
        	NUM_ALIENS = 5;
        	ROW_ALIENS = 2;
        	aliens = new Alien[ROW_ALIENS][NUM_ALIENS];
        	setTextAliens();  
        }
    }
    
    /** the getter methods for Alien, numAliens and rowsAliens return the respective values
	* @return aliens, numAliens and rowAliens to be used in logic  
    */
	public Alien[][] getAliens(){
        return aliens;
    }
	
    public int getNumAliens() {
    	return NUM_ALIENS;
    }

    public int getRowsAliens() {
    	return ROW_ALIENS;
    }

    /** 
     * Creates and sets the coordinates for aliens in the GUI version.
    */
    private void setGUIAliens(){
        for (int r=0; r<ROW_ALIENS ; r++) {
            for (int c=0; c<NUM_ALIENS; c++){
                aliens[r][c] = new Alien(10, 20, 40);
            }
        }
        
        int whereX=5;
        int whereY=0;
        
        for (int r=0; r<ROW_ALIENS;r++){
            for (int c=0; c<NUM_ALIENS;c++){
                whereX+=50;
                aliens[r][c].setXCoord(whereX);
                aliens[r][c].setYCoord(whereY);
            }
            whereX=5;
            whereY+=40;
        }
    }

    /** Sets the aliens in place for the text version of the game. 
     * Specifies the location of each alien, which are drawn in InvadersGame
    */
    private void setTextAliens(){
        for (int r = 0; r < ROW_ALIENS ; r++) {
            for (int c=0; c< NUM_ALIENS;c++){
            	aliens[r][c] = new Alien(3, 3, 0);
                aliens[r][c].setXCoord(4+2*c);
                if (r%2!=0){
                    aliens[r][c].setYCoord(r+1); 
                }else {
                	aliens[r][c].setYCoord(r);
                }
            }
        }
    }

    /** This method checks to see if there is an alien alive in the rightmost and leftmost
     * columns. If there is, that column becomes the boundary for the movement method and if there 
     * is not an alien alive, it moves to the next column over and that column then becomes the boundary.
    */ 
    private void checkBoundary(){
        leftBoundary = 0;
        rightBoundary = 0;
       
        for (int c = 0; c <NUM_ALIENS;c++){
            for (int r = 0; r < ROW_ALIENS ; r++) {
                if (aliens[r][c].isAlive()){
                    rightBoundary = c;
                }
            }
        }
        for (int c = NUM_ALIENS-1; c>-1;c--){
            for (int r = 0; r < ROW_ALIENS ; r++) {
                if (aliens[r][c].isAlive()){
                    leftBoundary = c;
                }
            }
        }  
    }

    /** This method handles all alien movement. 
    * If moveRight is true, it shifts all the aliens right every time the game updates. 
	* When the last alien reaches the end of the board, moveRight is set to false 
	* and the aliens move down and start going left. 
	* When they reach the left limit, moveRight is set to true again.
	* @param width of board
    */ 
    void aliensMovement(int width){
        checkBoundary();
        if (moveRight){ //move right until the boundary is reached, at which point shift down
            if ((aliens[0][rightBoundary].getXCoord()+aliens[0][rightBoundary].getHSpeed())>= width - 2 - aliens[0][0].getWidth()){ 
                for (int r = 0; r < ROW_ALIENS ; r++) {
                    for (int c = 0; c < NUM_ALIENS; c++){
                        aliens[r][c].moveDown();
                    } 
                }
                moveRight = false;
                return; 
            }

            for (int r = 0; r < ROW_ALIENS ; r++) { //if boundary isn't reached, move right
                for (int c = 0; c < NUM_ALIENS; c++){
                    aliens[r][c].moveRight();
                }
            }
        }
        
        else{ //if boundary is reached and moveright is flagged false, else block executes 
        	
            if (aliens[0][leftBoundary].getXCoord()<=3){ //similarly, move left until boundary is reached
                for (int r = 0; r < ROW_ALIENS ; r++) {
                    for (int c = 0; c < NUM_ALIENS; c++){
                        aliens[r][c].moveDown();
                    }
                }
                moveRight = true;
                return; 
            }
            for (int r = 0; r < ROW_ALIENS ; r++) {
                for (int c = 0; c < NUM_ALIENS; c++){
                    aliens[r][c].moveLeft();
                }
            }
        }
    } 

    /** This method draws the alien array.
	* @param Graphics object, g
    */
    public void drawAlienArray(Graphics g){
        for (int r=0; r<ROW_ALIENS;r++){
            for (int c=0; c<NUM_ALIENS;c++){
                if (aliens[r][c].isAlive()){
                    aliens[r][c].draw(g);
                }
            }
        }
    }
        
        
}
