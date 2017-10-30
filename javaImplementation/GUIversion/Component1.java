import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;


import javax.swing.JComponent;

public class Component1 extends JComponent{


	public void paintComponent (Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,400,500);
		
		Alien alien1= new Alien();
		int xcoord = alien1.getAlienX();
		int ycoord= alien1.getAlienY();

		g.setColor(Color.GREEN);
		g.fillOval(xcoord,ycoord,45,40);


		playerShip ship = new playerShip();
		int xcoordShip= ship.getLocation();

		g.setColor(Color.WHITE);
		g.fillRect(xcoordShip-50,400 ,50,50);
		
		

	}
	
	


}
