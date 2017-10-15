public class Alien{
	
	private int alienYcoord = 2;
	private int lastAlienY = alienYcoord;
	private int alienXcoord = 9; 
	private int lastAlienX = alienXcoord;
	private int horizontalSpeed = 3; // Made movement speed a variable, set it same as the ship so bullets will stay aligned
	private int verticalSpeed = 5; // I think this has to be odd with the way we set up left/right movement
	private boolean isAlive = true;
	
	public int getAlienY() {
		return alienYcoord;
	}
	
	public int getAlienX(){
		return alienXcoord;
	}
	
	public int getLastAlienY(){
		return lastAlienY;
	}
	
	public int getLastAlienX(){
		return lastAlienX;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void destroyAlien() {
		isAlive = false;
	}

	public boolean inBounds(int boardHeight) {
		boolean alienEnd = false;
		if (alienYcoord >= boardHeight - 1){
			alienEnd = true;
		}
		return alienEnd;
	}
	
	public void moveRight(int boardWidth){ // Just added boardWidth as a parameter instead of hard-coding 60
		lastAlienY = alienYcoord;
		lastAlienX = alienXcoord;
		if (alienXcoord + horizontalSpeed >= boardWidth) {
			alienXcoord = boardWidth - horizontalSpeed;
			alienYcoord += verticalSpeed;
		} else {
			alienXcoord += horizontalSpeed;
		}
	}
	
	public void moveLeft(int boardWidth) {
		lastAlienX = alienXcoord;
		lastAlienY = alienYcoord;
		if (alienXcoord - horizontalSpeed < 0) {
			alienXcoord = 0;
			alienYcoord += verticalSpeed;
		} else {
			alienXcoord -= horizontalSpeed;
		}
	}

}