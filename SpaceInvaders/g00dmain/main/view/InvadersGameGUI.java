package view;

import model.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
* This class handles all drawing for the game. It first creates a frame and then a canvas
* is painted, containing the logic that is passed by the controller. This logic is then used
* to paint exact locations of the objects.
*/

public class InvadersGameGUI extends JFrame {
    
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 500;
    private String gameStatus = "continue";
    private Object[] toDraw;
    private ArrayList<Integer> scores = new ArrayList<>();
    private int playerScore;
    
    /**
    *  The InvadersGameGUI constructor initializes the frame and the "canvas" component which is painted on.
    */
    public InvadersGameGUI(Object[] drawableObjects) {

        super("Space Invaders Game");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        toDraw = drawableObjects;

        Canvas canvas = new Canvas();

        /*Code from Stack Overflow answer (https://stackoverflow.com/questions/
        * 6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly) 
		*/
        canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.getContentPane().add(canvas);
        this.pack();
        
        this.setVisible(true);
        getContentPane().setFocusable(true);
        requestFocusInWindow();
    }

    /**
    *  This method calls the screen to be repainted and also updates the game status
    *  in case the game ends.
    */
    public void updateScreen(String newGameStatus, Object[] drawableObjects) {
    	toDraw = drawableObjects;
    	gameStatus = newGameStatus;
    
        repaint();
    }
    /** This method updates the score everytime it is called
    * @param allScores is the list of top ten scores
    * @param newPlayerScore is the new score to be added
    */
    public void updateScores(ArrayList<Integer> allScores, int newPlayerScore) {
    	scores = allScores;
    	playerScore = newPlayerScore;
    }
    
    /** This method displays the error message 
    */
    public void displayError(String message) {
    	JOptionPane.showMessageDialog(this, message, "Error occurred", JOptionPane.ERROR_MESSAGE);
    }

    /**
    *  The Canvas class is a JComponent with an overridden paintComponent method allowing one to draw
    *  objects on it.
    */
    private class Canvas extends JComponent{
        /**
        *  This method draws the background, aliens, player ship and bullets onto the screen by calling
        *  the draw methods of the appropriate object. It also draws the win and game over screens
        *  based on gameStatus.
        *  @param  g  the graphics object
        */
        public void paintComponent(Graphics g){

            super.paintComponent(g);
            // Draw background
            g.setColor(Color.BLACK);
            g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setFont(new Font("Arial", Font.BOLD, 36));

			// Draws end game screen win or loss
            g.setColor(Color.WHITE);
            switch (gameStatus) {
                case "win":
                	g.drawString("YOU WON!", WINDOW_WIDTH/3, WINDOW_HEIGHT*3/25);
                    g.drawString("Your time was: " + playerScore + "s", WINDOW_WIDTH/4, WINDOW_HEIGHT/5);
                    g.drawString("High Scores:", WINDOW_WIDTH/3, WINDOW_HEIGHT/3);
                    
                    g.setFont(new Font("Arial", Font.BOLD, 28));
                    
                    int y = WINDOW_HEIGHT*2/5;
                    for (int index = 0; index < scores.size(); index++) {
                    	String indexAsString = Integer.toString(index + 1);
                    	String scoreAsString = Integer.toString(scores.get(index));
                    	g.drawString(indexAsString + ": " + scoreAsString + "s", WINDOW_WIDTH*2/5, y);
                    	
                    	y += 30;
                    }
                    
                    break;
                case "loss":
                    g.drawString("GAME OVER", WINDOW_WIDTH / 3, WINDOW_HEIGHT / 3);
                    break;
                    
                default:

                    for (Object obj : toDraw) {
                        if (obj instanceof Shape) {   // This will draw the ship and shot
                            Shape aShape = (Shape) obj;
                            aShape.draw(g);
  
                        } else if (obj instanceof AlienArray) {  // This draws the aliens
                            AlienArray anAlienArray = (AlienArray) obj;
                            anAlienArray.drawAlienArray(g);

                        } else if (obj instanceof BarrierArray) { // This draws the barriers
                            BarrierArray aBarrierArray = (BarrierArray) obj;
                            aBarrierArray.drawBarrierArray(g);
                        }
                    }

                    break;
            }
            
        }

    }

}
