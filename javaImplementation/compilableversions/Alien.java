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
	
	public int getLastAlienY(){
		return lastAlienY;
	}
	
	public int getLastAlienX(){
		return lastAlienX;
	}
	
	public void moveRight(){
		
		lastAlienX = alienXcoord;
		lastAlienY = alienYcoord;
		if (alienXcoord + 2 < 60){
			alienXcoord = alienXcoord+2;
		}else{
			alienXcoord = 59;
			alienYcoord += 1;
		}
	}
	
	public void moveLeft() {
		
		lastAlienX = alienXcoord;
		lastAlienY = alienYcoord;
		if (alienXcoord - 2 < 0){
			alienYcoord += 1;
		}else{
			alienXcoord = alienXcoord-2;
		}

	}
	
	public boolean inBounds() {
		boolean alienEnd = false;
		getAlienY();
		if (alienYcoord==10){
			alienEnd = true;
		}
		return alienEnd;
	}
	
}