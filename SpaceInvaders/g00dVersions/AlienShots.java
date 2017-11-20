import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class AlienShots extends Shape{
	
    private boolean shotFired = false;
	int lastShotRow = 0;
	
    public AlienShots(int xLoc, int yLoc, int width, int height, int speed){
        super(xLoc, yLoc, width, height);
        setVSpeed(speed);
    }
    

	//***************************************************
	public boolean alienShotShip(int shipXCoord, int shipYCoord){
		boolean hit = false;
		
		if (getXCoord() >= shipXCoord-2 && getXCoord() <= shipXCoord+2){
			if (getYCoord() >= shipYCoord - 2){
				hit = true;
			}
		}
		
		return hit;
	}
	
    //alien hitdetection might need a tiny bit of tweaking, got a bit funky after extending to shape
    //i tihnk it has to do with the alien shot being a rectangle? idk 
    
    public boolean alienHitDetection(int targetX, int targetY, int targetLength) {
        boolean hit = false;
        int xCoord;
        targetX += targetLength; // Set x and y to center of target circle
        targetY += targetLength;

        if (getXCoord() >= targetX - 0.5 * getWidth()) {
            xCoord = getXCoord(); // Checks top left point of bullet
        } else {
            xCoord = getXCoord() + getWidth(); // Checks top right point of bullet
        }

        // From Prof. Verwaal's code for the distance method in the Point class used in Team Assignment 4
        int xdiff = xCoord - targetX;
        int ydiff = getYCoord() - targetY;
        double distance = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        if (distance <= targetLength) {
            hit = true;
            shotFired = false;
            setYCoord(0);
        }

        return hit;
    }

	//************************************************************
	
	public boolean checkBarrierHit(Barrier barrier, int boardWidth, int boardHeight){
		
		boolean hit = false;
		
		if (shotFired){
			if (getYCoord() >= boardHeight-6){
				if (getXCoord() <= boardWidth-45 && getXCoord() >= boardWidth-55){
					if (barrier.getBarrier1HP() > 0){
						barrier.updateBarrier1();				
						System.out.println("BARRIER 1 HIT");
						hit = true;
						shotFired = false;
					}
				}else if (getXCoord() <= boardWidth-25 && getXCoord() >= boardWidth-35){//25 to 35 
					if (barrier.getBarrier2HP() > 0){
						barrier.updateBarrier2();
						hit = true;
						System.out.println("BARRIER 2 HIT");
						shotFired = false;
					}
				}else if (getXCoord() <= boardWidth-5 && getXCoord() >= boardWidth-15){
					if (barrier.getBarrier3HP() > 0){
						barrier.updateBarrier3();
						hit = true;
						System.out.println("BARRIER 3 HIT");
						shotFired = false;
					}
				}
			}
		}
		return hit;
	}
	
   
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getXCoord(),getYCoord(), getWidth(), getHeight());
    }

}

