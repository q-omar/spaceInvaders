import java.util.Scanner;

public class drawGame{

    private int boardHeight = 30;
    private int boardWidth = 60;

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
    
}
