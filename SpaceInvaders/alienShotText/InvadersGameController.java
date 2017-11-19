import java.util.Scanner;

import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
This class plays either the text version or GUI version of the game. It contains all ACTION events.
If the GUI version is chosen by the user in main, the controller calls actions to the gui version
constructor of the logic, which updates locations and passes it back to controller, the controller then 
passes it to GUI which draws the locations. This process repeats until a win or loss.
If the text version is chosen, the controller calls the text version constructor of the logic, initalizes
the class that prints the text board (InvadersGameText), and then plays the text version of the game 
using calls to print and update ship/shot/alien variables through the logic class and text class
*/
public class InvadersGameController implements KeyListener{
    
	private Scanner keyboard = new Scanner(System.in);
    private InvadersGameLogic logic;
    private InvadersGameGUI gui;
    private InvadersGameText text;
        
    public InvadersGameController() {
    	
    	System.out.println("Let's play the InvadersGame!\nEnter T to launch the text version or G to launch the GUI version.");
    	String input = keyboard.nextLine().toUpperCase();
    	
    	if (input.equals("G")) {
    		logic = new InvadersGameLogic("GUI");
    		gui = new InvadersGameGUI(logic);
    		playGui();
    	} else if (input.equals("T")) {
    		logic = new InvadersGameLogic("TEXT");
    		text = new InvadersGameText();
    		playText();
    	}
    }
    
    /*********
    method: playText
			This method starts and plays the GUI version of the game. Handling win/loss conditions and movement of
			aliens and shots in the Timer and the movement of the ship outside the timer using a key listener.
    *********/
    public void playGui(){
    	
        Timer timer = new Timer(40, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                logic.moveAliens();
                logic.checkStatus();
                logic.handleShotInteraction();
                logic.alienShots(); 
                gui.updateScreen();
            }
        });
        
        timer.setInitialDelay(10);
        timer.start();
    	gui.addKeyListener(this);
    }
	
	/******
	method: playText
			Plays the text version of the game. Uses conditionals to take input from user, calling 
			methods from the logic class depending on the input to update ship/shot and checking for
			a quit input. Calls methods from logic class to update aliens and shot interactions,
			and calls a method (drawCurrentState) from InvadersGameText to draw the board onto screen
				
	*******/
    
    public void playText() {
    	boolean quit = false;

    	while (!quit) {
    		
    		if (logic.getGameStatus().equals("continue")) {
        		text.drawCurrentState(logic.getShip(), logic.getShot(), logic.getArray(), logic.getAlienShots());
        		
                System.out.print("Enter A for left, D for right, or F to shoot (Q to quit)"); 
                String selection = keyboard.nextLine().toUpperCase(); 
                
                if (selection.equals("Q")) {
                    quit = true;
                    System.out.println("You quit the game.");
                } else if (selection.equals("A") || selection.equals("D")) {
                    logic.shipMovement(selection);
                } else if (selection.equals("F")) {
                    logic.shotAttempt();
                }
                
				logic.alienShots();
                logic.handleShotText();
				logic.moveAliens();
    		}
    	
            logic.checkStatus();
            
            if (logic.getGameStatus().equals("win")) {
            	quit = true;
            	System.out.println("You won!");
            } else if (logic.getGameStatus().equals("loss")) {
            	quit = true;
            	System.out.println("The aliens got you!");
            }
    	}
     }
    
     /********
    method: keyPressed
	*		This method responds to keyboard input and updates the screen. When the user presses A/D, the ship moves left or right.
    *  		When the user presses the space key, a bullet will be fired if one is not already active.
    *  		@param  e  a key pressed by the user
    *******/

    @Override
    public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 'A':
			logic.shipMovement("A");
			break;
		case 'D':
			logic.shipMovement("D");
			break;
		case KeyEvent.VK_SPACE:
			logic.shotAttempt();
			break;
		}
		gui.updateScreen();
        
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    


}