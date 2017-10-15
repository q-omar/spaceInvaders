import java.util.Scanner;

public class InvadersGame{
    
    playerShip ship = new playerShip();
    playerShot shot = new playerShot();
	Alien aliens = new Alien();
    boolean quit = false;
    int boardHeight = 30;
    int boardWidth = 60;
    
    char[][] board = new char[boardHeight][boardWidth];
    
    public void play(){
        createBoard();
        while(!quit){
            drawCurrentState();
            handleEvents(); 
            ship.inBounds(boardWidth);
            shot.inBounds(); 
            if (aliens.inBounds()){
                quit = true;
                System.out.println("Game over, the aliens got you!");
            }

        }
    }
    public void createBoard(){
		for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = ' '; 
            }
        }  
	}
	
    public void drawCurrentState(){ 
        board[boardHeight-1][ship.getLocation()] = 'X';
        if (ship.getLocation() != ship.getLastLocation()) {
            board[boardHeight-1][ship.getLastLocation()] = ' ';
        }

        if (shot.getShotRow() != shot.getLastShotRow()) { 
        	board[shot.getLastShotRow()][shot.getShotColumn()] = ' ';
        }

        if (shot.getShotFired()) { 
        	board[shot.getShotRow()][shot.getShotColumn()] = '*';
        }		
		//
		board[aliens.getLastAlienY()][aliens.getLastAlienX()] = ' ';
		board[aliens.getAlienY()][aliens.getAlienX()] = 'U';

		if ((aliens.getAlienY()%2)==0){
			aliens.moveRight();
		}else {
			aliens.moveLeft();
		}
		//		
        for (int r = 0; r < boardHeight; r++) {
            System.out.print("|"); 
            for (int c = 0; c < boardWidth; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println("|"); 
        }
    }

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

    }

}