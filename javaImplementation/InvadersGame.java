import java.util.Scanner;


public class InvadersGame{

    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in); //import scanner for user input
        
        drawGame game1 = new drawGame();
        playerShip shipObj = new playerShip();
        game1.createBoard();
        boolean quit = false; 
                    
        while (!quit) {

            game1.displayBoard(shipObj.getLocation());

            System.out.print("Enter A for left, D for right (Q to quit)"); //prompts user for input 
            String selection = keyboard.nextLine(); //our selection for left/right
            String upperSelection = selection.toUpperCase();
            
            // if input is left or right
            if (upperSelection.equals("A") || upperSelection.equals("D")) {
                shipObj.shipMovement(upperSelection);
            } else if (upperSelection.equals("Q")) {
                quit = true;
            }
        }
    
    }
}