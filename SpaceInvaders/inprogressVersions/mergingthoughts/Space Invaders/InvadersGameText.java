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

    public void createBoard(){
        for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = ' '; 
            }
        }  
    }
	/******************************************************
	method: printBoard
			prints each index of the board array onto screen
	******************************************************/
    public void printBoard(){
        for (int r = 0; r < boardHeight; r++) {
            System.out.print("|"); 
            for (int c = 0; c < boardWidth; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println("|"); 
        }
    }
    
	/******************************************************
	method: drawShip
			prints the location of the player ship on the board
	******************************************************/
    public void drawShip(playerShip ship){
    	
        board[boardHeight-1][ship.getXCoord()] = 'X';
        if (ship.getXCoord() != ship.getLastXCoord() && ship.getLastXCoord() <= (boardWidth-1) && ship.getLastXCoord() >= 0) {
            board[boardHeight-1][ship.getLastXCoord()] = ' ';
        }
    }
    
	/******************************************************
	method: drawShot
			draws the current player shot on the board, checking if the player shot hit an alien,
			destroying the alien if so and setting shotFired (trigger for the shot) to false, thus
			"removing" the shot from the board
			checks if shot is within the bounds of the board
			replaces last shot position on board with a space character
	******************************************************/
    public void drawShot(playerShot shot, AlienArray array){
        for (int r = 0; r < array.getRowsAliens() ; r++) {
			for (int c=0; c < array.getNumAliens();c++){
				
				if (shot.checkHit(array.aliens[r][c].getYCoord(), array.aliens[r][c].getXCoord()) && shot.getShotFired()) {
					shot.shotFired(false); 
					array.aliens[r][c].destroyAlien();
				}
				
            } 
        }

        shot.inBounds();

        if (shot.getShotRow() != shot.getLastShotRow() && shot.getLastShotRow() >= 0) { 
            board[shot.getLastShotRow()][shot.getShotColumn()] = ' ';
        }
        if (shot.getShotFired()) { 
            board[shot.getShotRow()][shot.getShotColumn()] = '*';
        }
    }

	/******************************************************
	method: drawAliens
			draws array of aliens on the board, replacing last alien positions with spaces first
			and then setting the new alien positions if they are still "alive"
	******************************************************/
    public void drawAliens(AlienArray array){ 
		for (int r = 0; r < array.getRowsAliens() ; r++) {
			for (int c=0; c < array.getNumAliens();c++){

				board[array.aliens[r][c].getLastYCoord()][array.aliens[r][c].getLastXCoord()] = ' ';
				board[array.aliens[r][c].getLastYCoord()][array.aliens[r][c].getLastXCoord()] = ' ';
            
				if (array.aliens[r][c].isAlive()) {
					board[array.aliens[r][c].getYCoord()][array.aliens[r][c].getXCoord()] = 'U';
                }
            }
        }
    }

	/******************************************************
	method: drawCurrentState
			draws the current iteration of the game onto the board
	******************************************************/
    public void drawCurrentState(playerShip ship, playerShot shot, AlienArray array){ 
        drawShip(ship);
        drawShot(shot, array);
        drawAliens(array);
        printBoard();
    }
}