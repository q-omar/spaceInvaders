package view;

import model.*;

public class InvadersGameText{
	/*******************************************************
	method: This class handles all the drawing for the text version of the game. It prints a board array, player ship and player shot. It gets the 
	* ship and shot each time the board is redrawn. 
	* @param BOARD_HEIGHT is the height of the text-based board game.
	* @param BOARD_WIDTH is the width of the text-based board game.
	* @param board is a char array of the board.
	*/
    private final int BOARD_HEIGHT = 30;
    private final int BOARD_WIDTH = 60;
    private final char[][] board = new char[BOARD_HEIGHT][BOARD_WIDTH];
    
    public InvadersGameText() {
    	// Leaving this call here to initialize the board properly
    	createBoard();
    }

	/**
	 * this method initializes the character array with empty spaces
	 */

    public void createBoard(){
        for (int r = 0; r < BOARD_HEIGHT; r++) {
            for (int c = 0; c < BOARD_WIDTH; c++) {
                board[r][c] = ' '; 
            }
        }  
	}
	
    /**
	 * a boolean method to check if a location is within bounds, checking for shot and ship locations
	 * @param x,y takes in a location to see if it is within the boards height and width 
	 */

    private boolean validLocation(int x, int y) {
    	boolean valid = false;
    	if (x >= 0 && x < BOARD_WIDTH && y >= 0 && y < BOARD_HEIGHT) {
    		valid = true;
    	}
    	return valid;
    }
    
	/**
	method: printBoard
			prints each index of the board array onto screen
	*/

    private void printBoard(){
        for (int r = 0; r < BOARD_HEIGHT; r++) {
            System.out.print("|"); 
            for (int c = 0; c < BOARD_WIDTH; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println("|"); 
        }
    }

	/**
	 * method drawBarriers creates barrier if their hp is higher than 0
	 * @param barrier takes in a barrier object and depending on its hp, draws it otherwise it destroys it
	 */

	private void drawBarriers(BarrierArray barriers){
		for(int i=0; i<barriers.getAmount(); i++){
			
			char character = barriers.getBarriers()[i].barrierCharText();
			int y = barriers.getBarriers()[i].getYCoord();
			int lowerBound = y + barriers.getBarriers()[i].getHeight();
		
			for (; y < lowerBound; y++) {
				int x = barriers.getBarriers()[i].getXCoord();
				int rightBound = x + barriers.getBarriers()[i].getWidth();
				
				for (; x < rightBound; x++) {
					board[y][x] = character;
				}
			}
			
        }	
	}
    
	/**
	 * method drawShip gets current location and then prints char X on it, and puts a ' ' at its last location
	 * @param ship is passed from the controller and its location is inside the object, which this method uses to display it
	*/

    private void drawShip(PlayerShip ship){
    	
        board[BOARD_HEIGHT -1][ship.getXCoord()] = 'X';
        if (ship.getXCoord() != ship.getLastXCoord() && validLocation(ship.getLastXCoord(), 0)) {
            board[BOARD_HEIGHT -1][ship.getLastXCoord()] = ' ';
        }
    }
    
	/**
	method: drawShot
		draws the current player shot on the board, checking if the player shot hit an alien,
		destroying the alien if so and setting shotFired (trigger for the shot) to false, thus
		"removing" the shot from the board
		checks if shot is within the bounds of the board
		replaces last shot position on board with a space character
		@param shot contains the location of the shot and this method uses it to print it
	*/

    private void drawShot(Shot shot){
    	
    	if (validLocation(0, shot.getLastYCoord())) {
			board[shot.getLastYCoord()][shot.getXCoord()] = ' ';
		}

		if (shot.getShotFired()){
			board[shot.getYCoord()][shot.getXCoord()] = '*';
		}
	}
      

	/**
	method: drawAliens
		draws array of aliens on the board, replacing last alien positions with spaces first
		and then setting the new alien positions if they are still "alive"
		@param array contains locations of the positions of all aliens and this method uses it to display it 
	*/
	private void drawAliens(AlienArray array){
		for (int r = 0; r < array.getRowsAliens() ; r++) {
			for (int c=0; c < array.getNumAliens();c++){
				
				if (validLocation(array.getAliens()[r][c].getLastXCoord(), array.getAliens()[r][c].getLastYCoord())) {
					board[array.getAliens()[r][c].getLastYCoord()][array.getAliens()[r][c].getLastXCoord()] = ' ';
				}

				if (array.getAliens()[r][c].isAlive() && validLocation(array.getAliens()[r][c].getXCoord(), array.getAliens()[r][c].getYCoord())) {
					board[array.getAliens()[r][c].getYCoord()][array.getAliens()[r][c].getXCoord()] = 'U';
                }
            }
        }
    }

	/** 
	 * method drawAlienShot checks if shot is interacting with the barrier and if it does, the flag is set to false and it doesnt draw it
	 * ship object is needed for the shot to be drawn at its location
	 * @param shot,barrier,ship ship object is used to get the position where the shot will be draw
	 */

	private void drawAlienShot(Shot shot){
		
		boolean shotFired = shot.getShotFired();
		
		board[shot.getLastYCoord()][shot.getLastXCoord()] = ' ';
		
		if (shotFired) {
			board[shot.getYCoord()][shot.getXCoord()] = 'Y';
		}

	}

	/**
	method: drawCurrentState draws the current iteration of the game onto the
	* @param ship,shot,alienshot,array,barrier are passed from the controller and are used for the drawing with
	* their respective methods
	*/

    public void drawCurrentState(PlayerShip ship, Shot shot, Shot alienShot, AlienArray array, BarrierArray barriers){
        drawShip(ship);
		drawBarriers(barriers);
        drawAliens(array);
        drawShot(shot);
		drawAlienShot(alienShot);
        printBoard();
    }
}
