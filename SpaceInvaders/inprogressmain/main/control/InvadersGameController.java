package control;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import model.*;
import view.*;
import java.util.Scanner;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * This class is part of the Model-View-Controller set up, situated in the middle.
 */

class InvadersGameController implements KeyListener{
    private long startTime = System.currentTimeMillis();
    private final Scanner keyboard = new Scanner(System.in);
    private InvadersGameLogic logic;
    private InvadersGameGUI gui;
    private InvadersGameText text;
    private final Object[] drawableObjects = new Object[5];
    private Scores scores = new Scores();
        
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
            checkScores(); //currently, scores are executed from the moment the program runs to when you press G which is not correct
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
            if (logic.getGameStatus() == "win" | logic.getGameStatus() == "loss"){
                System.out.println("gamestatus 1: "+logic.getGameStatus()); //tried to put checkscores here but it keeps looping 
                return;
            }
            updateStatus();
            logic.shotGeneration();
            gui.updateScreen(logic.getGameStatus());
        });
        
        timer.setInitialDelay(10);
        timer.start();
        gui.addKeyListener(this);
        if (logic.getGameStatus() == "win" | logic.getGameStatus() == "loss"){          
            System.out.println("gamestatus 2: "+logic.getGameStatus());  //also tried here but this block isnt ever reached 
            timer.stop();  
        } 
    }

    private void checkScores(){
        final long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        duration = duration/1000;
        String durationAsString = Long.toString(duration);

        scores.readScores();
        scores.addLastScore(durationAsString);
        scores.sortScores();
        scores.buildScores();        
        scores.writeScores();
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
