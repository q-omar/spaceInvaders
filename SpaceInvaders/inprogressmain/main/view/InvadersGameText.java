package view;

import model.*;

public class InvadersGameText{
	/*******************************************************
	method: This class handles all the drawing for the text version of the game. It prints a board array, player ship and player shot. It gets the 
	* ship and shot each time the board is redrawn. 
	* @param boardHeight is the height of the text-based board game.
	* @param boardWidth is the width of the text-based board game.
	* @param board is a char array of the board.
	*/
    int boardHeight = 30;
    int boardWidth = 60;
    char[][] board = new char[boardHeight][boardWidth]; 

	/**
	 * this method initializes the character array with empty spaces
	 */

    public void createBoard(){
        for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = ' '; 
            }
        }  
	}
	
    /**
	 * a boolean method to check if a location is within bounds, checking for shot and ship locations
	 * @param x,y takes in a location to see if it is within the boards height and width 
	 */

    public boolean validLocation(int x, int y) {
    	boolean valid = false;
    	if (x >= 0 && x < boardWidth && y >= 0 && y < boardHeight) {
    		valid = true;
    	}
    	return valid;
    }
    
	/**
	method: printBoard
			prints each index of the board array onto screen
	*/

    public void printBoard(){
        for (int r = 0; r < boardHeight; r++) {
            System.out.print("|"); 
            for (int c = 0; c < boardWidth; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println("|"); 
        }
    }
    
	/**
	 * creates barriers onto the array
	 * @param rightBarrier,leftBarrier,traversePoint specifies the position of the barrier
	 */

	public void createBarriers(int rightBarrier, int leftBarrier, int traversePoint){
			board[boardHeight-5][boardWidth-rightBarrier] = '|';
			board[boardHeight-5][boardWidth-leftBarrier] = '|';
			for (int i = traversePoint; i<boardWidth-leftBarrier; i++){
				board[boardHeight-6][i] = '_';
			}
			for (int i = traversePoint; i<boardWidth-leftBarrier; i++){
				board[boardHeight-5][i] = '_';
			}
	}

	/**
	 * removes barrier
	 * @param rightBarrier,leftBarrier,traversePoint specifies the position of the 'destroyed' barrier
	 */

	public void emptyBarriers(int rightBarrier, int leftBarrier, int traversePoint){
			board[boardHeight-5][boardWidth-rightBarrier] = ' ';
			board[boardHeight-5][boardWidth-leftBarrier] = ' ';
			for (int i = traversePoint; i<boardWidth-leftBarrier; i++){
				board[boardHeight-6][i] = ' ';
			}
			for (int i = traversePoint; i<boardWidth-leftBarrier; i++){
				board[boardHeight-5][i] = ' ';
			}
	}
	
	/**
	 * method drawBarriers creates barrier if their hp is higher than 0
	 * @param barrier takes in a barrier object and depending on its hp, draws it otherwise it destroys it
	 */

    public void drawBarriers(Barrier barrier){
	/*	if (barrier.getBarrier1HP() > 0){
			createBarriers(55, 45, 6);
		}else{
			emptyBarriers(55, 45, 6);
		}
	
		if (barrier.getBarrier2HP() > 0){
			createBarriers(35, 25, 26);
		}else{
			emptyBarriers(35, 25, 26);
		}
		
		if (barrier.getBarrier3HP() > 0){
			createBarriers(15, 5, 46);
		}else{
			emptyBarriers(15, 5, 46);
		} */
	}
    
	/**
	 * method drawShip gets current location and then prints char X on it, and puts a ' ' at its last location
	 * @param ship is passed from the controller and its location is inside the object, which this method uses to display it
	*/

    public void drawShip(PlayerShip ship){
    	
        board[boardHeight-1][ship.getXCoord()] = 'X';
        if (ship.getXCoord() != ship.getLastXCoord() && validLocation(ship.getLastXCoord(), 0)) {
            board[boardHeight-1][ship.getLastXCoord()] = ' ';
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

    public void drawShot(Shot shot){
    	
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

    public void drawAliens(AlienArray array){ 
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

	public void drawAlienShot(Shot shot, Barrier barrier, PlayerShip ship){
		
		boolean shotFired = shot.getShotFired();
		
		board[shot.getLastYCoord()][shot.getLastXCoord()] = ' ';
		
		if (shotFired){
			if (shot.checkBarrierHit(barrier, boardWidth, boardHeight)){
				shot.shotFired(false);
			}else if(shot.alienShotShip(ship.getXCoord(), ship.getYCoord())){
				shot.shotFired(false);
			}else 
				board[shot.getYCoord()][shot.getXCoord()] = 'Y';
			}
			
			//either this can be used or inBound in AlienShots class with tweaking of that method
			if (shot.getYCoord() > boardHeight-1){
				shot.shotFired(false);
		}
	}

	/**
	method: drawCurrentState draws the current iteration of the game onto the 
	* @param ship,shot,alienshot,array,barrier are passed from the controller and are used for the drawing with
	* their respective methods
	*/
	
    public void drawCurrentState(PlayerShip ship, Shot shot, Shot alienShot, AlienArray array, Barrier barrier){ 
        drawShip(ship);
        drawShot(shot);
        drawAliens(array);
        drawAlienShot(alienShot, barrier, ship);
        drawBarriers(barrier);
        printBoard();
    }
}
