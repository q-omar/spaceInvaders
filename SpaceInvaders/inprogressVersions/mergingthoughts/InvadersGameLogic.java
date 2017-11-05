//this class takes action input from the controller, updates the objects, and then
//passes those updated objects back to controller

public class InvadersGameLogic{
    
    private int windowWidth = 400;
    
    private playerShot shot;
    private playerShip ship;
    private AlienArray alienInvaders;
    private String gameStatus = "continue";

    public InvadersGameLogic(String version){
        if (version=="GUI"){
            ship = new playerShip("GUI");
            shot = new playerShot("GUI");
            alienInvaders = new AlienArray("GUI");

        } else{
            ship = new playerShip("Text"); //need new constructors in similar format to this one
            shot = new playerShot("Text"); //for ship, shot and alien 
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

   /** 
    * This method moves aliens 
    */

    public void moveAliens(){
        alienInvaders.aliensMovement(windowWidth);
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


    //adjust this method to fit into one above possibly 
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
    }
    
}