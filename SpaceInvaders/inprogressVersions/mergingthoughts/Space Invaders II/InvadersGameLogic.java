public class InvadersGameLogic{
	/**this class takes action input from the controller, updates the objects, and then
	* passes those updated objects back to controller.
	* @param screenHeight, screenWidth are the dimensions of the board
	* @param gameStatus lets the program know if the game should continue or not.
	* @param gameVersion lets the constructor know whether the text and GUI wasnt 
	*/

    private int screenHeight = 500;
    private int screenWidth = 400;
    
    private PlayerShot shot;
    private PlayerShip ship;
    private AlienArray alienInvaders;
    private String gameStatus = "continue";
    private String gameVersion;
	
	/** InvadersGameLogic constructor takes a string agruement and implements the GUI or Text-based version of the game. 
	* This is required beacuse the text and GUI versions have different board dimensions and alien arrays are implemented differently.
	*/

    public InvadersGameLogic(String version){

        if (version.equals("GUI")){
        	gameVersion = version;
            screenHeight = 500;
            screenWidth = 400;
            ship = new PlayerShip(screenWidth, screenHeight - 60, 20, 5);  // Temporarily using same constructors as before, update later to correspond to version
            shot = new PlayerShot(420, 20, 5, 20);
            alienInvaders = new AlienArray("GUI");

        } else if (version.equals("TEXT")) {
        	gameVersion = version;
            screenWidth = 60;
            screenHeight = 30;
            ship = new PlayerShip(screenWidth, screenHeight-1, 0, 3); 
            shot = new PlayerShot(screenHeight-3, 5);
            alienInvaders = new AlienArray("Text");
        }
    }
    /**
     * getter methods to be used by controller and GUI classes 
     */
    public String getGameStatus(){
        return gameStatus;
    }
    public AlienArray getArray(){
        return alienInvaders;
    }
    public PlayerShip getShip(){
        return ship;
    }
    public PlayerShot getShot(){
        return shot;
    }
    
    public int getScreenWidth() {
    	return screenWidth;
    }
    
    public int getScreenHeight() {
    	return screenHeight;
    }

   /** 
    * This method moves aliens 
    */
    public void moveAliens(){
        alienInvaders.aliensMovement(screenWidth);
    }
    
    /**
    *  This method checks whether or not the game has been won or lost and updates the string gameStatus appropriately
	* if the aliens reach the ship, the game is lost.
	* If all the aliens are killed, the game is won.
    */
    public void checkStatus() {
        if (!gameStatus.equals("loss")) {
            gameStatus = "win";

            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                            
                    if (alienInvaders.aliens[r][c].isAlive()) {
                        gameStatus = "continue";
                    }
                }
            }
        }
        
        if (!gameStatus.equals("win")) {
            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                    if (alienInvaders.aliens[r][c].isAlive() && alienInvaders.aliens[r][c].inBounds(410)) {
                        gameStatus = "loss";
                    }
                }
            }
        }
    }
    
	/**This method takes a string arguement and moves the ship appropriately. It prevents the ship from going out of bounds.
	* @param is the string input used to decide which way the string moves.
	*/
    public void shipMovement(String direction) {
    	ship.move(direction);
    	ship.inBounds(screenWidth - ship.getWidth());
    }

     /** 
    *  This method handles the shot firing and interaction of the shot with the aliens.
    */
    public void handleShotInteraction(){
        if (shot.getShotFired()) {
            shot.moveShot();
            shot.inBounds();

            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                    
                    if (alienInvaders.aliens[r][c].isAlive() && shot.checkHit(alienInvaders.aliens[r][c].getXCoord(), alienInvaders.aliens[r][c].getYCoord(), alienInvaders.aliens[r][c].getWidth())) {    
                        alienInvaders.aliens[r][c].destroyAlien(); 
                    }
                }
            }
        }
    }
    
    /** This method attempts to fire a shot if thre isn't already a shot on the screen. 
	* It prevents the user from firing the shot if there is a shot on the screen.
	*/
    public void shotAttempt() {
    	if (!shot.getShotFired()) {
    		shot.resetShot(true, ship.getXCoord());
    	} else {
    		if (gameVersion.equals("TEXT")) {
    			System.out.println("Out of ammo!");
    		}
    	}
    }

/*    //adjust this method to fit into one above possibly 
    public void handleShot(String handlePart){
        if (handlePart == "part1"){
            if (!shot.getShotFired()) { 
                shot.setShotColumn(ship.getLocation());
                shot.shotFired(true);
            } else {
                System.out.println("Out of ammo!");
            }
        } else if (shot.getShotFired()) { 
            shot.moveShot();
        }   
    }
	
    //adjust this method to fit with the checkStatus one possibly 
    public void quitCondition(){
		int count=0;
        if (alienInvaders.aliens[alienInvaders.getRowsAliens() -1][1].inBounds(boardHeight)) {
            quit = true;
            System.out.println("Game over, the aliens got you!");
        } else{
			for (int r = 0; r < alienInvaders.getRowsAliens() ; r++) {
				for ( int c=0; c<alienInvaders.getNumAliens(); c++){
					if (!alienInvaders.aliens[r][c].isAlive()){
						count+=1;
					}
					if (count == (alienInvaders.getRowsAliens()*alienInvaders.getNumAliens())){
						quit=true;
						System.out.println("You won!!");
					}
                }
            }
        }
    } */


    
}
