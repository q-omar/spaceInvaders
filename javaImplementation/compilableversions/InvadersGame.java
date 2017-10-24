/******************************************************************************
							Group 13: Demo 1 Invaders Game
							
This program launches a text-based, simple version of Invaders Game. There is one 
player ship, one alien, with the ability for the playership to move left or right
or shoot by user input across a board.

******************************************************************************/

import java.util.Scanner;

public class InvadersGame{
    
    AlienArray alienInvaders = new AlienArray();
    playerShip ship = new playerShip();
    playerShot shot = new playerShot();
    
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
    
    public void quitCondition(){
        if (alienInvaders.alienRowTwo[0].inBounds(boardHeight)) {
            quit = true;
            System.out.println("Game over, the aliens got you!");
        } 
        else{
            for (int i = 0; i < alienInvaders.numAliens ; i++) {
                if ((!alienInvaders.alienRowOne[i].isAlive()) && (!alienInvaders.alienRowTwo[i].isAlive())) {
                    quit = true;
                    System.out.println("You won!!");
                }
            }
        }
    }

    public void play(){
        createBoard();
        alienInvaders.createAlienArrays();
		alienInvaders.setAliens();
		
        while(!quit){
            drawCurrentState();
            handleEvents(); 
            quitCondition();
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

    public void printBoard(){
        for (int r = 0; r < boardHeight; r++) {
            System.out.print("|"); 
            for (int c = 0; c < boardWidth; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println("|"); 
        }
    }

	/************************************************************************************
	method: drawCurrentState
			outputs onto screen current iteration of the board with the player ship, alien ships, the player shot
			and boundaries within their respective index positions			
	
	************************************************************************************/
    
    public void drawShip(){
        board[boardHeight-1][ship.getLocation()] = 'X';
        if (ship.getLocation() != ship.getLastLocation()) {
            board[boardHeight-1][ship.getLastLocation()] = ' ';
        }
    }
    
    public void drawShot(){
        for (int i = 0; i < alienInvaders.numAliens ; i++) {
            if (shot.checkHit(alienInvaders.alienRowOne[i].getAlienY(), alienInvaders.alienRowOne[i].getAlienX()) && shot.getShotFired()) {
                shot.shotFired(false); // Makes shot "disappear"
                alienInvaders.alienRowOne[i].destroyAlien();
            } else if (shot.checkHit(alienInvaders.alienRowTwo[i].getAlienY(), alienInvaders.alienRowTwo[i].getAlienX()) && shot.getShotFired()) {
                shot.shotFired(false); // Makes shot "disappear"
                alienInvaders.alienRowTwo[i].destroyAlien(); //current bug with shot interaction: goes through multiple rows 
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


    public void drawAliens(){ 
        for (int i = 0; i < alienInvaders.numAliens ; i++) {
            board[alienInvaders.alienRowOne[i].getLastAlienY()][alienInvaders.alienRowOne[i].getLastAlienX()] = ' ';
            board[alienInvaders.alienRowTwo[i].getLastAlienY()][alienInvaders.alienRowTwo[i].getLastAlienX()] = ' ';
            
            if ((alienInvaders.alienRowOne[i].isAlive()) && (alienInvaders.alienRowTwo[i].isAlive())) {
                board[alienInvaders.alienRowOne[i].getAlienY()][alienInvaders.alienRowOne[i].getAlienX()] = 'U';
                board[alienInvaders.alienRowTwo[i].getAlienY()][alienInvaders.alienRowTwo[i].getAlienX()] = 'U';
                
            }
        }
    }

    public void drawCurrentState(){ 
        drawShip();
        drawShot();
        drawAliens();
        printBoard();
    }
    
        
        
       
    public void handleShot(String handlePart){
        if (handlePart == "part1"){
            if (!shot.getShotFired()) { // This makes a new shot
                shot.setShotColumn(ship.getLocation());
                shot.setShotRow(boardHeight-2);
                shot.shotFired(true);
            } else {
                System.out.println("Out of ammo!"); // Temporary message for trying to shoot more than one at a time
            }
        } else if (shot.getShotFired()) { // Update shot location
        	shot.moveShot();
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
            ship.inBounds(boardWidth);    
            
        } else if (upperSelection.equals("F")) {
        	handleShot("part1");
        	
        } else if (upperSelection.equals("Q")) {
            quit = true;
            System.out.println("You quit the game.");
        }

        handleShot("part2");
        alienInvaders.aliensMovement();
    }

}