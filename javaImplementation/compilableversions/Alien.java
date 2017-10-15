public class Alien{
	
	private int alienYcoord = 2;
	private int lastAlienY = alienYcoord;
	private int alienXcoord = 9; 
	private int lastAlienX = alienXcoord;
	
	public int getAlienY() {
		return alienYcoord;
	}
	
	public int getAlienX(){
		return alienXcoord;
	}
	
	public void setAlienX(int newAlienX) {
		lastAlienX = alienXcoord;
		alienXcoord = newAlienX;
	}
	
	public void setAlienY(int newAlienY){
		lastAlienY = alienYcoord;
		alienYcoord = newAlienY;
	}
	
	public int getLastAlienY(){
		return lastAlienY;
	}
	
	public int getLastAlienX(){
		return lastAlienX;
	}
	
	public int moveRight(){
	    alienXcoord = alienXcoord+2;
	    getAlienX();
		return alienXcoord;
	}
	
	public int moveLeft() {
	    alienXcoord = alienXcoord-2;
	    getAlienX();
		return alienXcoord;
	}
	
	
}