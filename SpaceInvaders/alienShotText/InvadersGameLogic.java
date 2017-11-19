import java.util.Random;

public class InvadersGameLogic{

    private int screenHeight = 500;
    private int screenWidth = 400;
    
    private PlayerShot shot;
    private PlayerShip ship;
    private AlienArray alienInvaders;
    private AlienShots alienShots;
	private Barrier barrier;
    private String gameStatus = "continue";
    private String gameVersion;

    public InvadersGameLogic(String version){

        if (version.equals("GUI")){
        	gameVersion = version;
            screenHeight = 500;
            screenWidth = 400;
            ship = new PlayerShip(screenWidth, screenHeight - 60, 20, 5);  // Temporarily using same constructors as before, update later to correspond to version
            shot = new PlayerShot(420, 20, 5, 20);
            alienInvaders = new AlienArray();
            alienShots = new AlienShots(0,0,0,0,0);

        } else if (version.equals("TEXT")) {
        	gameVersion = version;
            screenWidth = 60;
            screenHeight = 30;
            ship = new PlayerShip(screenWidth, screenHeight-1, 0, 5); 
            shot = new PlayerShot(screenHeight-3, 5);
			barrier = new Barrier(screenWidth, screenHeight);
            alienInvaders = new AlienArray("TEXT");
			alienShots = new AlienShots(0,0,0,0,0);
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
	public Barrier getBarrier(){
		return barrier;
	}
	public AlienShots getAlienShots(){
        return alienShots;
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

    public void alienShots(){
        shotGeneration();
    }
    
    /**
    *  This method checks whether or not the game has been won or lost and updates the boolean gameStatus appropriately.
    */
    public void checkAlienHit(){
        if (alienShots.alienHitDetection(ship.getXCoord(), 440, ship.getHeight())){
            gameStatus = "loss";
        }
    }
	
	public void checkShipHit(){
        if (alienShots.alienShotShip(ship.getXCoord(), ship.getYCoord())){
            gameStatus = "loss";
        }
    }
	
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
                    if (alienInvaders.getAliens()[r][c].isAlive() && alienInvaders.getAliens()[r][c].inBounds(boundary)) {
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
    
    public void handleShotText() {
        if (shot.getShotFired()) {
            shot.moveShot();
            shot.inBounds();
        }
		if (alienShots.getShotFired()){
			alienShots.moveShot();
			alienShots.inBounds(screenHeight); 
		}
    }

     /** 
    *  This method handles the shot firing and interaction of the shot with the aliens.
    */
    public void handleShotInteraction(){
        if (alienShots.getShotFired()){
            alienShots.moveShot();
            alienShots.inBounds(screenHeight);
        }
        
        if (shot.getShotFired()) {
            shot.moveShot();
            shot.inBounds();
        if (shot.getHit1() >=0){
            shot.checkBarrierHit(screenWidth-345,20,60,screenHeight-100);
        }
        if (shot.getHit2() >=0){
            shot.checkBarrierHit(screenWidth-230,20,60,screenHeight-100);
        }
        if (shot.getHit3() >=0){
            shot.checkBarrierHit(screenWidth-115,20,60,screenHeight-100);
        }
           
            
            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                    
                    if (alienInvaders.getAliens()[r][c].isAlive() && shot.checkHit(alienInvaders.getAliens()[r][c].getXCoord(), alienInvaders.getAliens()[r][c].getYCoord(), alienInvaders.getAliens()[r][c].getWidth())) {    
                        alienInvaders.getAliens()[r][c].destroyAlien(); 
                    }
                }
            }
        }
    }
    
    
    // Called when the user presses F
    public void shotAttempt() {
    	if (!shot.getShotFired()) {
    		shot.resetShot(true, ship.getXCoord());
    	} else {
    		if (gameVersion.equals("TEXT")) {
    			System.out.println("Out of ammo!");
    		}
    	}
    }
	
	//************************************************************
    //following two methods used for alien shot generation

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;//this includes the minimum into the random selection 
        return randomNum;
    }
    
    public void shotGeneration(){
        int rand1 = randInt(0, alienInvaders.getRowsAliens()-1); //generate a random number for column/row to fire 
        int rand2 = randInt(0, alienInvaders.getNumAliens()-1);
        boolean shotFired = alienShots.getShotFired();
		System.out.println(shotFired);

        if (!shotFired){
            int randomNum = randInt(0,0); //generate a 10% chance for example for any one alien to fire 
            if (randomNum==0){
                if (alienInvaders.getAliens()[rand1][rand2].isAlive()){ //check if that alien is alive 
                    int alienShotRow = alienInvaders.getAliens()[rand1][rand2].getYCoord() + alienInvaders.getAliens()[0][0].getWidth(); //if it is, alien shot is generated at its location 
                    int alienShotCol = alienInvaders.getAliens()[rand1][rand2].getXCoord();
					if (gameVersion.equals("GUI")){
						alienShots = new AlienShots(alienShotCol, alienShotRow, 6, 12, 10);
					}else{
						alienShots = new AlienShots(alienShotCol, alienShotRow, 6, 12, 3);
					}
                    alienShots.shotFired(true);
                }

            }
        }
    }

	
	
    /*adjust this method to fit with the checkStatus one possibly 
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