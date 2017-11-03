import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.KeyListener;


/**
* This class handles all drawing for the game. It first creates a frame and then a canvas
* is painted, containing the logic that is passed by the controller. This logic is then used
* to paint exact locations of the objects.
*/

public class InvadersGameGUI extends JFrame {
    
    private int windowWidth = 400;
    private int windowHeight = 500;

    /**
    *  This method calls the screen to be repainted.
    */

    public void updateScreen() {
        repaint();
    }

    /*
    *  The InvadersGameGUI constructor initializes the frame and the "canvas" component which is painted on.
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

        /**
            *  This method draws the background, aliens, player ship and bullets onto the screen by calling
            *  the draw methods of the appropriate object. It also draws the win and game over screens
            *  based on gameStatus.
            *  @param  g  the grpahics object
            */
        @Override
        public void paintComponent(Graphics g){

            super.paintComponent(g);

            // Draw background
            g.setColor(Color.BLACK);
            g.fillRect(0,0,windowWidth,windowHeight);
            g.setFont(new Font("Arial", Font.BOLD, 36));

            g.setColor(Color.WHITE);
            if (logic.getGameStatus().equals("win")) {
                g.drawString("YOU WON!", windowWidth/3,windowHeight/2);

            } else if (logic.getGameStatus().equals("loss")) {
                g.drawString("GAME OVER", windowWidth/3,windowHeight/2);

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
