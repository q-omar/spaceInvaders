public class playerShip{

    // Location
    private int location = 29;

    public int getLocation() {
        return location;
    }

    public void setLocation(int newLocation) {
        location = newLocation;
    }

    private boolean inBounds(int shipRow){ 
        boolean inBounds=true;   
        if (shipRow>60 || shipRow<0){
            inBounds=false;
            System.out.print("Out of bounds!");
        }
        return inBounds;
    }    

    public void shipMovement(String direction) {

        if (direction.equals("A") && location > 2) { 
            location=location-3; 
            
        } else if (direction.equals("D") && location<58) { 
            location=location+3;
		}
	}
}
