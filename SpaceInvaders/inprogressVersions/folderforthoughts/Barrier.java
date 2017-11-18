//i have no idea if this would even be possible but i thought if we could put all the barrier methods
//into the barrier class itself again i have no idea how this class works exactly so i
//pretty much just copy pasted stuff for now 


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class Barrier extends Shape{

    private boolean isAlive = true;
	private Color barrier1;
    private Color lastBarrier1;
    
    private int hitCount1=3;
	private int hitCount2=3;
    private int hitCount3=3;


    public Barrier(int xLoc, int yLoc, int width, int height) {
        super(xLoc, yLoc, width, height);
    }

    //i think we might be able to use similar destroy/isalive methods
        //from our alien class for barriers as well 

    public boolean isAlive() {
        return isAlive;
    }
   
    public void destroyBarrier() { 
        isAlive = false;
    }
    
    public void setHit1(int aHit1){
        hitCount1 -= aHit1;
		//System.out.print(hitCount1);
    }
	
	public void setHit2(int aHit2){
        hitCount2 -= aHit2;
    }
	
	public void setHit3(int aHit3){
        hitCount3 -= aHit3;
    }
	public int getHit1(){
		return hitCount1;
	}
	
	public int getHit2(){
		return hitCount2;
	}
	
	public int getHit3(){
		return hitCount3;
    }
    




    public int getLocation() {
        return location;
    }

    public void setLocation(int newLocation) {
        location = newLocation;
    }
    
   	public void draw(Graphics gr) {
		int r = 0;
		int g = 0;
		int b = 0;
		
		
	}
}