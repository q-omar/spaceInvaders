/******************************************************************************
                            Group 13: Demo 1 Invaders Game
                            
This program launches a text-based, simple version of Invaders Game. There is one 
player ship, one alien, with the ability for the playership to move left or right
or shoot by user input across a board.
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

    public void play(){
        createBoard();
        
        while(!quit){
            drawCurrentState();
            handleEvents(); 
            quitCondition();
        }
    }
    
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
    
    public void drawShip(){
        board[boardHeight-1][ship.getLocation()] = 'X';
        if (ship.getLocation() != ship.getLastLocation()) {
            board[boardHeight-1][ship.getLastLocation()] = ' ';
        }
    }
    
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

    public void drawCurrentState(){ 
        drawShip();
        drawShot();
        drawAliens();
        printBoard();
    }  
       
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