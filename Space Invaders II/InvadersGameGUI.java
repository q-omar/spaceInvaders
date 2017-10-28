import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

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

    private playerShot shot = new playerShot(0, 5, 10, 30);
    private playerShip ship = new playerShip(windowWidth, 3);
    private AlienArray alienInvaders= new AlienArray();
    private InvadersGameScreen screen = new InvadersGameScreen();


    public InvadersGameGUI() {
		Timer timer = new Timer(40,
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					alienInvaders.aliensMovement(windowWidth);
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

    // Keyboard event handling
    @Override
    public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 'A':
			ship.moveLeft(5);
			break;
		case 'D':
			ship.moveRight(5);
			break;
		case 'F':
			if (!shot.getShotFired()){
				shot.shotFired(true);
				shot.where(ship.getLocation());
			}else if(shot.getShotFired()){
				shot.moveShot();
				shot.inBounds();
				for (int r=0; r<alienInvaders.rowsAliens ; r++) {
					for (int c=0; c<alienInvaders.numAliens; c++){
						
						if (shot.checkHit(alienInvaders.aliens[r][c].getAlienX(), alienInvaders.aliens[r][c].getAlienY(), alienInvaders.aliens[r][c].getRadius())) {
							alienInvaders.aliens[r][c].destroyAlien();
							updateScreen();
						}
					}
				}
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

                // Draw background
                g.setColor(Color.BLACK);
                g.fillRect(0,0,windowWidth,windowHeight);
				
				ship.draw(g);
				
				alienInvaders.drawAlienArray(g);
                alienInvaders.aliensMovement(windowWidth);
				
                if (shot.getShotFired()) {
                    shot.draw(g);
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