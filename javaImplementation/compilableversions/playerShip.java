public class playerShip{
  
    // Location

    private int location = 29;
    private int lastLocation = location;
	private int boardWidth =60;
  
    public int getLocation() {
        return location;
    }
  
    public int getLastLocation() {
        return lastLocation;
    }
 
    public void setLocation(int newLocation) {
        lastLocation = location;
        location = newLocation;
    }
  
    public void shipMovement(String direction) {
        lastLocation = location;
 
        if (direction.equals("A") && location > 0) { 
            location=location-1; 
        } else if (direction.equals("D") && location<boardWidth) { 
            location=location+1;
		} else if ((direction.equals("A") || direction.equals("D")) && (location<0 || location>boardWidth+5)){
			location = location;
		}
  	}
  }