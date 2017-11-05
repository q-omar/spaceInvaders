public class InvadersGameText{
   
    int boardHeight = 30;
    int boardWidth = 60;
    char[][] board = new char[boardHeight][boardWidth]; 
	private InvadersGameLogic logic;
	
	public InvadersGameText(InvadersGameLogic newLogic){
		logic = newLogic;
	}

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
        board[boardHeight-1][logic.getShip().getLocation()] = 'X';
        if (logic.getShip().getLocation() != logic.getShip().getLastLocation()) {
            board[boardHeight-1][logic.getShip().getLastLocation()] = ' ';
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
        for (int r = 0; r < logic.getArray().getRowsAliens() ; r++) {
			for (int c=0; c < logic.getArray().getNumAliens();c++){
				
				if (logic.getShot().checkHit(logic.getArray().aliens[r][c].getAlienY(), logic.getArray().aliens[r][c].getAlienX()) && logic.getShot().getShotFired()) {
					logic.getShot().shotFired(false); 
					logic.getArray().aliens[r][c].destroyAlien();
				}
				
            } 
        }

        logic.getShot().inBounds();

        if (logic.getShot().getShotRow() != logic.getShot().getLastShotRow() && logic.getShot().getLastShotRow() >= 0) { 
            board[logic.getShot().getLastShotRow()][logic.getShot().getShotColumn()] = ' ';
        }
        if (logic.getShot().getShotFired()) { 
            board[logic.getShot().getShotRow()][logic.getShot().getShotColumn()] = '*';
        }
    }

	/******************************************************
	method: drawAliens
			draws array of aliens on the board, replacing last alien positions with spaces first
			and then setting the new alien positions if they are still "alive"
	******************************************************/
    public void drawAliens(){ 
		for (int r = 0; r < logic.getArray().getRowsAliens() ; r++) {
			for (int c=0; c < logic.getArray().getNumAliens();c++){

				board[logic.getArray().aliens[r][c].getLastAlienY()][logic.getArray().aliens[r][c].getLastAlienX()] = ' ';
				board[logic.getArray().aliens[r][c].getLastAlienY()][logic.getArray().aliens[r][c].getLastAlienX()] = ' ';
            
				if (logic.getArray().aliens[r][c].isAlive()) {
					board[logic.getArray().aliens[r][c].getAlienY()][logic.getArray().aliens[r][c].getAlienX()] = 'U';
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