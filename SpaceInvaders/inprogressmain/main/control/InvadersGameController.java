package control;

import model.*;
import view.*;

import java.util.Objects;
import java.util.Scanner;

import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * This class is part of the Model-View-Controller set up, situated in the middle.
 * @param logic is the logic object which itself contains all Shape objects
 * @param gui is the gui object that takes takes the drawable array object to handle display via Jframe window
 * @param text is text object which is used to display the text based version 
 * @param drawableObjects takes drawable shapes from logic and puts it in an array used by interface class
 */

public class InvadersGameController implements KeyListener{
    
	private Scanner keyboard = new Scanner(System.in);
    private InvadersGameLogic logic;
    private InvadersGameGUI gui;
    private InvadersGameText text;
    private Object[] drawableObjects = new Object[5]; 
        
     /**
     * Constructor for InvadersGameController which uses if branch to select what version of the game to start
     * calling on the appropriate play methods
     */

    InvadersGameController() {
    	
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
    private void initializeDrawableArray() {
		drawableObjects[0] = logic.getShip();
		drawableObjects[1] = logic.getShot();
		drawableObjects[2] = logic.getArray();
		drawableObjects[3] = logic.getAlienShot();
        drawableObjects[4] = logic.getBarriers();
    }
    
    /**
    *  This method starts and plays the GUI version of the game.
    */
    private void playGui(){
    	
        Timer timer = new Timer(40, e -> {
            if (Objects.equals(logic.getGameStatus(), "win") | Objects.equals(logic.getGameStatus(), "loss")){
                return;
            }
            updateStatus();
            logic.shotGeneration();
            gui.updateScreen(logic.getGameStatus());
            System.out.println(logic.getGameStatus());

        });
        
        timer.setInitialDelay(10);
        timer.start();
        gui.addKeyListener(this);
        System.out.println(logic.getGameStatus());
        if (Objects.equals(logic.getGameStatus(), "win") | Objects.equals(logic.getGameStatus(), "win")) timer.stop();
    }


    /**
     * The method updates the logic object by moving shapes
    */

    private void updateStatus() {
    	logic.moveAliens();
    	logic.moveAlienShot();
    	logic.handleShotInteraction();
    	logic.checkStatus();
    }

    /**
    *  This method starts and plays the text version of the game.
    */

    private void playText() {
    	boolean quit = false;
		text.createBoard();

    	while (!quit) {
    		
    		if (logic.getGameStatus().equals("continue")) { //if gamestatus is not equal to quit, the loop continues (plays the game)
        		text.drawCurrentState(logic.getShip(), logic.getShot(), 
        				logic.getAlienShot(), logic.getArray(), logic.getBarriers());//draws current state
        		
                System.out.print("Enter A for left, D for right, or F to shoot (Q to quit)"); 
                String selection = keyboard.nextLine().toUpperCase();

                switch (selection) {
                    case "Q":
                        quit = true;
                        System.out.println("You quit the game.");
                        break;
                    case "A":
                    case "D":  //movement
                        logic.shipMovement(selection);
                        break;
                    case "F":  //firing
                        logic.shotAttempt();
                        break;
                }
                
            	logic.shotGeneration();
                updateStatus();
    		}
            
            if (logic.getGameStatus().equals("win")) { //check status at the end to see if game has been won or lost, update quit
            	quit = true;
            	text.drawCurrentState(logic.getShip(), logic.getShot(), 
            			logic.getAlienShot(), logic.getArray(), logic.getBarriers());
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
