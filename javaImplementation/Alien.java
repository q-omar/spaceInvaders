public class Alien{
	
	private int alienHeight = 2;
	private int lastAlienHeight = alienHeight;
	private int alienWidth = 9; 
	private int lastAlienWidth = alienWidth;
	
	public int getAlienHeight() {
		return alienHeight;
	}
	
	public int getAlienWidth(){
		return alienWidth;
	}
	
	public void setAlienWidth(int newAlienWidth) {
		lastAlienWidth = alienWidth;
		alienWidth = newAlienWidth;
	}
	
	public void setAlienHeight(int newAlienHeight){
		lastAlienHeight = alienHeight;
		alienHeight = newAlienHeight;
	}
	
	public int getLastAlienHeight(){
		return lastAlienHeight;
	}
	
	public int getLastAlienWidth(){
		return lastAlienWidth;
	}
		
	public void alienMoveDown(int boardWidth){
		lastAlienHeight = alienHeight;
		if (alienWidth >= boardWidth-2  ) {
			alienHeight = alienHeight+3;
			alienWidth = 0;
		}
		else if (alienWidth<=0){
			alienWidth= boardWidth-1;
			alienHeight=alienHeight+2;
		}
		
	}
	public int alienMove(){
		lastAlienWidth = alienWidth;
		if (alienHeight<=0){
			alienWidth = alienWidth+2;
		}
		else if (alienHeight>58){
			alienWidth = alienWidth-2;
		}
		return alienWidth;
	}		
	public void alienMoveSideways(String direction){
		lastAlienWidth = alienWidth;
		if (direction.equals("A") || direction.equals("D")) { 
			alienMove();
		}     
	}	
	public int alienMoveRight(){
	    alienWidth = alienWidth+2;
	    getAlienWidth();
		
	
	public int alienMoveLeft() {
	    alienWidth = alienWidth-2;
	    getAlienWidth();
	
}