package control;

/**
 * This class contains the main method that creates and runs an instance of InvadersGameController,
 * which will launch the game.
 */
class RunMe {
    
    public static void main (String[] args){
        javax.swing.SwingUtilities.invokeLater(InvadersGameController::new);
    }
}