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
    public void drawBarriers(){
		board[boardHeight-5][boardWidth-55] = '|';
		board[boardHeight-5][boardWidth-45] = '|';
		for (int i = 6; i<boardWidth-45; i++){
			board[boardHeight-6][i] = '_';
		}
		for (int i = 6; i<boardWidth-45; i++){
			board[boardHeight-5][i] = '_';
		}
	
		board[boardHeight-5][boardWidth-35] = '|';
		board[boardHeight-5][boardWidth-25] = '|';
		for (int i = 26; i<boardWidth-25; i++){
			board[boardHeight-6][i] = '_';
		}
		for (int i = 26; i<boardWidth-25; i++){
			board[boardHeight-5][i] = '_';
		}
		board[boardHeight-5][boardWidth-15] = '|';
		board[boardHeight-5][boardWidth-5] = '|';
		for (int i = 46; i<boardWidth-5; i++){
			board[boardHeight-6][i] = '_';
		}
		for (int i = 46; i<boardWidth-5; i++){
			board[boardHeight-5][i] = '_';
		}
		
		
		
	}
	/******************************************************
	method: drawShip
			prints the location of the player ship on the board
	******************************************************/
    public void drawShip(PlayerShip ship){
    	
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
    public void drawShot(PlayerShot shot, AlienArray array){
        for (int r = 0; r < array.getRowsAliens() ; r++) {
			for (int c=0; c < array.getNumAliens();c++){
				
				if (shot.checkHit(array.getAliens()[r][c].getYCoord(), array.getAliens()[r][c].getXCoord()) && shot.getShotFired()) {
					shot.shotFired(false); 
					array.getAliens()[r][c].destroyAlien();
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

				board[array.getAliens()[r][c].getLastYCoord()][array.getAliens()[r][c].getLastXCoord()] = ' ';
				board[array.getAliens()[r][c].getLastYCoord()][array.getAliens()[r][c].getLastXCoord()] = ' ';
            
				if (array.getAliens()[r][c].isAlive()) {
					board[array.getAliens()[r][c].getYCoord()][array.getAliens()[r][c].getXCoord()] = 'U';
                }
            }
        }
    }

	/******************************************************
	method: drawCurrentState
			draws the current iteration of the game onto the board
	******************************************************/
    public void drawCurrentState(PlayerShip ship, PlayerShot shot, AlienArray array){ 
        drawShip(ship);
        drawShot(shot, array);
        drawAliens(array);
        printBoard();
		drawBarriers();
    }
}
