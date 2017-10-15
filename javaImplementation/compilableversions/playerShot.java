public class playerShot{
   
    boolean shotFired;
    int shotRow;
    int lastShotRow = shotRow;
    int shotColumn;
    int speed = 1;
    
    public void shotFired(boolean shotStatus){
        if (shotStatus){ 
            shotFired = true;
        } else { 
            shotFired = false;
        }
    }

    public boolean getShotFired(){
        return shotFired;
    }

    public int getShotRow(){
        return shotRow;
    }

    public int getLastShotRow(){
        return lastShotRow;
    }

    public int getShotColumn(){
        return shotColumn;
    }

    public void setShotColumn(int column) {
        shotColumn = column;
    }

    public void setShotRow(int row) {
        lastShotRow = shotRow;
        shotRow = row;
    }

    public void moveShot() {
        lastShotRow = shotRow;
        shotRow -= speed;
    }

    public void inBounds() { 
        if (shotRow < 0) {
            shotFired = false;
        }
    }

}