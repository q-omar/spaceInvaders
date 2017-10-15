import java.util.Scanner;

public class InvadersGame{
    
    playerShip ship = new playerShip();
    playerShot shot = new playerShot();
    boolean quit = false;
    int boardHeight = 30;
    int boardWidth = 60;
    
    char[][] board = new char[boardHeight][boardWidth];
    
    public void play(){
        createBoard();
        while(!quit){
            drawCurrentState(ship.getLocation(), ship.getLastLocation());
            handleEvents(); 
            ship.inBounds(boardWidth); // inBounds methods ensure location doesn't go out of bounds
            shot.inBounds(); 
        }
    }
    
    public void createBoard(){
        for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = ' '; 
            }
        }        
    }

    public void drawCurrentState(int shipLocation, int shipLastLocation){ 
        board[boardHeight-1][shipLocation] = 'X';
        if (shipLocation != shipLastLocation) {
            board[boardHeight-1][shipLastLocation] = ' ';
        }

        if (shot.getShotRow() != shot.getLastShotRow()) { // Make last spot bullet was in blank
        	board[shot.getLastShotRow()][shot.getShotColumn()] = ' ';
        }

        if (shot.getShotFired()) { // Draw new bullet only if shotFired is currently true
        	board[shot.getShotRow()][shot.getShotColumn()] = '*';

        }

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