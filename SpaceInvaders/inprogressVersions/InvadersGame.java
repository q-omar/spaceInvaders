/******************************************************************************
                            Group 13: Demo 1 Invaders Game
                            
This program launches a text-based, incomplete version of Invaders Game. There is one 
player-controlled ship, now with numerous rows and columns of aliens moving from right
to left down the board, with the player being able to shoot and destroy them until
they win or lose the game depending on if aliens reach the bottom of the board. 
Aliens do not shoot (yet).
******************************************************************************/

import java.util.Scanner;

public class InvadersGame{

    boolean quit = false;
    int boardHeight = 30;
    int boardWidth = 60;
    
    AlienArray alienInvaders = new AlienArray("text");
    playerShip ship = new playerShip(boardWidth, 3);
    playerShot shot = new playerShot(boardHeight - 1, 5);
    
    char[][] board = new char[boardHeight][boardWidth]; 

	/******************************************************
	method: play
			begins the game itself, creating a board and updating it until player quits or loses
	******************************************************/
    public void play(){
        createBoard();
        
        while(!quit){
            drawCurrentState();
            handleEvents(); 
            quitCondition();
        }
    }
    /******************************************************
	method: createBoard
			creates a board of a double array with clear height and width dimensions
	******************************************************/
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
    
	/******************************************************
	method: handleShot
			sets player shot column position to the ship's colum position and sets
			shotFired to true (meaning a shot exists on the board)
			if shot already exists, calls to move the shot up the board
	@param  handlePart if set to "part1", is meant to initialize the player shot
	******************************************************/
    public void handleShot(String handlePart){
        if (handlePart == "part1"){
            if (!shot.getShotFired()) { 
                shot.setShotColumn(ship.getLocation());
                shot.shotFired(true);
            } else {
                System.out.println("Out of ammo!");
            }
        } else if (shot.getShotFired()) { 
            shot.moveShot();
        }   
    }
	
    /******************************************************
	method: handleEvents
			checks for player input; whether player entered to move, shoot, or quit
			also calls to move the alien array
	******************************************************/
    public void handleEvents(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter A for left, D for right, or F to shoot (Q to quit)"); 
        String selection = keyboard.nextLine(); 
        String upperSelection = selection.toUpperCase();

        if (upperSelection.equals("A") || upperSelection.equals("D")) {
            ship.shipMovement(upperSelection);
            ship.inBounds(boardWidth);    
            
        } else if (upperSelection.equals("F")) {
            handleShot("part1");
            
        } else if (upperSelection.equals("Q")) {
            quit = true;
            System.out.println("You quit the game.");
        }

        handleShot("part2");
        alienInvaders.aliensMovement(boardWidth - 4);
    }
	
	/******************************************************
	method: quitCondition
			checks whether or not all aliens have been destroyed, giving the player the win
			or if first alien on first rows reached the row before the last on board, giving 
			the player the loss
	******************************************************/
    public void quitCondition(){
		int count=0;
        if (alienInvaders.aliens[alienInvaders.getRowsAliens() -1][1].inBounds(boardHeight)) {
            quit = true;
            System.out.println("Game over, the aliens got you!");
        } else{
			for (int r = 0; r < alienInvaders.getRowsAliens() ; r++) {
				for ( int c=0; c<alienInvaders.getNumAliens(); c++){
					if (!alienInvaders.aliens[r][c].isAlive()){
						count+=1;
					}
					if (count == (alienInvaders.getRowsAliens()*alienInvaders.getNumAliens())){
						quit=true;
						System.out.println("You won!!");
					}
                }
            }
        }
    }
 
    public static void main(String[] args) {
        InvadersGame game = new InvadersGame();
        game.play();
    }

}