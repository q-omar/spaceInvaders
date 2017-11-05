
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//this class contains all ACTION events. The controller gives actions to the logic, 
//the logic updates locations and passes it back to controller, the controller
//then passes it to GUI which draws the locations. the process then repeats

public class InvadersGameController implements KeyListener{
    
    private InvadersGameLogic logic = new InvadersGameLogic("GUI");
    private InvadersGameGUI gui = new InvadersGameGUI(this, logic);
    private InvadersGameLogic logicText = new InvadersGameLogic("Text");
    private InvadersGameText text = new InvadersGameText(logic);
        
    public InvadersGameController(){
        Timer timer = new Timer(40, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                logic.moveAliens();
                logic.checkStatus();
                logic.handleShotInteraction();
                gui.updateScreen();
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
        gui.addKeyListener(this);
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
    
    //adjust this method to fit with the keyevents above 
    public void handleEvents(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter A for left, D for right, or F to shoot (Q to quit)"); 
        String selection = keyboard.nextLine(); 
        String upperSelection = selection.toUpperCase();

        if (upperSelection.equals("A") || upperSelection.equals("D")) {
            ship.shipMovement(upperSelection);
            ship.inBounds(boardWidth);    
            
        } else if (upperSelection.equals("F")) {
            handleShot("part1");
            
        } else if (upperSelection.equals("Q")) {
            quit = true;
            System.out.println("You quit the game.");
        }

        handleShot("part2");
        alienInvaders.aliensMovement(boardWidth - 4);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}