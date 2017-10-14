import java.util.Scanner;

public class drawGame{

    private int boardHeight = 20;
    private int boardWidth = 60;
    private int shotRow = 2;

    private char[][] board = new char[boardHeight][boardWidth]; //creates a 30x60 list 

    public int getWidth() {
        return boardWidth;
    }

    public int getHeight() {
        return boardHeight;
    }

    public void createBoard() { 
        for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                board[r][c] = ' '; //populating the list with empty spaces
            }
        }        
    }

    public void displayBoard(int shipLocation, boolean shotFired, int shotLocation){ //displays the list that was created
    	if (shipLocation > 2 && shipLocation < (boardWidth-2)) {
    		board[boardHeight-1][shipLocation] = 'X';
    		board[boardHeight-1][shipLocation+3] = ' '; //makes the previous index the ship was in as blank
            board[boardHeight-1][shipLocation-3] = ' ';
            
            if (shotFired==true){
                int oldShotRow = shotRow;
                board[boardHeight - (1+(shotRow++))][shotLocation] = '|';
                board[boardHeight - (oldShotRow++)][shotLocation] = ' ';
    
            }
    	}

        for (int r = 0; r < boardHeight; r++) {
            System.out.print("|"); //i put this to show left boundary
            for (int c = 0; c < boardWidth; c++) {
                
                System.out.print(board[r][c]);
            }
            System.out.println("|"); //i put this to show right boundary 
        }
    }
        
    
}