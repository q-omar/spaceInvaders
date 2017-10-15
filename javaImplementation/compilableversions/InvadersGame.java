 import java.util.Scanner;
 
 public class InvadersGame{
 	
    playerShip ship = new playerShip();
	playerShot shotOne = new playerShot();
 	boolean quit = false;
 	int boardHeight = 30;
 	int boardWidth = 60;
     
  	char[][] board = new char[boardHeight][boardWidth];
  	
  	public void play(){
 		createBoard();
  		while(!quit){
			updateState(ship.getLocation(), ship.getLastLocation());
            handleEvents();	
  		}
  	}
  	
 	public void createBoard(){
        for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = ' '; 
            }
        }        
  	}
  
 	public void updateState(int shipLocation,int shipLastLocation){ 
     	if (shipLocation > 2 && shipLocation < (boardWidth-2)) {
     		board[boardHeight-1][shipLocation] = 'X';
    		board[boardHeight-1][shipLocation+3] = ' '; 
     		board[boardHeight-1][shipLocation-3] = ' ';
		}
		
     	board[boardHeight-1][shipLocation] = 'X';
     	if (shipLocation != shipLastLocation) {
     		board[boardHeight-1][shipLastLocation] = ' ';
      	}
		
		if (shotOne.isFired()){
			int shotRow = shotOne.moveBullet(shotOne.getShotColumn(),shotOne.getShotRow());
			int shotColumn = shotOne.getShotColumn();
			board[shotRow][shotColumn] = '|';
            if (shotRow <= boardHeight-2){
				board[shotRow+1][shotColumn] = ' ';
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
 
 	public boolean handleEvents(){
		Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter A for left, D for right, F to fire (Q to quit)"); 
        String selection = keyboard.nextLine(); 
        String upperSelection = selection.toUpperCase();
        if (upperSelection.equals("A") || upperSelection.equals("D")) {
            ship.shipMovement(upperSelection);
        } else if (upperSelection.equals("Q")) {
            quit = true;
        } else if (upperSelection.equals("F")){
			shotOne.setIsFired();
		}
 		return quit;
    }
 }