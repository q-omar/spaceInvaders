import java.util.Scanner;

public class InvadersGame{
	playerShip shipOne = new playerShip();
	Scanner keyboard = new Scanner(System.in);
	drawGame brd = new drawGame();
	int boardHeight = brd.getHeight();
	int boardWidth = brd.getWidth();
	char[][] board = new char[boardHeight][boardWidth];
	boolean quit = false;
	
	public void play(){
		int shipLocation =shipOne.getLocation();
		drawCurrentState(shipLocation);
		handleEvent();
		while(!quit){
			updateGame();
			drawCurrentState(shipLocation);
		}
		
	}
	
	public void updateGame(){
		int shipLocation= shipOne.getLocation();
        drawCurrentState(shipLocation); 
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

	public boolean handleEvent(){
        System.out.print("Enter A for left, D for right (Q to quit)"); 
        String selection = keyboard.nextLine(); 
        String upperSelection = selection.toUpperCase();
        if (upperSelection.equals("A") || upperSelection.equals("D")) {
            shipOne.shipMovement(upperSelection);
        } else if (upperSelection.equals("Q")) {
            quit = true;
        }
		return quit;
    }
}
