package model;
import java.util.Random;
import javax.sound.sampled.*;
import java.io.*;

/**
 * This class holds instances of the game objects and has methods to regulate their interactions.
 */
public class InvadersGameLogic{

    private int screenHeight;
    private int screenWidth;
    
    private Shot shot;
    private Shot alienShot;
	private BarrierArray barriers;
    private PlayerShip ship;
    private AlienArray alienInvaders;

    private String gameStatus = "continue";
    private String gameVersion;
	
	/** InvadersGameLogic constructor takes a string argument and implements the GUI or Text-based version of the game. 
	* This is required because the text and GUI versions have different board dimensions and alien arrays are implemented differently.
	*/
    public InvadersGameLogic(String version){

        if (version.equals("GUI")){
        	gameVersion = version;
            screenHeight = 500;
            screenWidth = 800;
            ship = new PlayerShip(screenWidth, screenHeight - 60, 20, 8);
            shot = new Shot(420, -20, 5, 20);
            alienShot = new Shot(0, 8, 6, 12);
            alienInvaders = new AlienArray("GUI");
			barriers = new BarrierArray("GUI");

        } else if (version.equals("TEXT")) {
        	gameVersion = version;
            screenWidth = 60;
            screenHeight = 30;
            ship = new PlayerShip(screenWidth, screenHeight-1, 0, 5);
            shot = new Shot(screenHeight-3, -5);
            alienShot = new Shot(0, 3);
            alienInvaders = new AlienArray("Text");
            barriers = new BarrierArray("Text");
        }
    }

    /**
     * Getter methods to be used by controller and GUI classes 
     */
    public String getGameStatus(){
        return gameStatus;
    }
    public AlienArray getArray(){
        return new AlienArray(alienInvaders);
    }
    public PlayerShip getShip(){
        return new PlayerShip(ship);
    }
    public Shot getShot(){
        return new Shot(shot);
    }
    
	public BarrierArray getBarriers(){
		return new BarrierArray(barriers);
	}
    
    public Shot getAlienShot() {
    	return new Shot(alienShot);
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
        int boundary;
        if (gameVersion.equals("GUI")) {
            boundary = screenHeight - alienInvaders.getAliens()[0][0].getHeight()*3; // Adjust as needed
        } else {
            boundary = screenHeight-5;
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
    
    /** This method is used for moving alien shot and checking its interaction with a barrier */
    public void moveAlienShot() {
    	if (alienShot.getShotFired()) {
    		alienShot.moveShot();
    		
    		for (Barrier b : barriers.getBarriers()) {
    			if (b.getBarrierHit() < 3 && 
    					alienShot.checkHitRectangle(b.getXCoord(), b.getYCoord(), b.getWidth(), b.getHeight())) {
    				b.barrierIsHit();
    			}
    		}
    		// Checks if alien shot has hit the ship
        	if (gameVersion.equals("GUI")) {
                if (alienShot.checkHitRectangle(ship.getXCoord(), ship.getYCoord(), ship.getWidth(), ship.getHeight())){
                    gameStatus = "loss";
                    playSound("destroy.wav");
                }
        	} else {
        	    if (alienShot.checkTextHit(ship.getYCoord(), ship.getXCoord(), ship.getLastXCoord())){
        	    	gameStatus = "loss";
        	    }
        	}
    		
    		alienShot.inBounds(screenHeight);
    	}
    }
	
     /** 
    *  This method handles the shot firing and interaction of the player shot with the aliens and barriers.
    */
    public void handleShotInteraction(){
        if (shot.getShotFired()) {
			
            shot.moveShot();
            
    		for (Barrier b : barriers.getBarriers()) {
    			if (b.getBarrierHit() < 3 && 
    					shot.checkHitRectangle(b.getXCoord(), b.getYCoord(), b.getWidth(), b.getHeight())) {
    				b.barrierIsHit();
    			}
    		}

            for (int r=0; r<alienInvaders.getRowsAliens() ; r++) {
                for (int c=0; c<alienInvaders.getNumAliens(); c++){
                	
                	if(alienInvaders.getAliens()[r][c].isAlive()) {
                		
                		int xCoord = alienInvaders.getAliens()[r][c].getXCoord();
                		int yCoord = alienInvaders.getAliens()[r][c].getYCoord();
                		int width = alienInvaders.getAliens()[r][c].getWidth();
                		int height = alienInvaders.getAliens()[r][c].getHeight();
                		
                		if (gameVersion.equals("GUI") && shot.checkHitRectangle(xCoord, yCoord, width, height)) {                			
                            alienInvaders.getAliens()[r][c].destroyAlien();
                            playSound("kill.wav");

                		} else if (gameVersion.equals("TEXT") 
                				&& shot.checkTextHit(yCoord, xCoord, alienInvaders.getAliens()[r][c].getLastXCoord())) {
                			alienInvaders.getAliens()[r][c].destroyAlien();
                		}
                	}
                    
                }
            }
        }
    }
    
    /**
     * When called, this method plays sound from a given filename, provided as a string.
     * @param soundName the String name of the file
     */
    private void playSound(String soundName){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (UnsupportedAudioFileException ignored){}
        catch (LineUnavailableException ignored){}
        // FileNotFoundExceptions will not occur due to controller checking if all files are present before launching
        catch (IOException ignored){}
    }
    
    /** This method attempts to fire a shot if there isn't already a shot on the screen. 
	* It prevents the user from firing the shot if there is a shot on the screen.
	* Barrier hits will be registered if the new shot overlaps the barrier.
	*/
    public void shotAttempt() {
    	
        if (gameVersion.equals("TEXT") && shot.getShotFired()) {
                System.out.println("Out of ammo!");
        }
        if (!shot.getShotFired()){
            shot.tryShot(ship.getXCoord());
            playSound("shot.wav");
        }
        
		for (Barrier b : barriers.getBarriers()) {
			if (b.getBarrierHit() < 3 && 
					shot.checkHitRectangle(b.getXCoord(), b.getYCoord(), b.getWidth(), b.getHeight())) {
				b.barrierIsHit();
			}
		}
    }
    
    /**
     * standard method used for generating random numbers in Java
     * @param min,max set values to generate the values in between
     */
    private static int randInt(int max) {
        Random rand = new Random();
        return rand.nextInt((max) + 1);
    }

    /** 
     * Method used for generating shots for aliens, creating two random values within the range of the number of column and row
     * and then choosing that random col/row to generate a shot at, if that particular alien is alive
     */
    public void shotGeneration(){
        int rand1 = randInt(alienInvaders.getRowsAliens()-1); //generate a random number for column/row to fire
        int rand2 = randInt(alienInvaders.getNumAliens()-1);
        boolean shotFired = alienShot.getShotFired();

        if (!shotFired){
            int randomNum = randInt(0); //generate an x% chance for example for any one alien to fire
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
