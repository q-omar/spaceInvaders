import java.util.Scanner;

import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//this class contains all ACTION events. The controller gives actions to the logic, 
//the logic updates locations and passes it back to controller, the controller
//then passes it to GUI which draws the locations. the process then repeats

public class InvadersGameController implements KeyListener{
    
	private Scanner keyboard = new Scanner(System.in);
    private InvadersGameLogic logic;
    private InvadersGameGUI gui;
    private InvadersGameText text;
    private Object[] drawableObjects = new Object[3]; 
        
    public InvadersGameController() {
    	
    	System.out.println("Let's play the InvadersGame!\nEnter T to launch the text version or G to launch the GUI version.");
    	String input = keyboard.nextLine().toUpperCase();
    	
    	if (input.equals("G")) {
    		logic = new InvadersGameLogic("GUI");
    		initializeDrawableArray();
    		gui = new InvadersGameGUI(drawableObjects);
    		
    		playGui();
    	} else if (input.equals("T")) {
    		logic = new InvadersGameLogic("TEXT");
    		text = new InvadersGameText();
    		initializeDrawableArray();

    		playText();
    	}
    }
    
    /**
     * Adds all drawable objects to the drawable array.
     */
    public void initializeDrawableArray() {
		drawableObjects[0] = logic.getShip();
		drawableObjects[1] = logic.getShot();
		drawableObjects[2] = logic.getArray();
    }
    
    /**
    *  This method starts and plays the GUI version of the game.
    */
    public void playGui(){
    	
        Timer timer = new Timer(40, new ActionListener(){
            public void actionPerformed(ActionEvent e){
             
            	updateStatus();
                gui.updateScreen(logic.getGameStatus());
            }
        });
        
        timer.setInitialDelay(10);
        timer.start();
    	gui.addKeyListener(this);
    }


    // Steps shared between logic and GUI
    public void updateStatus() {
    	logic.moveAliens();
    	logic.handleShotInteraction();
    	logic.checkStatus();
    }
    
    public void playText() { 
    	boolean quit = false;
		text.createBoard();

    	while (!quit) {
    		
    		if (logic.getGameStatus().equals("continue")) {
        		text.drawCurrentState(logic.getShip(), logic.getShot(), logic.getArray());
        		
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
                
                updateStatus();
    		}
            
            if (logic.getGameStatus().equals("win")) {
            	quit = true;
            	text.drawCurrentState(logic.getShip(), logic.getShot(), logic.getArray());
            	System.out.println("You won!");
            } else if (logic.getGameStatus().equals("loss")) {
            	quit = true;
            	System.out.println("Game over, the aliens got you!");
            }
 
    	}
     }
    
     /**
    *  This method responds to keyboard input and updates the screen. When the user presses A/D, the ship moves left or right.
    *  When the user presses the space key, a bullet will be fired if one is not already active.
    *  @param  e  a key pressed by the user
    */

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
		gui.updateScreen(logic.getGameStatus());
        
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    


}
