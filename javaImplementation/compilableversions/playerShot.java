public class playerShot{
    
	playerShip shipOne = new playerShip();
    int shotColumn = shipOne.getLastLocation();
	boolean shotFired = false;
	int boardHeight= 30;
	int shotRow= boardHeight;
	
	public void setIsFired(){
		shotFired = true;
	}
	
	public boolean isFired(){
		return shotFired;
	}
	public int getShotRow(){
		return shotRow;
	}
	
    public int getShotColumn(){
        return shotColumn;
    }
	
	public int moveBullet(int shotColumn, int shotRow){
		shotRow= shotRow-1;
		shotColumn = shotColumn;
		return shotRow;
	}	

}