import java.util.Scanner;

public class InvadersGame{
	
    playerShip ship = new playerShip();
    playerShot shot = new playerShot();
    
    boolean quit = false;
	int boardHeight = 30;
	int boardWidth = 60;
	char[][] board = new char[boardHeight][boardWidth];
    int shotHeight = 2;
    
	public void play(){
		createBoard();
        
        while(!quit){
            drawCurrentState(ship.getLocation(), ship.getLastLocation(),shot.getShotFired());
            handleEvents();	
            ship.inBounds(boardWidth);
		}
	}
	
	public void createBoard(){
        for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = ' '; 
            }
        }        
	}

	public void drawCurrentState(int shipLocation, int shipLastLocation, boolean shotFired){ 
    	board[boardHeight-1][shipLocation] = 'X';
    	if (shipLocation != shipLastLocation) {
            board[boardHeight-1][shipLastLocation] = ' ';
        }
        
        if (shotFired==true){
            int oldShotHeight = shotHeight;
            System.out.print(shipLocation); //this is a debug message im using to see what shotLocation value is passed 
            playerShip shotShip = new playerShip(shipLocation);
            System.out.print(shotShip.getLocation());//another debug message, im passing the shipLocation as a parameter but its still printing 29 x_x
            int shotLocation = shotShip.getLocation();
            board[boardHeight-(1+(shotHeight++))][shotLocation] = '|';
            board[boardHeight-(oldShotHeight++)][shotLocation] = ' ';
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
        } else if (upperSelection.equals("F")){
            shot.shotFired(upperSelection);
        }else if (upperSelection.equals("Q")) {
            quit = true;
        }
		return quit;
    }

}