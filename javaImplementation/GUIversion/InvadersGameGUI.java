import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

import java.awt.event.*;

/**
* This class acts as the controller for the GUI version of the Invaders Game. It responds to
* keyboard input and timer events.
*/

public class InvadersGameGUI implements KeyListener {

	private int windowWidth = 400;
	private int windowHeight = 500;

	private playerShot shot = new playerShot();
	private playerShip ship = new playerShip();
	private Alien alien1= new Alien();
	private InvadersGameScreen screen = new InvadersGameScreen();


	public InvadersGameGUI() {
		screen.addKeyListener(this);
	}

	public void play() { //Placeholder

	}

	public void updateScreen() {
		screen.repaint();
	}

	// Keyboard event handling
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'f') {
			shot.moveShot();
			updateScreen();
		}
			
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

			this.setSize(windowWidth,windowHeight);

			this.setLocationRelativeTo(null);

			Canvas canvas = new Canvas();

			this.add(canvas);

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
				int xcoordShip= ship.getLocation();

				g.setColor(Color.WHITE);
				g.fillRect(xcoordShip-50,400,50,50);

				// Draw bullet
				g.setColor(Color.RED);
				g.fillRect(shot.getShotColumn(),shot.getShotRow(), shot.getWidth(), shot.getLength());

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

