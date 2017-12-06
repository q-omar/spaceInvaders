package control;

class RunMe {
    
    public static void main (String[] args){
        javax.swing.SwingUtilities.invokeLater(InvadersGameController::new);
    }
}
/*this class takes action input from the controller, updates the objects, and then
	 passes those updated objects back to controller.
	 @param screenHeight, screenWidth are the dimensions of the board
	* @param gameStatus lets the program know if the game should continue or not.
	* @param gameVersion lets the constructor know whether the text and GUI wasnt 
	*/