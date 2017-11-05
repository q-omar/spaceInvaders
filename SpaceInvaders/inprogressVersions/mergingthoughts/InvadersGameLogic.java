//this class takes action input from the controller, updates the objects, and then
//passes those updated objects back to controller

public class InvadersGameLogic{

    private int screenHeight = 500;
    private int screenWidth = 400;
    
    private playerShot shot;
    private playerShip ship;
    private AlienArray alienInvaders;
    private String gameStatus = "continue";
    private String gameVersion;

    public InvadersGameLogic(String version){

        if (version.equals("GUI")){
        	gameVersion = version;
            screenHeight = 500;
            screenWidth = 400;
            ship = new playerShip(screenWidth, 5);  // Temporarily using same constructors as before, update later to correspond to version
            shot = new playerShot(420, 20, 5, 20);
            alienInvaders = new AlienArray("GUI");

        } else if (version.equals("TEXT")) {
        	gameVersion = version;
            screenWidth = 60;
            screenHeight = 30;
            ship = new playerShip(screenWidth, 3); 
            shot = new playerShot(screenWidth-1, 5);
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
    * This method moves aliens 
    */
    public void moveAliens(){
        alienInvaders.aliensMovement(screenWidth);
    }
    
    /**
    *  This method checks whether or not the game has been won or lost and updates the boolean gameStatus appropriately.
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
    
    public void shipMovement(String direction) {
    	ship.move(direction);
    	ship.inBounds(screenWidth);
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
                    
                    if (alienInvaders.aliens[r][c].isAlive() && shot.checkHit(alienInvaders.aliens[r][c].getAlienX(), alienInvaders.aliens[r][c].getAlienY(), alienInvaders.aliens[r][c].getRadius())) {    
                        alienInvaders.aliens[r][c].destroyAlien(); 
                    }
                }
            }
        }
    }
    
    // Called when the user presses F
    public void shotAttempt() {
    	if (!shot.getShotFired()) {
    		shot.resetShot(true, ship.getLocation());
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