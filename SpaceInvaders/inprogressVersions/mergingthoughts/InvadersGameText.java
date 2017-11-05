public class InvadersGameText{
   
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
    public void drawShip(){
        board[boardHeight-1][ship.getLocation()] = 'X';
        if (ship.getLocation() != ship.getLastLocation()) {
            board[boardHeight-1][ship.getLastLocation()] = ' ';
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
    public void drawShot(){
        for (int r = 0; r < alienInvaders.getRowsAliens() ; r++) {
			for (int c=0; c<alienInvaders.getNumAliens();c++){
				
				if (shot.checkHit(alienInvaders.aliens[r][c].getAlienY(), alienInvaders.aliens[r][c].getAlienX()) && shot.getShotFired()) {
					shot.shotFired(false); 
					alienInvaders.aliens[r][c].destroyAlien();
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
    public void drawAliens(){ 
		for (int r = 0; r < alienInvaders.getRowsAliens() ; r++) {
			for (int c=0; c<alienInvaders.getNumAliens();c++){

				board[alienInvaders.aliens[r][c].getLastAlienY()][alienInvaders.aliens[r][c].getLastAlienX()] = ' ';
				board[alienInvaders.aliens[r][c].getLastAlienY()][alienInvaders.aliens[r][c].getLastAlienX()] = ' ';
            
				if (alienInvaders.aliens[r][c].isAlive()) {
					board[alienInvaders.aliens[r][c].getAlienY()][alienInvaders.aliens[r][c].getAlienX()] = 'U';
                }
            }
        }
    }

	/******************************************************
	method: drawCurrentState
			draws the current iteration of the game onto the board
	******************************************************/
    public void drawCurrentState(){ 
        drawShip();
        drawShot();
        drawAliens();
        printBoard();
    }
}