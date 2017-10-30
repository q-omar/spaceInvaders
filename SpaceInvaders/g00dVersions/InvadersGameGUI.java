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
public class InvadersGameGUI implements KeyListener {

    private int windowWidth = 400;
    private int windowHeight = 500;

    private playerShot shot = new playerShot(420, 20, 5, 20);
    private playerShip ship = new playerShip(windowWidth, 10);
    private AlienArray alienInvaders= new AlienArray("GUI");
    private InvadersGameScreen screen = new InvadersGameScreen();

    private String gameStatus = "continue";

    /**
    *  Constructor for InvadersGameGUI which creates the game's timer and begins the game. It updates coordinates and status for all the objects when
    *  the timer is activated. It also checks if the game has been won or lost.
    */
    public InvadersGameGUI() {
		Timer timer = new Timer(40,
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					alienInvaders.aliensMovement(windowWidth);
                    checkStatus();

                    if (shot.getShotFired()) {
                        shot.moveShot();
                        shot.inBounds();

                        for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                            for (int c=0; c<alienInvaders.getNumAliens(); c++){
                                
                                if (alienInvaders.aliens[r][c].isAlive() && shot.checkHit(alienInvaders.aliens[r][c].getAlienX(), alienInvaders.aliens[r][c].getAlienY(), alienInvaders.aliens[r][c].getRadius())) {    
                                    alienInvaders.aliens[r][c].destroyAlien();
                                    checkStatus();
                                    updateScreen();
                                }
                            }
                        }
                    }
					updateScreen();
				}
			});
		timer.setInitialDelay(10);
		timer.start();
		init();
	}

    /**
    *  This method initializes the screen by making it responsive to user keyboard input.
    */
	public void init(){
        screen.addKeyListener(this);
    }

    /**
    *  This method calls the screen to be repainted.
    */
    public void updateScreen() {
        screen.repaint();
    }

    /**
    *  This method checks whether or not the game has been won or lost and updates the boolean gameStatus appropriately.
    */
    public void checkStatus() {

        if (!gameStatus.equals("loss")) {
            gameStatus = "win";

            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                            
                    if (alienInvaders.aliens[r][c].isAlive()) {
                        gameStatus = "continue";
                    }
                }
            }
        }
        
        if (!gameStatus.equals("win")) {
            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                    if (alienInvaders.aliens[r][c].isAlive() && alienInvaders.aliens[r][c].inBounds(410)) {
                        gameStatus = "loss";
                    }
                }
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
			ship.moveLeft();
			break;
		case 'D':
			ship.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			if (!shot.getShotFired()){
				shot.shotFired(true);
				shot.setShotColumn(ship.getLocation());
			}
			break;
		}
        updateScreen();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
    *  This class extends JFrame and draws the window for the invaders game.
    */
    private class InvadersGameScreen extends JFrame {

        /**
        *  The InvadersGameScreen constructor initializes the frame and the "canvas" component which is painted on.
        */
        public InvadersGameScreen() {

            super("Space Invaders Game");
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);

            Canvas canvas = new Canvas();

            /** Code from Stack Overflow answer (https://stackoverflow.com/questions/
            * 6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly) 
			*/
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

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 36));

                if (gameStatus.equals("win")) {
                    g.drawString("YOU WON!", 112,200);

                } else if (gameStatus.equals("loss")) {
                    g.drawString("GAME OVER", 98,200);

                } else {

                    ship.draw(g);
                
                    alienInvaders.drawAlienArray(g);
                
                    if (shot.getShotFired()) {
                        shot.draw(g);
                    }
                }
				
            }
    
        }

    }

    /**
    *  The main method creates a new instance of InvadersGameGUI which starts the game.
    */
    public static void main (String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                InvadersGameGUI game = new InvadersGameGUI();
            }
        });
    }

}
