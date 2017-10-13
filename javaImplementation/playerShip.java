public class playerShip{

    // Location
    private int location = 29;

    public int getLocation() {
        return location;
    }

    public void setLocation(int newLocation) {
        location = newLocation;
    }

    private boolean inBounds(int shipRow){ //this is a boolean function that checks out of list boundary
        boolean inBounds=true;   //i haven't employed it yet but it'll be neccessary later i think 
        if (shipRow>60 || shipRow<0){
            inBounds=false;
            System.out.print("Out of bounds!");
        }
        return inBounds;
    }    

    public void shipMovement(String direction) {

        if (direction.equals("A") && location > 0) { // if user chooses 1/left, it subtracts the shipRow 
            location=location-3; 
            
        } else if (direction.equals("D")) { // if user chooses 2/right, it adds the shipRow 
            location=location+3;
    }
}
}