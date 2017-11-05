/** This class is an entry point to our program and creates an instance
 * of the game controller 
 */

public class RunMe {
    
    public static void main (String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new InvadersGameController();
            }
        });
    }
}