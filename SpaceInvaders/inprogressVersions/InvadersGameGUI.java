import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

/**
* This class acts as the controller for the GUI version of the Invaders Game. It responds to
* keyboard input and timer events. It contains the InvadersGameScreen class which draws the actual frame
* using the coordinates of the objects contained in InvadersGameGUI.
*/



//>>this class takes the 'updated' locations by the controller and handles all the drawing<<
public class InvadersGameGUI extends JFrame {
    
    private int windowWidth = 400;
    private int windowHeight = 500;

    public void updateScreen() {
        repaint();
    }

    /*
    *  The InvadersGameScreen constructor initializes the frame and the "canvas" component which is painted on.
    */
    public InvadersGameGUI(KeyListener listener, InvadersGameLogic logic) {

        super("Space Invaders Game");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        Canvas canvas = new Canvas(logic);

        /* Code from Stack Overflow answer (https://stackoverflow.com/questions/
        * 6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly) */
        canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.getContentPane().add(canvas);
        this.pack();

        this.setVisible(true);
    }

    /**
    *  The Canvas class is a JComponent with an overridden paintComponent method allowing one to draw
    *  objects on it.
    */
    private class Canvas extends JComponent{
        private InvadersGameLogic logic;
        /**
        *  This method draws the background, aliens, player ship and bullets onto the screen by calling
        *  the draw methods of the appropriate object. It also draws the win and game over screens
        *  based on gameStatus.
        *  @param  g  the grpahics object
        */
        
        public Canvas(InvadersGameLogic inputLogic){
            logic = inputLogic;
        }
        @Override
        public void paintComponent(Graphics g){

            super.paintComponent(g);

            // Draw background
            g.setColor(Color.BLACK);
            g.fillRect(0,0,windowWidth,windowHeight);

            g.setColor(Color.WHITE);
            if (logic.getGameStatus().equals("win")) {
                g.drawString("YOU WON!", 112,200);

            } else if (logic.getGameStatus().equals("loss")) {
                g.drawString("GAME OVER", 98,200);

            }
            else{ 

                logic.getShip().draw(g);
                logic.getArray().drawAlienArray(g);
            
                if (logic.getShot().getShotFired()) {
                    logic.getShot().draw(g);
                }
            }
            
        }

    }

}
