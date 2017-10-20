import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class InvadersGameGUI extends JFrame{
	public InvadersGameGUI(){
		super("Space Invaders Game");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		JComponent component = new Component1();
	}
	
	public static void main (String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				InvadersGameGUI gui = new InvadersGameGUI();
				JComponent component = new Component1();
				gui.setSize(400,500);
				gui.setLocationRelativeTo(null);
				gui.add(component);
				gui.setVisible(true);
				
		
			}
		});
	}
	

		
}
