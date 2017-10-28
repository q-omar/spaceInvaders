import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

import java.awt.event.*;
import java.awt.Dimension;

/**
* This class acts as the controller for the GUI version of the Invaders Game. It responds to
* keyboard input and timer events.
*/

public class InvadersGameGUI implements KeyListener {

    private int windowWidth = 400;
    private int windowHeight = 500;

    private playerShot shot = new playerShot(350, 5, 10, 30);
    private playerShip ship = new playerShip(windowWidth, 3);
    private Alien alien1= new Alien();
    boolean moveRight = true;

    private InvadersGameScreen screen = new InvadersGameScreen();


    public InvadersGameGUI() {
        screen.addKeyListener(this);
    }

    public void play() { //Placeholder

    }

    public void updateScreen() {
        screen.repaint();
    }

    // Keyboard EVENT handling
    @Override
    public void keyPressed(KeyEvent e) {  
        if (e.getKeyCode() == 'F' && !shot.getShotFired()) { // Press F to make a shot
            shot.shotFired(true);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && shot.getShotFired()) { // For now, move the bullet up with space
            shot.moveShot();
            shot.inBounds();
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

        //DRAWING happens here, screen gets updated here

        public void alienMovement(){
            if (moveRight){

                if (alien1.getAlienX()>300){
                    alien1.moveDown();
                
                    moveRight = false;
                    return;
                }
                alien1.moveRight();
                System.out.println(alien1.getAlienX());
                
            }
            else {
                System.out.println(alien1.getAlienX());
                if (alien1.getAlienX()<30){
                    
                    alien1.moveDown();

                    moveRight = true;
                    return;
                }
                alien1.moveLeft();
                
            }

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
                alienMovement();
                

                g.setColor(Color.GREEN);
                g.fillOval(xcoord,ycoord,45,40);

                // Draw ship
                int xcoordShip= ship.getLocation();
                g.setColor(Color.WHITE);
                g.fillRect(xcoordShip-25,450,50,50);

                // Draw bullet
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

