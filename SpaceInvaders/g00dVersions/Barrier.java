import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class Barrier{

	int barrier1HP = 3;
	int barrier2HP = 3;
	int barrier3HP = 3;

    private int location;
	private int height;
	private Color barrier1;
	private Color lastBarrier1;
	
	public Barrier(int screenWidth,int screenHeight) {
		location = screenWidth;
		height = screenHeight-100;
    }
//***************************

	public int getBarrier1HP(){
		return barrier1HP;
	}
	
	public int getBarrier2HP(){
		return barrier2HP;
	}
	
	public int getBarrier3HP(){
		return barrier3HP;
	}
	
	public void updateBarrier1(){
		barrier1HP--;
	}
	
	public void updateBarrier2(){
		barrier2HP--;
	}
	
	public void updateBarrier3(){
		barrier3HP--;
	}

//*******************************

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