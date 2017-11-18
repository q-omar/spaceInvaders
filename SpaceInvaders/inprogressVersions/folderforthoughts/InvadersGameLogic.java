//this class does not compile, just some thoughts

import java.util.Random;

public class InvadersGameLogic{

    private int screenHeight = 500;
    private int screenWidth = 400;
    

    //so now we have two shot objects, one playershot, one alienshot. respective methods in the shot class
    //will be applied to the object ie theres two move shot methods since one has to go up, other down 
    private Shots playerShot;
    private Shots alienShot;
    private PlayerShip ship;
    private AlienArray alienInvaders;
    private String gameStatus = "continue";
    private String gameVersion;

    private Barrier barrier1; //i figured we could create three barrier objects, specifying the 
    private Barrier barrier2; //x locations of them 
    private Barrier barrier3;


    public InvadersGameLogic(String version){

        if (version.equals("GUI")){
        	gameVersion = version;
            screenHeight = 500;
            screenWidth = 400;
            ship = new PlayerShip(screenWidth, screenHeight - 60, 20, 5);  // Temporarily using same constructors as before, update later to correspond to version
            playerShot = new Shots(420, screenWidth/2, 20, 5, 20); 
            alienInvaders = new AlienArray();
            alienShot = new Shots(0,0,0,0,0);
            barrier1 = new Barrier(100, screenHeight);
            barrier2 = new Barrier(200, screenHeight);
            barrier3 = new Barrier(300, screenHeight);

        } else if (version.equals("TEXT")) {
        	gameVersion = version;
            screenWidth = 60;
            screenHeight = 30;
            ship = new PlayerShip(screenWidth, screenHeight-1, 0, 3); 
            shot = new Shots(screenHeight-3, 5);
            alienInvaders = new AlienArray("TEXT");
			alienShot = new Shots(0,0,0,0,0);
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
    public PlayerShot getPlayerShot(){
        return playerShot;
    }
    
    public int getScreenWidth() {
    	return screenWidth;
    }
    
    public int getScreenHeight() {
    	return screenHeight;
    }

    public AlienShots getAlienShot(){
        return alienShot;
    }

   /** 
    * This method moves aliens 
    */
    public void moveAliens(){
        alienInvaders.aliensMovement(screenWidth);
    }

    public void alienShots(){
        shotGeneration();
    }
    
    /**
    *  This method checks whether or not the game has been won or lost and updates the boolean gameStatus appropriately.
    */
    public void checkAlienHitPlayer(){
        if (alienShot.checkGuiHit(ship.getXCoord(), 440, ship.getHeight())){
            gameStatus = "loss";
        }
    }
    
    public void checkStatus() {
        checkAlienHitPlayer();
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
                    if (alienInvaders.getAliens()[r][c].isAlive() && alienInvaders.getAliens()[r][c].inBounds(410)) {
                        gameStatus = "loss";
                    }
                }
            }
        }
    }
    
    public void shipMovement(String direction) {
    	ship.move(direction);
    	ship.inBounds(screenWidth - ship.getWidth());
    }

     /** 
    *  This method handles the shot firing and interaction of the shot with the aliens.
    */


    public void checkBarrierHits(){
        //invoke the GUI hit detection method in shot class here ie 
        if alienShot.checkGuiHit(input coords for barrier here){
            destroy barrier or make it tick health //just some pseudocode for now
        }
        if playerShot.checkGuiHit(input coords for barrier here){
            tick barrier
        }

        //probably put above in a for loop so that it checks barrier 1, 2 and 3 together 
    }


    public void checkPlayerHitAliens(){
        for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
            for (int c=0; c<alienInvaders.getNumAliens(); c++){
                
                if (alienInvaders.getAliens()[r][c].isAlive() && playerShot.checkGuiHit(alienInvaders.getAliens()[r][c].getXCoord(), alienInvaders.getAliens()[r][c].getYCoord(), alienInvaders.getAliens()[r][c].getWidth())) {    
                    alienInvaders.getAliens()[r][c].destroyAlien(); 
                }
            }
        }
    }
    public void handleShotInteraction(){
        if (alienShot.getAlienShot()){
            alienShot.moveAlienShot();
            alienShot.inBounds();
        }
        
        if (playerShot.getPlayerShot()) {
            playerShot.movePlayerShot();
            playerShot.inBounds();
        }
        checkBarrierHits();
        checkPlayerHitAliens();
        
    }
    
    // Called when the user presses F
    public void shotAttempt() {
    	if (!playerShot.getShotFired()) {
            playerShot = new Shot(420, playerShot.getXCoord(), 20, 5, 20); //idk if this is right to do, creating a new shot object to overwrite it but yeah
            playerShot.setPlayerShot(true); //figured it might be since thats the way i did alien shots
    	} else {
    		if (gameVersion.equals("TEXT")) {
    			System.out.println("Out of ammo!");
    		}
    	}
    }

    //following two methods used for alien shot generation

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;//this includes the minimum into the random selection 
        return randomNum;
    }
    
    public void alienShotAttempt(){ //renamed alienshotgeneration method to be more aligned with playershot 
        int rand1 = randInt(0, alienInvaders.getRowsAliens()-1); //generate a random number for column/row to fire 
        int rand2 = randInt(0, alienInvaders.getNumAliens()-1);

        if (!alienShot.getAlienShotFired()){
            int randomNum = randInt(0,0); //generate a 10% chance for example for any one alien to fire 
            if (randomNum==0){
                if (alienInvaders.getAliens()[rand1][rand2].isAlive()){ //check if that alien is alive 
                    int alienShotRow = alienInvaders.getAliens()[rand1][rand2].getYCoord() + alienInvaders.getAliens()[0][0].getWidth(); //if it is, alien shot is generated at its location 
                    int alienShotCol = alienInvaders.getAliens()[rand1][rand2].getXCoord();
                    alienShot = new Shot(alienShotCol, alienShotRow, 6, 12, 10);
                    alienShot.setAlienShot(true);
                }

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
