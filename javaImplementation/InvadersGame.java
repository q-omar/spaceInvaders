import java.util.Scanner;

public class InvadersGame{
	
	boolean quit = false;
	int boardHeight = 30;
	int boardWidth = 60;

	char[][] board = new char[boardHeight][boardWidth];
	
	public void play(){
		playerShip ship = new playerShip();
		drawCurrentState(ship.getLocation);
		handleEvents();
		while(!quit){
			updateGame();
			drawCurrentState(shipLocation);
		}
	}
	
	public void updateGame(){
        drawCurrentState(ship.getLocation()); 
    }
	
	public void createBoard(){
        for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = ' '; 
            }
        }        
	}

	public void drawCurrentState(int shipLocation){ 
    	if (shipLocation > 2 && shipLocation < (boardWidth-2)) {
    		board[boardHeight-1][shipLocation] = 'X';
    		board[boardHeight-1][shipLocation+3] = ' '; 
    		board[boardHeight-1][shipLocation-3] = ' ';
    	}

        for (int r = 0; r < boardHeight; r++) {
            System.out.print("|"); 
            for (int c = 0; c < boardWidth; c++) {
                
                System.out.print(board[r][c]);
            }
            System.out.println("|"); 
        }
    }

	public boolean handleEvents(){
		Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter A for left, D for right (Q to quit)"); 
        String selection = keyboard.nextLine(); 
        String upperSelection = selection.toUpperCase();
        if (upperSelection.equals("A") || upperSelection.equals("D")) {
            ship.shipMovement(upperSelection);
        } else if (upperSelection.equals("Q")) {
            quit = true;
        }
		return quit;
    }













}