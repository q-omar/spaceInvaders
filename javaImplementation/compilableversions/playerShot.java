public class playerShot{
   
    boolean shotFired;
    int shotLocation;
    
    public void shotFired(String shotCheck){
        if (shotCheck.equals("F")){ 
            shotFired = true;
            
        } else{ 
            shotFired = false;
        }
    }

    public boolean getShotFired(){
        return shotFired;
    }

    public int getShotLocation(){
        return shotLocation;
    }
    
}