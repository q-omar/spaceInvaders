/******************************************************************************
							Group 13: Demo 1 Invaders Game
							
This program launches a text-based, simple version of Invaders Game. There is one 
player ship, one alien, with the ability for the playership to move left or right
or shoot by user input across a board.

******************************************************************************/

import java.util.Scanner;

public class InvadersGame{
    
    playerShip ship = new playerShip();
    playerShot shot = new playerShot();
	Alien alien1 = new Alien();
	Alien alien2 = new Alien();
    boolean quit = false;
    int boardHeight = 30;
    int boardWidth = 60;
    
    char[][] board = new char[boardHeight][boardWidth]; //This array holds a boardHeightxboardWidth board
 
	/**************************************************************************************************
	method: play
			this method creates a board and sets two alien index positions and checks for the user quit flag
			and if any alien is within board boundaries, displays a simulation of the board if conditions met,
			and checks if both aliens are alive, ending the game if so, otherwise continues the game
			
	**************************************************************************************************/
    public void play(){
        createBoard();
		alien1.setAlienX(10);
		alien2.setAlienX(14);
		alien1.setAlienY(2);
		alien2.setAlienY(2);
        while(!quit){
            if (alien1.inBounds(boardHeight-1) | alien2.inBounds(boardHeight-1)) {
                quit = true;
                System.out.println("Game over, the aliens got you!");
            } else {
                drawCurrentState();
                if(!alien1.isAlive()&& !alien2.isAlive()) {
                    quit = true;
                    System.out.println("You won!!");
                } else {
                    handleEvents(); 
                    ship.inBounds(boardWidth); 
                }
                
            }
        }
    }
	
	/******************************************
	method: createBoard
			creates a board with width and height		
	******************************************/
    public void createBoard(){
		for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = ' '; 
            }
        }  
	}
	/************************************************************************************
	method: drawCurrentState
			outputs onto screen current iteration of the board with the player ship, alien ships, the player shot
			and boundaries within their respective index positions			
	
	************************************************************************************/
    public void drawCurrentState(){ 
        board[boardHeight-1][ship.getLocation()] = 'X';
        if (ship.getLocation() != ship.getLastLocation()) {
            board[boardHeight-1][ship.getLastLocation()] = ' ';
        }
		
        if (shot.checkHit(alien1.getAlienY(), alien1.getAlienX()) && shot.getShotFired()) {
            shot.shotFired(false); // Makes shot "disappear"
            alien1.destroyAlien();
        } else if (shot.checkHit(alien2.getAlienY(), alien2.getAlienX()) && shot.getShotFired()) {
            shot.shotFired(false); // Makes shot "disappear"
            alien2.destroyAlien();
        }

        shot.inBounds();

        if (shot.getShotRow() != shot.getLastShotRow() && shot.getLastShotRow() >= 0) { 
        	board[shot.getLastShotRow()][shot.getShotColumn()] = ' ';
        }

        if (shot.getShotFired()) { 
        	board[shot.getShotRow()][shot.getShotColumn()] = '*';
        }		

		board[alien1.getLastAlienY()][alien1.getLastAlienX()] = ' ';
		board[alien2.getLastAlienY()][alien2.getLastAlienX()] = ' ';
        if (alien1.isAlive() || alien2.isAlive()) {
            if (alien1.isAlive() && alien2.isAlive()){
				board[alien2.getAlienY()][alien2.getAlienX()] = 'U';
				board[alien1.getAlienY()][alien1.getAlienX()] = 'U';
			} else if (alien1.isAlive()){
				board[alien1.getAlienY()][alien1.getAlienX()]='U';
			} else if (alien2.isAlive()){
				board[alien2.getAlienY()][alien2.getAlienX()]='U';
			}
		}
		
        for (int r = 0; r < boardHeight; r++) {
            System.out.print("|"); 
            for (int c = 0; c < boardWidth; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println("|"); 
        }
    }

	/************************************************************************************
	method: handleEvents
			prompts the user for input, giving options of moving right, left, shoot, or to
			quit, passing valid user input through required methods to move left or right, shoot
			if possible, and reacting accordingly under conditions of whether they are in the correct
			boundaries within the board 
	
	************************************************************************************/
    public void handleEvents(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter A for left, D for right, or F to shoot (Q to quit)"); 
        String selection = keyboard.nextLine(); 
        String upperSelection = selection.toUpperCase();

        if (upperSelection.equals("A") || upperSelection.equals("D")) {
            ship.shipMovement(upperSelection);
        } else if (upperSelection.equals("F")) {
        	
        	if (!shot.getShotFired()) { // This makes a new shot
        		shot.setShotColumn(ship.getLocation());
        		shot.setShotRow(boardHeight-2);
        		shot.shotFired(true);
        	} else {
        		System.out.println("Out of ammo!"); // Temporary message for trying to shoot more than one at a time
        	}
        	
        } else if (upperSelection.equals("Q")) {
            quit = true;
        }

        if (shot.getShotFired()) { // Update shot location
        	shot.moveShot();
        }

        if ((alien1.getAlienY()%2)==0 && (alien2.getAlienY()%2)==0){ // Moved alien movement here due to loss condition checking
            alien1.moveRight(boardWidth);
			alien2.moveRight(boardWidth);
        } else if ((alien1.getAlienY()%2)==0 && (alien2.getAlienY()%2)!=0) {
            alien1.moveRight(boardWidth);
			alien2.moveLeft(boardWidth);
		} else if ((alien2.getAlienY()%2)==0 && (alien1.getAlienY()%2)!=0){
			alien2.moveRight(boardWidth);
			alien1.moveLeft(boardWidth);
		}else{
			alien1.moveLeft(boardWidth);
			alien2.moveLeft(boardWidth);
        }

    }

}