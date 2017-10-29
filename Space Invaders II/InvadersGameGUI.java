import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.*;

import java.awt.event.*;
import java.awt.Dimension;

/**
* This class acts as the controller for the GUI version of the Invaders Game. It responds to
* keyboard input and timer events.
*/

public class InvadersGameGUI implements KeyListener {

    private int windowWidth = 400;
    private int windowHeight = 500;

    private playerShot shot = new playerShot(420, 20, 5, 20);
    private playerShip ship = new playerShip(windowWidth, 10);
    private AlienArray alienInvaders= new AlienArray();
    private InvadersGameScreen screen = new InvadersGameScreen();

    private String gameStatus = "continue";


    public InvadersGameGUI() {
		Timer timer = new Timer(40,
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					alienInvaders.aliensMovement(windowWidth);
                    checkStatus();

                    if (shot.getShotFired()) {
                        shot.moveShot();
                        shot.inBounds();

                        for (int r=0; r<alienInvaders.rowsAliens ; r++) {
                            for (int c=0; c<alienInvaders.numAliens; c++){
                                
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


	public void init(){
        screen.addKeyListener(this);
    }

    public void updateScreen() {
        screen.repaint();
    }

    public void checkStatus() { // Check win/loss conditions

        if (!gameStatus.equals("loss")) {

            gameStatus = "win";

            for (int r=0; r<alienInvaders.rowsAliens ; r++) {
                for (int c=0; c<alienInvaders.numAliens; c++){
                            
                    if (alienInvaders.aliens[r][c].isAlive()) {
                        gameStatus = "continue";
                    }
                }
            }
        }
        
        if (!gameStatus.equals("win")) {

            for (int r=0; r<alienInvaders.rowsAliens ; r++) {
                for (int c=0; c<alienInvaders.numAliens; c++){
                    if (alienInvaders.aliens[r][c].isAlive() && alienInvaders.aliens[r][c].inBounds(420)) {
                        gameStatus = "loss";
                    }
                }
            }
        }
    }

    // Keyboard event handling
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

    /****** GUI interface ******/

    private class InvadersGameScreen extends JFrame {

        public InvadersGameScreen() {

            super("Space Invaders Game");
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);

            Canvas canvas = new Canvas();

            /* Code from Stack Overflow answer (https://stackoverflow.com/questions/
            * 6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly) */
            canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
            this.getContentPane().add(canvas);
            this.pack();

            this.setVisible(true);
        
        }

        private class Canvas extends JComponent{
			
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

    // Main method

    public static void main (String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                InvadersGameGUI game = new InvadersGameGUI();
            }
        });
    }

}