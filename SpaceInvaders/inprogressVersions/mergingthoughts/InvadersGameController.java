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
    //private InvadersGameLogic logicText = new InvadersGameLogic("Text");
        
    public InvadersGameController() {
    	
    	System.out.println("Let's play the InvadersGame!\nEnter T to launch the text version or G to launch the GUI version.");
    	String input = keyboard.nextLine().toUpperCase();
    	
    	if (input.equals("G")) {
    		logic = new InvadersGameLogic("GUI");
    		gui = new InvadersGameGUI(logic);
    		playGui();
    	} else if (input.equals("T")) {
    		logic = new InvadersGameLogic("Text");
    		playText();
    	}
    }
    
    /**
    *  This method starts and plays the GUI version of the game.
    */
    public void playGui(){
    	
        Timer timer = new Timer(40, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                logic.moveAliens();
                logic.checkStatus();
                logic.handleShotInteraction();
                gui.updateScreen();
            }
        });
        
        timer.setInitialDelay(10);
        timer.start();
    	gui.addKeyListener(this);
    }
    
    // Since there are some of the same steps in both of these "play" methods we can probably reduce code repetition more
    
    public void playText() { // Plays text version of the game, need to implement board printing
    	boolean quit = false;

    	while (!quit) {
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
            
            logic.handleShotInteraction();
            logic.moveAliens();;
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
		gui.updateScreen();
        
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    


}