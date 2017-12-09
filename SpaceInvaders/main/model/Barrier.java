package model;

import java.awt.Graphics;
import java.awt.Color;

/*
*This class holds the information for individual barriers, from its health, position on board, and how it looks for both text and GUI
*@param isHit health of barrier
*@param barrierChar what any individual characters of barrier will look like, set default to ' '
*/
public class Barrier extends Shape{
	private int isHit = 0;
	private char barrierChar =' ';
	
	/**
	 * A constructor for the Barrier class which takes coordinates, width and height as parameters.
	 * @param xcoord the top-left x-coordinate
	 * @param ycoord the top-left y-coordinate
	 * @param wide the width of the barrier in pixels/board units
	 * @param high the height of the barrier in pixels/board units
	 */
	Barrier(int xcoord, int ycoord, int wide, int high){
		super(xcoord,ycoord, wide,high);
	}
	/*
	*method: BarrierIsHit
	*essentially the health of an individual barrier, that being variable isHit
	*is called when a barrier is detected to be hit by a shot outside of this class
	*/
	void barrierIsHit(){
		if (isHit <3){
			isHit +=1;
		}
	}
	
	int getBarrierHit(){
		return isHit;
	}
	
	/*
	*method: barrierCharText
	*	designates what individual characters of the barriers will look like, assigning the 
	*	health of the barrier in numerical form as a visual representation of the health of 
	*	the barrier itself
	*/
	public char barrierCharText(){
		
		if (isHit ==0){
			barrierChar ='3';
		} else if (isHit==1){
			barrierChar ='2';
		}else if (isHit ==2){
			barrierChar ='1';
		}else if (isHit ==3){
			barrierChar =' ';
		}
		return barrierChar;
	}
	
	/**
	 * Draws the barrier and changes its colour depending on its isHit status.
	 */
	public void draw(Graphics g){
		if (isHit ==0){
			g.setColor(Color.GREEN);
		} else if (isHit==1){
			g.setColor(Color.YELLOW);
		}else if (isHit ==2){
			g.setColor(Color.RED);
		}else if (isHit ==3){
			g.setColor(Color.BLACK);
		}
		g.fillRect(getXCoord(),getYCoord(),getWidth(),getHeight());
	}
}
