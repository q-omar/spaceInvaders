package view;

import java.io.*;
import model.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.sql.Timestamp;


/**
* This class handles all drawing for the game. It first creates a frame and then a canvas
* is painted, containing the logic that is passed by the controller. This logic is then used
* to paint exact locations of the objects.
*/

public class InvadersGameGUI extends JFrame {
    
    private final long startTime = System.currentTimeMillis();
    private int windowWidth = 400;
    private int windowHeight = 500;
    private String gameStatus = "continue";
    private Object[] toDraw;
    

    /*
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
        canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
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
    public void updateScreen(String newGameStatus) {
    	gameStatus = newGameStatus;
        repaint();
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
        String duration(){
            final long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            duration = duration/1000;
            String durationAsString = Long.toString(duration);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            File file = new File("scoreLog.txt");
            try{            
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file,true))); //the true will append the new data
                pw.println(timestamp);
                pw.println("End game report: "+gameStatus);
                pw.println("Your time was: "+durationAsString+"s");
                pw.println();
                pw.close();
            }
            catch(IOException ioe){}
            
            return durationAsString;
        }
        
        public void paintComponent(Graphics g){

            super.paintComponent(g);
            // Draw background
            g.setColor(Color.BLACK);
            g.fillRect(0,0,windowWidth,windowHeight);
            g.setFont(new Font("Arial", Font.BOLD, 36));

			// Draws end game screen win or loss
            g.setColor(Color.WHITE);
            switch (gameStatus) {
                case "win":
                    g.drawString("Your time was: " + duration() + "s", windowWidth / 6, windowHeight / 2);
                    g.drawString("YOU WON!", windowWidth / 3, windowHeight / 3);

                    break;
                case "loss":
                    g.drawString("Your time was: " + duration() + "s", windowWidth / 6, windowHeight / 2);
                    g.drawString("GAME OVER", windowWidth / 3, windowHeight / 3);
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
