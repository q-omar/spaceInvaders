public class InvadersGameLogic{

    private int screenHeight = 500;
    private int screenWidth = 400;
    
    private playerShot shot;
    private playerShip ship;
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
            ship = new playerShip(screenWidth, screenHeight - 60, 20, 5);  
			// Temporarily using same constructors as before, update later to correspond to version
            shot = new playerShot(420, 20, 5, 20,screenHeight-60);
            alienInvaders = new AlienArray("GUI");

        } else if (version.equals("TEXT")) {
        	gameVersion = version;
            screenWidth = 60;
            screenHeight = 30;
            ship = new playerShip(screenWidth, screenHeight-1, 0, 3); 
            shot = new playerShot(screenHeight-3, 5);
            alienInvaders = new AlienArray("Text");
        }
    }

    /**
     * Getter methods to be used by controller and GUI classes 
     */
    public String getGameStatus(){
        return gameStatus;
    }
    public AlienArray getArray(){
        return alienInvaders;
    }
    public playerShip getShip(){
        return ship;
    }
    public playerShot getShot(){
        return shot;
    }
    
    public int getScreenWidth() {
    	return screenWidth;
    }
    
    public int getScreenHeight() {
    	return screenHeight;
    }

   /** 
    * This method calls alienInvaders movement 
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
                    if (alienInvaders.aliens[r][c].isAlive() && alienInvaders.aliens[r][c].inBounds(screenHeight -ship.getWidth())) {
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
	
	public void handleShotText() {
        if (shot.getShotFired()) {
            shot.moveShot();
            shot.inBounds();
        }
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
						//shot.shotFired(false);
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

}
