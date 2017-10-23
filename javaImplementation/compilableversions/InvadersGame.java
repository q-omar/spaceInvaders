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
    boolean quit = false;
    int boardHeight = 30;
    int boardWidth = 60;
	boolean shotFired = false;
	
	int amountAliens = 4;
	Alien[] alienList= new Alien[amountAliens];
	int x=0;
	int y=2;
    
    char[][] board = new char[boardHeight][boardWidth]; //This array holds a boardHeightxboardWidth board
 
	/**************************************************************************************************
	method: play
			this method creates a board and sets two alien index positions and checks for the user quit flag
			and if any alien is within board boundaries, displays a simulation of the board if conditions met,
			and checks if both aliens are alive, ending the game if so, otherwise continues the game
			
	**************************************************************************************************/
    public void play(){
        createBoard();
		
		for (int i=0; i< amountAliens; i++){ // creates a list of aliens for each index or position i we set the x and y coordinate
			alienList[i] = new Alien();
			alienList[i].setAlienX(x);
			alienList[i].setAlienY(y);
			x += (boardWidth / amountAliens); // the x coordinate increases by increments of the board, so if we have 4 aliens, the x is boardwidth divided by 4
			// we add another increment of the fraction to the next x so that the aliens are even over the board
		}
		
        while(!quit){ // I deleted all the conditionals where quit is being changed to false and put it in the handle events method handle events is supposed to change quit
            drawCurrentState();
            handleEvents(); 
            ship.inBounds(boardWidth);
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
		
		for (int i=0; i< amountAliens; i++){
			if (shot.checkHit(alienList[i].getAlienY(), alienList[i].getAlienX()) && shot.getShotFired()){
				shot.shotFired(false); // Makes shot "disappear"
				alienList[i].destroyAlien();
			}
		}

        shot.inBounds();

        if (shot.getShotRow() != shot.getLastShotRow() && shot.getLastShotRow() >= 0) { 
        	board[shot.getLastShotRow()][shot.getShotColumn()] = ' ';
        }

        if (shot.getShotFired()) { 
        	board[shot.getShotRow()][shot.getShotColumn()] = '*';
        }		

		for (int i=0; i< amountAliens; i++){// draws the aliens, changes each alien in alien list (all that is changed is that i put it in a loop)
			board[alienList[i].getLastAlienY()][alienList[i].getLastAlienX()] = ' ';
			if (alienList[i].isAlive()){
				board[alienList[i].getAlienY()][alienList[i].getAlienX()] = 'U';
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
		
		
		int count=0; // counts how many aliens are destroyed
		for (int i=0; i<= amountAliens; i++){
			if ((alienList[i].getAlienY()%2)==0){
				if (alienList[amountAliens].getAlienX()<= (boardWidth -5)){
					alienList[i].moveRight(boardWidth);// move right if row is even
				}
			}else if((alienList[i].getAlienY()%2)!=0){
				alienList[i].moveLeft(boardWidth); // move left if row is odd
			}
			if (alienList[i].inBounds(boardHeight)){ // checks if aliens are in bounds, if not changes quit to true
				quit=true;
				System.out.println("Game over, the aliens got you!");
			}
			if (!alienList[i].isAlive()) {
                    count+=1; // counts if the specific alien is destroyed
			}
			if (count == amountAliens){ // if count is equal to the number of aliens, quit is true
				quit = true; 
				System.out.println("You won!!");
			}
		}

    }

}