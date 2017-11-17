import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;


public class AlienArray{
    
    /** This class creates an array of aliens for the GUI and the text version of InvadersGame. 
	* @param numAliens is the number of aliens in each row.
	* @param rowsAliens is the number of rows of aliens. 
	* @param moveRight is true if the aliens need to be moving right and is set to false if the ship needs to move left
    */
    
    private int numAliens; 
    private int rowsAliens;
    private boolean moveRight = true;
    Alien[][] aliens = new Alien[rowsAliens][numAliens];
    private int leftBoundary;
    private int rightBoundary;

    /** A constructor for AlienArray that takes a String input.
	* @param determines whether the text ot GUI version of aliens need to be drawn. 
	* This is needed because the text and GUI versions need different methods to set the aliens.
    */
    
    public AlienArray(String version){

        if (version.equals("GUI")) {
        	numAliens = 6;
        	rowsAliens = 3;
        	aliens = new Alien[rowsAliens][numAliens];
        	createAlienArrays();
        	setGUIaliens();

        } else {
        	numAliens = 5;
        	rowsAliens = 3;
        	aliens = new Alien[rowsAliens][numAliens];
        	createAlienArrays();
        	setAliens();  
        }
        
    }
    
    /** the getter methods for numAliens and rowsAliens return the respective values
	* @return numAliens and rowAliens. 
    */
    
    public int getNumAliens() {
    	return numAliens;
    }

    public int getRowsAliens() {
    	return rowsAliens;
    }

    /** creates an array on which the aliens will be drawn on. This method is the same for GUI and Text Version
    */
    
    public void createAlienArrays(){
        for (int r=0; r<rowsAliens ; r++) {
            for (int c=0; c<numAliens; c++){
                aliens[r][c] = new Alien(10, 5, 40);
            }
        }
    }

    /** Sets the aliens in place for the text version of the game. Specifies the location of each alien, which are drawn in InvadersGame
    */
    
    public void setAliens(){
        for (int r = 0; r < rowsAliens ; r++) {
            for (int c=0; c< numAliens;c++){
            	aliens[r][c] = new Alien(3, 3, 0);
                aliens[r][c].setXCoord(4+2*c);
                if (r%2!=0){
                    aliens[r][c].setYCoord(r+1); 
                }
            }
        }
    }

    /** This method checks to see if there is an alien alive in the rightmost and leftmost
     * columns. If there is, that column becomes the boundary for the movement method and if there 
     * is not an alien alive, it moves to the next column over and that column then becomes the boundary.
    */ 

    public void checkBoundary(){
        
        for (int c = 0; c <numAliens;c++){
            int count = 0;
            for (int r = 0; r < rowsAliens ; r++) {
                if (aliens[r][c].isAlive()){
                    count++;
                    rightBoundary = c;
                }
            }
        }
        for (int c = numAliens-1; c>-1;c--){
            int count = 0;
            for (int r = 0; r < rowsAliens ; r++) {
                if (aliens[r][c].isAlive()){
                    count++;
                    leftBoundary = c;
                }
            }
        }  
    }


    /** This method handles all alien movement. If moveRight is true, it shifts all the aliens right every time the game updates. 
	*When the last alien reaches the end of the board, moveRight is set to false 
	*and the aliens move down and start going left. 
	*When they reach the left limit, moveRight is set to true again.
	*@param width of board. 
    */ 
    
    public void aliensMovement(int width){
        
        if (moveRight){
            checkBoundary();
			// checks if the alien right boundary is close to the right end of the board, if true aliens move down
            if ((aliens[0][rightBoundary].getXCoord()+aliens[0][rightBoundary].getHSpeed())>=width - aliens[0][0].getWidth()){ 
                for (int r = 0; r < rowsAliens ; r++) {
                    for (int c = 0; c < numAliens; c++){
                        aliens[r][c].moveDown();
                    } 
                }
                moveRight = false;
                return; 
            }

            for (int r = 0; r < rowsAliens ; r++) {
                for (int c = 0; c < numAliens; c++){
                    aliens[r][c].moveRight();
                }
            }
        }
        
        else{
            
            if (aliens[0][leftBoundary].getXCoord()<=1){ 
                for (int r = 0; r < rowsAliens ; r++) {
                    for (int c = 0; c < numAliens; c++){
                        aliens[r][c].moveDown();
                    }
                    
                }
                moveRight = true;
                return; 
            }

            for (int r = 0; r < rowsAliens ; r++) {
                for (int c = 0; c < numAliens; c++){
                    aliens[r][c].moveLeft();
                }
            }
        }
    } 

    /** This method draws the GUI alien array.
	* @param Graphics object, g
    */
    
    public void drawAlienArray(Graphics g){
        for (int r=0; r<rowsAliens;r++){
            for (int c=0; c<numAliens;c++){
                if (aliens[r][c].isAlive()){
                    aliens[r][c].draw(g);
                }
            }
        }
    }

    /** This method sets the location for aliens to be drawn
	*/

    public void setGUIaliens(){
        int whereX=5;
        int whereY=0;
		// the aliens are spaced out evenly
        for (int r=0; r<rowsAliens;r++){
            for (int c=0; c<numAliens;c++){
                whereX+=50;
                aliens[r][c].setXCoord(whereX);
                aliens[r][c].setYCoord(whereY);
            }
            whereX=5;
            whereY+=40;
        }
    }
        
        
}
