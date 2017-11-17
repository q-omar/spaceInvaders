import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;

public class Barrier{


    private int location;
	private int height;
	private Color barrier1;
	private Color lastBarrier1;

	
    public Barrier(int screenWidth,int screenHeight) {
        location = screenWidth;
		height = screenHeight-100;
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