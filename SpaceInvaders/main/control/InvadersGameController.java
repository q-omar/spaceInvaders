package control;

import model.*;
import view.*;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is part of the Model-View-Controller set up, situated in the middle.
 * Controls the flow of the game and communicates between logic and view classes.
 * @param logic is the logic object which itself contains all Shape objects
 * @param gui is the gui object that takes takes the drawable array object to handle display via Jframe window
 * @param text is text object which is used to display the text based version 
 * @param drawableObjects takes drawable shapes from logic and puts it in an array used by interface class
 */

class InvadersGameController implements KeyListener{
    
	private final Scanner keyboard = new Scanner(System.in);
	private long startTime;
	private Timer timer;
	
    private InvadersGameLogic logic;
    private InvadersGameGUI gui;
    private InvadersGameText text;
    private final Object[] drawableObjects = new Object[5];
    private final Scores scores = new Scores();
        
     /**
     * Constructor for InvadersGameController which uses if branch to select what version of the game to start
     * calling on the appropriate play methods. If the GUI version is selected, it checks for all image and 
     * audio files. If the files do not exist, an error message is displayed.
     */
    InvadersGameController() {
    	System.out.println("Let's play the InvadersGame!\nEnter T to launch the text version or G to launch the GUI version.");
    	String input = keyboard.nextLine().toUpperCase();
    	
    	if (input.equals("G")) {
    		
        	if (new File("a.png").exists()
        			&& new File("destroy.wav").exists()
        			&& new File("kill.wav").exists()
        			&& new File("shoot.wav").exists()
        			&& new File("shot.wav").exists()
        			&& new File("ship.jpg").exists()) {
        		
        		logic = new InvadersGameLogic("GUI");
        		updateDrawableArray();
        		gui = new InvadersGameGUI(drawableObjects);
        		playGui();
        		
        	} else {
        		gui = new InvadersGameGUI(drawableObjects);
        		gui.displayError("Files are missing or corrupt. Please reinstall the game.");
        		gui.dispose();
        	}
    		
    	} else if (input.equals("T")) {
    		logic = new InvadersGameLogic("TEXT");
    		text = new InvadersGameText();
    		playText();
    	}
    }
    
    /**
     * Adds all drawable objects to the drawable array.
     */
    private void updateDrawableArray() {
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
    	
    	timer = new Timer(40, timerListener);
        timer.setInitialDelay(10);
        timer.start();
        startTime = System.currentTimeMillis();
        gui.addKeyListener(this);

    }
    
    /** Defining action listener for the timer separate from where the timer is defined
    * The timer is now an instance variable so this method can call timer.stop()
    */
    private final ActionListener timerListener = new ActionListener() {
    /** This method keeps track of the timer and also sends time elapsed to the score class
    * and updates the score
    * 
    * @param e of class ActionEvent
    */
    	public void actionPerformed(ActionEvent e) {

            if (logic.getGameStatus().equals("win")){
            	final long endTime = System.currentTimeMillis();
            	int duration = (int) ((endTime - startTime)/1000);
            	
            	try {
            		scores.addLastScore(duration, "GUI"); // Send the time elapsed to the score class
                	gui.updateScores(scores.getScores(), duration);
            	} catch (IOException ioe) {
            		gui.displayError("Input/output error occurred when retrieving high scores. Please exit the game.");
            	}
            	
            	timer.stop();

            } else if (logic.getGameStatus().equals("loss")) {
            	timer.stop();
            }
            
            updateStatus();
            logic.shotGeneration();
            updateDrawableArray();
            gui.updateScreen(logic.getGameStatus(), drawableObjects);
    	}
    };

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
        int count = 0;
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
            count++;
            
            if (logic.getGameStatus().equals("win")) { //check status at the end to see if game has been won or lost, update quit
            	
            	quit = true;
            	text.drawCurrentState(logic.getShip(), logic.getShot(), 
            			logic.getAlienShot(), logic.getArray(), logic.getBarriers());
            	
            	System.out.println("You won!");
            	System.out.println("Your time was: " + count + " turns!");// prints how many turns it took to win
            	
            	try {
            		scores.addLastScore(count, "text");
                	System.out.println("Previous high scores:");// prints previous 3 high scores
                	ArrayList<Integer> highScores = scores.getScores();
                	for (int i = 0; i < highScores.size(); i ++) {
                		System.out.println((i+1) + ": " + highScores.get(i));
                	}
                	
            	} catch (IOException ioe) {
            		System.out.println("Input/output error occurred while retrieving high scores.");
            	}
            	
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
		updateDrawableArray();
		gui.updateScreen(logic.getGameStatus(), drawableObjects);   
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    


}
