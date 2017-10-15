public class playerShip{


    private int location = 30;
    private int lastLocation = location;
    private int speed = 3;

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

    public int getSpeed() {
        return speed;
    }

    public void inBounds(int boardWidth) { 
        if (location >= boardWidth) {
            location = boardWidth - speed;
        } else if (location < speed) {
            location = 0;
        }
    }

    public void shipMovement(String direction) {
        lastLocation = location;

        if (direction.equals("A") && location > 0) { 
            location=location-speed;
            
        } else if (direction.equals("D")) { 
            location=location+speed;
        }

    }
}
