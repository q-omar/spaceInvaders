package model;

import java.util.Random;

public class InvadersGameLogic{

    private int screenHeight = 500;
    private int screenWidth = 400;
    
    private Shot shot;
    private Shot alienShot;
    private Barrier barrier;
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
            ship = new PlayerShip(screenWidth, screenHeight - 60, 20, 5);  
            shot = new Shot(420, -20, 5, 20);
            alienShot = new Shot(0, 10, 6, 12);
            alienInvaders = new AlienArray("GUI");
			barrier = new Barrier(screenWidth, screenHeight);

        } else if (version.equals("TEXT")) {
        	gameVersion = version;
            screenWidth = 60;
            screenHeight = 30;
            ship = new PlayerShip(screenWidth, screenHeight-1, 0, 5); 
            shot = new Shot(screenHeight-3, -5);
            barrier = new Barrier(screenWidth, screenHeight);
            alienShot = new Shot(0, 3);
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
    public PlayerShip getShip(){
        return ship;
    }
    public Shot getShot(){
        return shot;
    }
    
	public Barrier getBarrier(){
		return barrier;
	}
    
    public Shot getAlienShot() {
    	return alienShot;
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
    	int boundary = 0;
    	if (gameVersion.equals("GUI")) {
    		boundary = screenHeight - alienInvaders.getAliens()[0][0].getHeight()*3; // Adjust as needed
    		checkAlienHit();
    	} else {
    		boundary = screenHeight-5;
    		checkShipHit();
    	}
    	
        if (!gameStatus.equals("loss")) {
            gameStatus = "win";
            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                    if (alienInvaders.getAliens()[r][c].isAlive()) {
                        gameStatus = "continue";
                    }
                }
            }
        }
        
        if (!gameStatus.equals("win")) {
            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                    if (alienInvaders.getAliens()[r][c].isAlive() && alienInvaders.getAliens()[r][c].reachedEnd(boundary)) {
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
    
    public void moveAlienShot() {
    	if (alienShot.getShotFired()) {
    		alienShot.moveShot();
			alienShot.checkGUIBarrierHit(barrier, screenWidth, screenHeight);
    		
        	if (gameVersion.equals("GUI")) {
        		checkAlienHit();
        	} else {
        		checkShipHit();
        	}
    		
    		alienShot.inBounds(screenHeight);
    	}
    }
    
    public void checkAlienHit(){
        if (alienShot.checkHit(ship.getXCoord(), 440, ship.getHeight())){
            gameStatus = "loss";
        }
    }
    
	public void checkShipHit(){
        if (alienShot.alienShotShip(ship.getXCoord(), ship.getYCoord())){
            gameStatus = "loss";
        }
    }
	
     /** 
    *  This method handles the shot firing and interaction of the shot with the aliens.
    */
    public void handleShotInteraction(){
        if (shot.getShotFired()) {
			
            shot.moveShot();
			shot.checkGUIBarrierHit(barrier, screenWidth, screenHeight);

            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                	
                	if(alienInvaders.getAliens()[r][c].isAlive()) {
                		
                		if (gameVersion.equals("GUI") && shot.checkHit(alienInvaders.getAliens()[r][c].getXCoord(), alienInvaders.getAliens()[r][c].getYCoord(), alienInvaders.getAliens()[r][c].getWidth())) {
                			alienInvaders.getAliens()[r][c].destroyAlien();
                			
                		} else if (gameVersion.equals("TEXT") 
                				&& shot.checkTextHit(alienInvaders.getAliens()[r][c].getYCoord(), alienInvaders.getAliens()[r][c].getXCoord(), alienInvaders.getAliens()[r][c].getLastXCoord())) {
                			alienInvaders.getAliens()[r][c].destroyAlien();
                		}
                	}
                    
                }
            }
        }
    }
    
    /** This method attempts to fire a shot if thre isn't already a shot on the screen. 
	* It prevents the user from firing the shot if there is a shot on the screen.
	*/
    public void shotAttempt() {

        if (gameVersion.equals("TEXT") && shot.getShotFired()) {
                System.out.println("Out of ammo!");
        }
        shot.tryShot(ship.getXCoord());

    }
    
    //following two methods used for alien shot generation

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;//this includes the minimum into the random selection 
        return randomNum;
    }
    
    public void shotGeneration(){
        int rand1 = randInt(0, alienInvaders.getRowsAliens()-1); //generate a random number for column/row to fire 
        int rand2 = randInt(0, alienInvaders.getNumAliens()-1);
        boolean shotFired = alienShot.getShotFired();
		//System.out.println(shotFired);

        if (!shotFired){
            int randomNum = randInt(0,0); //generate a 10% chance for example for any one alien to fire 
            if (randomNum==0){
                if (alienInvaders.getAliens()[rand1][rand2].isAlive()){ //check if that alien is alive 
                    int alienShotRow = alienInvaders.getAliens()[rand1][rand2].getYCoord() + alienInvaders.getAliens()[0][0].getWidth(); //if it is, alien shot is generated at its location 
                    int alienShotCol = alienInvaders.getAliens()[rand1][rand2].getXCoord();
					if (gameVersion.equals("GUI")){
						alienShot.newAlienShot(alienShotCol, alienShotRow);
						
					}else{
						alienShot.newAlienShot(alienShotCol, alienShotRow);
					}
                }

            }
        }
    }

}
