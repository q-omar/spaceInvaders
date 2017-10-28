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
	private boolean shipRight;
	private boolean shipLeft;
    private int windowWidth = 400;
    private int windowHeight = 500;


    private playerShot shot = new playerShot(350, 5, 10, 30);
    private playerShip ship = new playerShip(windowWidth, 3);
    private Alien alien1= new Alien();
    private InvadersGameScreen screen = new InvadersGameScreen();
	
	public InvadersGameGUI() {
        
        Timer timer = new Timer(60,
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    update();
                }
            });
        timer.setInitialDelay(200);
        timer.start();
		init();
    }
    public void init() {
        screen.addKeyListener(this);
		//new Thread(this).start();
		
    }

	
    public void update() {
		if (shipRight){
			ship.moveRight();
			ship.inBounds(windowWidth);
		}
		
		if (shipLeft){
			ship.moveLeft();
			ship.inBounds(windowWidth);
		}
		screen.repaint();
    }
	

    // Keyboard event handling
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 'F' && !shot.getShotFired()) { // Press F to make a shot
            shot.shotFired(true);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && shot.getShotFired()) { // For now, move the bullet up with space
            shot.moveShot();
            shot.inBounds();
        }
		if (e.getKeyCode() == KeyEvent.VK_A){
			shipLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D){
			shipRight = true;
		}

            
    }

    @Override
    public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A){
			shipLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D){
			shipRight = false;
		}
	}

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

                // Draw aliens
                int xcoord = alien1.getAlienX();
                int ycoord= alien1.getAlienY();

                g.setColor(Color.GREEN);
                g.fillOval(xcoord,ycoord,45,40);

                // Draw ship
				ship.draw(g);

                // Draw bullet
                if (shot.getShotFired()) {
                    shot.draw(g);
                }

            }
    
        }

    }


	public static void main(String[] args){
		 InvadersGameGUI game = new InvadersGameGUI();
	}
			
}
