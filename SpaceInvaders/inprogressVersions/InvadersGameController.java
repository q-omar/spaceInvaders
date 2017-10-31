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

//this class contains all ACTION events. The controller gives actions to the logic, 
//the logic updates locations and passes it back to controller, the controller
//then passes it to GUI which draws the locations. the process then repeats

public class InvadersGameController implements KeyListener{
    
    private InvadersGameLogic logic = new InvadersGameLogic();
    private InvadersGameGUI gui = new InvadersGameGUI(this, logic);
        
    public InvadersGameController(){
        Timer timer = new Timer(40, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                logic.checkStatus();
                logic.handleEvents();
                gui.updateScreen();
            }
        });
        
        timer.setInitialDelay(10);
        timer.start();
        init();
    }
    
    public void init(){
        gui.addKeyListener(this);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 'A':
			logic.getShip().moveLeft();
			break;
		case 'D':
			logic.getShip().moveRight();
			break;
		case KeyEvent.VK_SPACE:
			if (!logic.getShot().getShotFired()){
				logic.getShot().shotFired(true);
				logic.getShot().setShotColumn(logic.getShip().getLocation());
			}
			break;
		}
        
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}