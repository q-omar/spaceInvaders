//this class takes action input from the controller, updates the objects, and then
//passes those updated objects back to controller

public class InvadersGameLogic{
    
    private int windowWidth = 400;
    
    private playerShot shot = new playerShot(420, 20, 5, 20);
    private playerShip ship = new playerShip(windowWidth, 10);
    private AlienArray alienInvaders= new AlienArray("GUI");
    private String gameStatus = "continue";


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
    
}